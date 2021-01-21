package th.co.acc.dwi.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import th.co.acc.dwi.model.UserInfo;
import th.co.acc.dwi.service.impl.AuthenticationServiceImpl;
import th.co.acc.dwi.utils.BeanUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private AuthenticationServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.authenticationProvider(new AuthenticationProvider() {
						@Override
						public boolean supports(Class<?> authentication) {
							return authentication.equals(UsernamePasswordAuthenticationToken.class);
						}
						@Override
						public Authentication authenticate(Authentication authentication) throws AuthenticationException {
							String username = authentication.getPrincipal().toString();
							String password = authentication.getCredentials().toString();
							if(BeanUtils.isEmpty(username) || BeanUtils.isEmpty(password)) {
								throw new UsernameNotFoundException("username and password incorrect");
							}
							WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
							
							Authentication auth = null;
								UserInfo userInfo;
								try {
									userInfo = userDetailsService.loadUserByUsername(username);
									if(BeanUtils.isNull(userInfo)){
										throw new UsernameNotFoundException("not found username");
									}
									if(!password.equals(userInfo.getPassword()))
									{
										throw new UsernameNotFoundException("username and password incorrect");
									}
								}  catch (Exception e){
									throw e;
								}
								List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
								grantedAuths.add(new SimpleGrantedAuthority(userInfo.getRoleCode()));
								auth = new UsernamePasswordAuthenticationToken(userInfo, password, grantedAuths);

							return auth;
						}
					})
                   ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/isAlive").permitAll()
                .antMatchers("/healthz").permitAll()
                .antMatchers("/env").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/login*").permitAll()
                .antMatchers("/**").authenticated()
                .and().formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .defaultSuccessUrl("/listOrders")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling()
                .accessDeniedPage("/error/403")
                .and().sessionManagement().invalidSessionUrl("/session/timeout")
                .and().sessionManagement().sessionAuthenticationErrorUrl("/login");
        	
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    
}
