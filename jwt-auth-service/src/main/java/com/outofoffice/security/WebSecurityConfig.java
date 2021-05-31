package com.outofoffice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@CrossOrigin
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();
     http
     .cors()
     .and()
    .headers()
    .contentSecurityPolicy("script-src 'self'");

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers("/users/signin").permitAll()
        .antMatchers("/users/signup").permitAll()
        .antMatchers("/h2-console/**/**").permitAll()
        .antMatchers(HttpMethod.PATCH,"/users/{employee}").permitAll()
        .antMatchers(HttpMethod.GET,"/users/{username}").hasRole("ADMIN")
        
        .antMatchers("/users/username").permitAll()
        /////HOLIDAY-MS
        .antMatchers(HttpMethod.POST,"/holiday/holiday/{holidayTypeID}").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH,"/holiday/holiday/{holidayTypeID}/{employeeID}/{firstAndLastName}").hasRole("CLIENT")
        .antMatchers(HttpMethod.PATCH,"/holiday/holiday/{holidayId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH,"/holiday/holidayupdate/{employeeId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE,"/holiday/holiday/{holidayId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET,"/holiday/getlistofemployees/{holidayTypeId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET,"/holiday/getlistofholidays").permitAll()
        .antMatchers(HttpMethod.GET,"/holiday/holiday/{startDate}/{daysNum}").permitAll()
        .antMatchers(HttpMethod.POST,"/holiday/holidayType").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH,"/holiday/holidayType/{id}").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE,"/holiday/holidayType/{id}").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET,"/holiday/listHolidayTypes").permitAll()
        
        
        //////NOTIFICATION-MS
        .antMatchers(HttpMethod.GET, "/notification/notification/all_notification_types").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/notification/notificationsType").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/notification/notification_type/{notification_typeId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/notification/notification_type/{notification_typeId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/notification/notifications/{holidayTypeId}").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/notification/all_notifications").permitAll()
        .antMatchers(HttpMethod.GET, "/notification/notifications/{employeeId}").permitAll()
        
        
        //////LEAVEREQUEST-MS
        .antMatchers(HttpMethod.POST, "/leaverequest/request").hasRole("CLIENT")
        .antMatchers(HttpMethod.PATCH, "/leaverequest/request").hasRole("CLIENT")
        .antMatchers(HttpMethod.PATCH, "/leaverequest/request/{id}").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/leaverequest/request/{id}").hasRole("CLIENT")
       // .antMatchers(HttpMethod.GET).hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/leaverequest/request/{id}").hasRole("ADMIN")
        
        
        //////EMPLOYEE-MS
        .antMatchers(HttpMethod.POST, "/employee/employee").hasRole("ADMIN")
        .antMatchers(HttpMethod.PATCH, "/employee/employee").hasRole("ADMIN")
        .antMatchers(HttpMethod.DELETE, "/employee/employee").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/employee/employee").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/employee/employee/username").permitAll()
        .antMatchers(HttpMethod.GET, "/employee/allemployees").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/employee/employee/{id}").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/employee/employee/{id}/{days}").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/employee/getAllEmployeesNames").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/employee/getAllEmployeesByIds").hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/employee/department").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/employee/getAllDepartments").permitAll()
        .antMatchers(HttpMethod.DELETE, "/employee/deleteDepartment/{id}").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/employee/changeDepartment/{id}").hasRole("ADMIN")
        
        .anyRequest().authenticated();
    	

    	
   // http.exceptionHandling().accessDeniedPage("/login");
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs")
        .antMatchers("/swagger-resources/**")
        .antMatchers("/swagger-ui.html")
        .antMatchers("/configuration/**")
        .antMatchers("/webjars/**")//
        .antMatchers("/public")
        .and()
        .ignoring()
        .antMatchers("/h2-console/**/**");;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
