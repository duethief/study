package controllers;

import configs.Spring2WebConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsNot.*;
import static org.hamcrest.core.IsNull.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * Created by InSeok on 2014-03-28.
 */
@SuppressWarnings("unused")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Spring2WebConfiguration.class)
@WebAppConfiguration
public class HelloControllerTest {
    private MockMvc mvc;
    @Autowired
    WebApplicationContext context;

    @Before
    public void setUp() throws Exception {
        mvc = webAppContextSetup(context).build();
    }

    @Test
    public void getHello() throws Exception {
        MvcResult result = mvc.perform(get("/hello").param("name", "ykyoon"))
                .andExpect(status().isOk())
                .andReturn();
        String message = (String) result.getModelAndView().getModel().get("message");
        assertThat(message.contains("ykyoon"), is(true));
        assertThat(message, is(not(nullValue())));
        System.out.println(message);
    }
}
