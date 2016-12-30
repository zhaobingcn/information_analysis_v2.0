import com.isa.analysis.config.Neo4jConfig;
import com.isa.analysis.config.TestNeo4jConfig;
import com.isa.analysis.service.TestRestApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by hexu on 2016/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestNeo4jConfig.class)
@ActiveProfiles(profiles = "test")
public class toTestRestApi {

    @Autowired
    private TestRestApi testRestApi;

    @Test
    @DirtiesContext
    public void testrestapi(){
        testRestApi.test();
    }
}
