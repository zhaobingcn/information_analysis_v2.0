package com.isa.analysis.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by hexu on 2016/12/30.
 */
@Configuration
@Import(RepositoryRestMvcConfiguration.class)
//启动类的@SpringBootApplication会自动扫描同级包以及子包，所以下面的@ComponentScan不加应该没关系
//@ComponentScan("com.isa.analysis.sdn.repository")
@EnableNeo4jRepositories("com.isa.analysis.sdn.repository")
@EnableTransactionManagement
@Profile({"embedded", "test"})
public class TestNeo4jConfig extends Neo4jConfiguration {
    /**
     * 嵌入式连接
     *
     * @return
     */
    @Bean
    public org.neo4j.ogm.config.Configuration getEmbeddedConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver");
        return config;
    }

    @Bean
    public SessionFactory getSessionFactory() {
        /**
         * 如果不指定节点映射的java bean路径，保存时会报如下警告，导致无法将节点插入Neo4j中
         * ... is not an instance of a persistable class
         */
        return new SessionFactory(getEmbeddedConfiguration(), "com.isa.analysis.sdn.entity");
    }
}