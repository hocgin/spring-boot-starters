package in.hocg.boot.message.core.transactional;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;

/**
 * Created by hocgin on 2020/7/20.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class TransactionalMessage {
    private String groupSn;
    private Map<String, Object> headers = Collections.emptyMap();
    private String destination;
    private String payload;
    private LocalDateTime preparedAt;

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setPayload(Object payload) {
        this.payload = JSONUtil.toJsonStr(payload);
    }

    public TransactionalMessage() {
        this.preparedAt = LocalDateTime.now();
    }

}