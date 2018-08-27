package com.demo.profiledemo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GraphQLControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper mapper;

    private void ok(final String json) throws Exception {
        perform(json)
                .andDo(print())
                .andExpect(status()
                        .isOk());
    }

    private ResultActions perform(final String json) throws Exception {
        return mockmvc.perform(post("/graphql")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void queryShouldWork() throws Exception {
        final String json = "{\"query\":\"{\\n userById(id:1) {\\n "
                + "  fullName\\n  jobTitle\\n  defaultLocale {\\n   locale\\n  }\\n "
                + "  timezone {\\n  \\tid\\n   name\\n  }\\n  emails {\\n   id\\n   email\\n   primaryEmail\\n  }\\n  "
                + " phones {\\n   id\\n   number\\n   primaryPhone\\n  }\\n }\\n}\", "
                + "\"variables\":\"\",\"operationName\":null}";
        ok(json);
    }

    @Test
    public void createMailShouldWork() throws Exception {
        final String mutation = "{\\n createEmail(newEmail:\\n  {email:\\\"abc@abc.com\\\",\\n   notificationEnabled:true,\\n   user:{id:1}\\n  }\\n ){\\n  id\\n  email\\n  primaryEmail\\n }\\n}";
        final String json = String.format("{\"query\":\"%s\",\"variables\":\"\",\"operationName\":null}", mutation);
        ok(json);
    }
}
