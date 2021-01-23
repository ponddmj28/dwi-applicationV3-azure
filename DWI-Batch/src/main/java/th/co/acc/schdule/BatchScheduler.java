package th.co.acc.schdule;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import th.co.acc.dwi.service.util.BeanUtils;

@Component
public class BatchScheduler {
	
	@Autowired
    private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("workOrderJobXML")
	private Job jobXml;
	
	@Autowired
	@Qualifier("workOrderJobCsv")
	private Job jobCsv;
	
	@Value("${tmp.output.path}")
	private String pathOutput;
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value("${bt.mail.from}")
	private String from;
	@Value("${bt.mail.to}")
	private String to;
	@Value("${bt.mail.subject}")
	private String subject;
	@Value("${bt.mail.content}")
	private String contents;
	
//	@Scheduled(cron = "${sampling.schd.value}")
//	public void generateXML() throws Exception {
//
//		System.out.println("Job Started at :" + new Date());
//		File name = new File(pathOutput,"workorder_"+System.currentTimeMillis()+".xml");
//		JobParameters param = new JobParametersBuilder().addString("fileOutput", name.getAbsolutePath()).addLong( "time.millis", System.currentTimeMillis(), true)
//				.toJobParameters();
//		JobExecution execution = jobLauncher.run(jobXml,param);
//
//		System.out.println("Job finished with status :" + execution.getStatus());
//		sendWithAttachments(name);
//	}
//	
//	@Scheduled(cron = "${sampling.schd.value}")
//	public void generateCsv() throws Exception {
//
//		System.out.println("Job Started at :" + new Date());
//		File name = new File(pathOutput,"workorder_"+System.currentTimeMillis()+".csv");
//		JobParameters param = new JobParametersBuilder().addString("fileOutput", name.getAbsolutePath()).addLong( "time.millis", System.currentTimeMillis(), true)
//				.toJobParameters();
//		JobExecution execution = jobLauncher.run(jobCsv,param);
//		System.out.println("Job finished with status :" + execution.getStatus());
//		sendWithAttachments(name);
//
//	}
	
	private void sendWithAttachments(File file) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject.concat(" ").concat(file.getName()));
        helper.setText(contents);
        if(BeanUtils.isNotNull(file) && file.exists()) {
        	helper.addAttachment(file.getName(), file);
        }
        javaMailSender.send(msg);
    }
}
