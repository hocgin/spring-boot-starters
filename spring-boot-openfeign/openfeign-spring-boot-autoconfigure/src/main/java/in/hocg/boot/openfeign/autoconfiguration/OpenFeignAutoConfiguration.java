package in.hocg.boot.openfeign.autoconfiguration;

import feign.codec.ErrorDecoder;
import in.hocg.boot.openfeign.autoconfiguration.decoder.ExceptionErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * Created by hocgin on 2020/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Configuration
@EnableCaching
@EnableConfigurationProperties({OpenFeignProperties.class})
@ConditionalOnClass(FeignRibbonClientAutoConfiguration.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OpenFeignAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ErrorDecoder errorDecoder() {
        return new ExceptionErrorDecoder();
    }
}