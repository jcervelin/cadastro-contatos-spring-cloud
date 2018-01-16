package br.com.juliano.springcloud.zuul;

import java.net.MalformedURLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.io.ClassPathResource;

@EnableZuulProxy
@SpringBootApplication
public class SpringCloudZuulServerApplication {

    public static void main(String[] args) throws MalformedURLException {

        Object[] sources = {SpringCloudZuulServerApplication.class, new ClassPathResource("groovy/ExampleSurgicalDebugFilterBean.groovy")};
        SpringApplication.run(sources, args);

    }

}
