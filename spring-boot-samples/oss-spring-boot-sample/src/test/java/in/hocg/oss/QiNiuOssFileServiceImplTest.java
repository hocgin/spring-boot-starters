package in.hocg.oss;

import in.hocg.boot.oss.autoconfigure.core.OssFileService;
import in.hocg.boot.test.AbstractSpringBootTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by hocgin on 2020/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ActiveProfiles("qiniu")
public class QiNiuOssFileServiceImplTest extends AbstractSpringBootTest {
    @Autowired
    OssFileService ossFileService;

//    @Test
//    public void testUpload() {
//        Assert.assertTrue(true);
//        File file = new File("/Users/hocgin/Downloads/1111.png");
//        String url = ossFileService.upload(file);
//        log.info("==> {}", url);
//    }
}
