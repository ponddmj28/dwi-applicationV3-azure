package th.co.acc.dwi.conf;


import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import th.co.acc.dwi.model.WorkOrder;
import th.co.acc.dwi.service.WorkOrderService;
import th.co.acc.reader.WorkOrderReader;

@EnableBatchProcessing
@Configuration
public class BatchConfiguration {
	
	@Autowired
	private JobBuilderFactory jobs;
	
	@Autowired
	private StepBuilderFactory step;
	
	@Autowired
	private WorkOrderService workOrderService;
	
	@Bean
	@StepScope
	public WorkOrderReader workOrderReader() {
		return new WorkOrderReader(workOrderService);
	}
	
	@Bean("workOrderJobCsv")
	public Job workOrderJobCsv(){
	        return jobs.get("workOrderJobCsv")
	        		.incrementer(new RunIdIncrementer())
	                .start(workOrderStepCsv())
	                .build();
	}
	
	@Bean
	public Step workOrderStepCsv() {
		return step.get("workOrderStep")
				.chunk(10)
				.reader(workOrderReader())
				.writer(workOrderFlatFileItemWriter(null))
				.build();
	}
	
	@Bean("workOrderJobXML")
	public Job workOrderJobXML(){
	        return jobs.get("workOrderJobXML")
	        		.incrementer(new RunIdIncrementer())
	                .start(workOrderStepXML())
	                .build();
	}
	
	@Bean
	public Step workOrderStepXML() {
		return step.get("workOrderStepXML")
				.chunk(10)
				.reader(workOrderReader())
				.writer(staxEventItemWriter(null))
				.build();
	}
	
	@Bean
	@StepScope
	public FlatFileItemWriter<Object> workOrderFlatFileItemWriter(
			@Value("#{jobParameters['fileOutput']}")
			FileSystemResource output
			){
		FlatFileItemWriter writer = new FlatFileItemWriter();
		PropertyDescriptor[] fields = ReflectUtils.getBeanProperties(WorkOrder.class);
		String[] names = new String[fields.length];
		int i = 0;
		for(PropertyDescriptor fld : fields) {
			names[i++] = fld.getName();
		}
        writer.setResource(output);
        writer.setLineAggregator( new DelimitedLineAggregator(){
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor(){
                    {
                        setNames(names);
                    }
                });
            }
        });

        // how to write the header
        writer.setHeaderCallback(new FlatFileHeaderCallback() {
            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.write(String.join(",", names));
            }
        });

        writer.setAppendAllowed(false);
        writer.setFooterCallback(new FlatFileFooterCallback() {
            @Override
            public void writeFooter(Writer writer) throws IOException {
                writer.write("DWI The file is created at " + new SimpleDateFormat().format(new Date()));
            }
        });
        return writer;
	}
	
	@Bean
	@StepScope
	public StaxEventItemWriter<Object> staxEventItemWriter(
			@Value("#{jobParameters['fileOutput']}")
				FileSystemResource output){
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<String,Class> aliases = new HashMap<String,Class>();
		aliases.put("WorkOrder", WorkOrder.class);
		marshaller.setAliases(aliases);
		marshaller.setAutodetectAnnotations(true);
		StaxEventItemWriter writer = new StaxEventItemWriter();
		writer.setMarshaller(marshaller);
		writer.setResource(output);
		writer.setRootTagName("WorkOrders");
		writer.setOverwriteOutput(true);
		
		return writer;
	}
}
