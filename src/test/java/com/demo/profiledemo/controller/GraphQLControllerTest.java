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
    private static final String QUERY_JSON_FORMAT = "{\"query\":\"%s\",\"variables\":\"\",\"operationName\":null}";

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
        final String query = "{\\n"
                + "  listAllEnabledLocales {\\n"
                + "    id\\n"
                + "    name\\n"
                + "    locale\\n"
                + "  }\\n"
                + "  listAllTimezones{\\n"
                + "    id\\n"
                + "    name\\n"
                + "    \\n"
                + "  }\\n"
                + " userById(id:1) {\\n"
                + "  fullName\\n"
                + "  jobTitle\\n"
                + "  defaultLocale {\\n"
                + "   locale\\n"
                + "  }\\n"
                + "  timezone {\\n"
                + "   id\\n"
                + "   name\\n"
                + "  }\\n"
                + "  emails {\\n"
                + "   id\\n"
                + "   email\\n"
                + "   primaryEmail\\n"
                + "  }\\n"
                + "  phones {\\n"
                + "   id\\n"
                + "   number\\n"
                + "   primaryPhone\\n"
                + "  }\\n"
                + " }\\n"
                + "}\\n";
        final String json = String.format(QUERY_JSON_FORMAT, query);
        ok(json);
    }

    @Test
    public void createMailShouldWork() throws Exception {
        final String mutation = "{\\n createEmail(newEmail:\\n  {email:\\\"abc@abc.com\\\",\\n   notificationEnabled:true,\\n   user:{id:1}\\n  }\\n ){\\n  id\\n  email\\n  primaryEmail\\n }\\n}";
        final String json = String.format(QUERY_JSON_FORMAT, mutation);
        ok(json);
    }
}
