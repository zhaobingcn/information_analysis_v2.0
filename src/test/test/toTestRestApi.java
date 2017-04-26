import com.isa.analysis.config.Neo4jConfig;
import com.isa.analysis.sdn.entity.Author;
import com.isa.analysis.sdn.repository.AuthorRepository;
import com.isa.analysis.sdn.repository.Neo4jTemplateRepository;
import com.isa.analysis.service.RestApiServiceTest;
import com.isa.analysis.service.runtime.impl.Scheduler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * Created by hexu on 2016/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Neo4jConfig.class)
public class toTestRestApi {

    @Autowired
    AuthorRepository authorRepository;

//    @Autowired
//    Neo4jTemplateRepository neo4jTemplateRepository;

    @Test
    @DirtiesContext
    public void testrestapi(){
        Author a = authorRepository.findById(64l);
        System.out.println(a.getName());

//        neo4jTemplateRepository.getCountOfEntities(Author.class);
    }
}
