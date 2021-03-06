package in.hocg.boot.excel.autoconfiguration.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by hocgin on 2021/6/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ExcelUtils {
    /**
     * 自动翻页写入所有数据
     *
     * @param paging   分页查询
     * @param ro       请求参数
     * @param consumer 消费函数
     * @param <R>      _
     */
    public <R extends PageRo> void write(Function<R, IPage<?>> paging, R ro, Consumer<List<?>> consumer) {
        ro.setPage(1);
        ro.setIsCount(false);
        Integer size = ro.getSize();
        List<?> records;
        do {
            records = paging.apply(ro).getRecords();
            consumer.accept(records);
            ro.setPage(ro.getPage() + 1);
        } while (records.size() < size);
    }
}
