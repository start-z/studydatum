import com.zhou.othWorkApplication;
import com.zhou.service.OtherWorkService;
import com.zhou.utils.ReOfferA;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhouhelong
 * @creat 2022-07-13 20:23
 * @description:
 */
@SpringBootTest(classes = othWorkApplication.class)
@RunWith(SpringRunner.class)
public class OtherWorkTest {
    @Autowired
      private OtherWorkService otherWorkService;

    @Autowired
    private ReOfferA reOfferA;

    @Test
    public void getDDL(){
        reOfferA.getDDL("2025-2");
    }
    @Test
    public void  test(){
        otherWorkService.hello();
        String cmd="ping  www.baidu.com";
        try {
            Process exec = Runtime.getRuntime().exec(cmd);
            BufferedReader gbk = new BufferedReader(new InputStreamReader(exec.getInputStream(), "GBK"));
            StringBuffer buffer = new StringBuffer();
            gbk.lines().forEach(item->{
                buffer.append(item);
            });
            exec.destroy();
            System.out.println(buffer);
        } catch (IOException e) {
            System.out.println("测试异常");
        }

    }
}
