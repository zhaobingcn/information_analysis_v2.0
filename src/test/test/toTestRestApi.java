import com.isa.analysis.config.Neo4jConfig;
import com.isa.analysis.service.TestRestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hexu on 2016/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jConfig.class)
public class toTestRestApi {

    @Autowired
    private TestRestApi testRestApi;

    @Test
    public void testrestapi(){
        testRestApi.test();
    }
}
