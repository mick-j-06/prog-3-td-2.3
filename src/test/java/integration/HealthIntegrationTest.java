package integration;

import app.foot.FootApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
public class HealthIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void read_ping_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/ping"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse();
        String actual = response.getContentAsString();

        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());
        Assertions.assertEquals("pong", actual);
    }
}
