package com.boot.youzan.cxf.config;

import com.boot.youzan.cxf.as.UserService;
import com.boot.youzan.cxf.as.UserServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    @Bean
            /*("cxfServletRegistration")*/
    public ServletRegistrationBean cxfServletRegistration(){
        return new ServletRegistrationBean(new CXFServlet(),"/service/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus()
    {
        return  new SpringBus();
    }

    @Bean
    public UserService userService()
    {
        return  new UserServiceImpl();
    }

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), userService());
        endpoint.publish("/user");
        return endpoint;
    }
}