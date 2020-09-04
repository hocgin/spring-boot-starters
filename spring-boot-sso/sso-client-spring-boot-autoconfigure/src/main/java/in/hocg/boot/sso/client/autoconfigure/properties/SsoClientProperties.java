package in.hocg.boot.sso.client.autoconfigure.properties;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2019/6/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = SsoClientProperties.PREFIX)
public class SsoClientProperties {
    public static final String PREFIX = "boot.sso.client";
    private String loginProcessingUrl = "http://sso.hocgin.local:20000/login";
    private String logoutUrl = "/logout";
    private List<String> ignoreUrls = Collections.emptyList();
}
