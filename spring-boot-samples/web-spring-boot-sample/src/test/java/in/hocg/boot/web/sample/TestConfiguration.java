package in.hocg.boot.web.sample;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackageClasses = BootApplication.class)
@EnableAutoConfiguration
public class TestConfiguration {
}
