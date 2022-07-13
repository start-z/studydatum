import com.zhou.annotation.Demo;
import com.zhou.aspect.DemoAspect;
import com.zhou.othWorkApplication;
import com.zhou.service.OtherWorkService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Test
    public void  test(){
        otherWorkService.hello();

    }

}
