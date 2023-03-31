package com.example.demo.config;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;

@EnableCassandraRepositories
@Configuration
public class CassandraConfiguration {


    @Autowired
    Environment env;
    public @Bean CqlSession session(){
        return CqlSession.builder()
                .addContactPoint(new InetSocketAddress(env.getProperty("spring.data.cassandra.contact-points"),Integer.parseInt(env.getProperty("spring.data.cassandra.port"))))
                .withLocalDatacenter(env.getProperty("spring.data.cassandra.local-datacenter"))
                .withKeyspace(env.getProperty("spring.data.cassandra.keyspace-name"))
                .build();
    }



}
