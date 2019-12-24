package top.actim.spider;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderApplicationTests {

    @Value("${spider.cachePath}")
    String cachePath;

    @Test
    public void contextLoads() {
        System.out.println(cachePath);
    }

    @Test
    public void showCachePathVal() {
        System.out.println(cachePath);
    }

}
