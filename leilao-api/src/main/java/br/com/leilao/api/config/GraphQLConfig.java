package br.com.leilao.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQLConfig {

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer(DateTimeScalar dateTimeScalar) {
        return wiringBuilder -> wiringBuilder
                .scalar(dateTimeScalar.dateTime());
    }
}