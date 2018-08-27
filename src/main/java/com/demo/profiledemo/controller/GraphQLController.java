package com.demo.profiledemo.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.metadata.strategy.query.AnnotatedResolverBuilder;
import io.leangen.graphql.metadata.strategy.query.PublicResolverBuilder;
import io.leangen.graphql.metadata.strategy.value.jackson.JacksonValueMapperFactory;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.demo.profiledemo.service.EmailService;
import com.demo.profiledemo.service.LocaleService;
import com.demo.profiledemo.service.PhoneService;
import com.demo.profiledemo.service.TimezoneServie;
import com.demo.profiledemo.service.UserService;

@RestController
@Slf4j
public class GraphQLController {

    private final GraphQL graphQL;

    @Autowired
    public GraphQLController(UserService userService,
        PhoneService phoneService,
        EmailService emailService,
        LocaleService localeService,
        TimezoneServie timezoneServie) {
        GraphQLSchema schema = new GraphQLSchemaGenerator()
            .withResolverBuilders(
                new AnnotatedResolverBuilder(),
                new PublicResolverBuilder("profiledemo"))
            .withOperationsFromSingleton(userService)
            .withOperationsFromSingleton(emailService)
            .withOperationsFromSingleton(phoneService)
            .withOperationsFromSingleton(localeService)
            .withOperationsFromSingleton(timezoneServie)
            .withValueMapperFactory(new JacksonValueMapperFactory())
            .generate();
        graphQL = GraphQL.newGraphQL(schema).build();
        log.info("GraphQL schema generated");
    }

    @PostMapping(value = "/graphql", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Map<String, Object> indexFromAnnotated(@RequestBody Map<String, String> request, HttpServletRequest raw) {
        ExecutionResult executionResult = graphQL.execute(ExecutionInput.newExecutionInput()
            .query(request.get("query"))
            .operationName(request.get("operationName"))
            .context(raw)
            .build());
        return executionResult.toSpecification();
    }
}
