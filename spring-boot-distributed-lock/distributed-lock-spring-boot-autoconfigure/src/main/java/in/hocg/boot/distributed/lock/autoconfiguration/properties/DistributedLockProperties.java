package in.hocg.boot.distributed.lock.autoconfiguration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hocgin on 2020/8/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ConfigurationProperties(DistributedLockProperties.PREFIX)
public class DistributedLockProperties {
    public static final String PREFIX = "boot.distributed-lock";
    /**
     * 锁类型
     */
    private LockType type = LockType.Redisson;

    public enum LockType {
        Redis,
        Redisson;
    }
}
