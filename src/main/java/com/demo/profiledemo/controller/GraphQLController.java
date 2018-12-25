package com.demo.profiledemo.controller;

import com.demo.profiledemo.service.EmailService;
import com.demo.profiledemo.service.LocaleService;
import com.demo.profiledemo.service.PhoneService;
import com.demo.profiledemo.service.TimezoneServie;
import com.demo.profiledemo.service.UserService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/graphql")
    public ResponseEntity<Map<String, Object>> handleRequest(@RequestBody Map<String, String> request,
            HttpServletRequest raw) {
        ExecutionInput input = ExecutionInput.newExecutionInput()
                .query(request.get("query"))
                .operationName(request.get("operationName"))
                .context(raw)
                .build();
        log.info("handleRequest:{}", input);
        ExecutionResult executionResult = graphQL.execute(input);
        return ResponseEntity.ok(executionResult.toSpecification());
    }
}
