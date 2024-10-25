package be.kdg.prog6.parkManagementTest.testcontainers;


import be.kdg.prog6.parkManagement.domain.Park;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class parkcontrollerintegrationtest extends AbstractBaseTest {

    private static final String createStand = "/park/createStand";
    @Autowired
    protected MockMvc mvc;
    @MockBean
    private RabbitTemplate rabbitTemplate;
    @MockBean
    private SimpleMessageListenerContainer simpleMessageListenerContainer;


    @Test
    void cantDeleteAStandThatDoesntExist() throws Exception {
        UUID uuid = UUID.randomUUID();
        Park.getInstance().addStand(uuid);

        //There is a Stand found and succesfully deleted;
        mvc.perform(delete("/park/stand/" + uuid).with(jwt()
                                .authorities(new SimpleGrantedAuthority("admin")))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());


        //There is a Stand found but already closed and succesfully delted;
        mvc.perform(delete("/park/stand/" + uuid).with(jwt()
                                .authorities(new SimpleGrantedAuthority("admin")))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The stand with UUID " + uuid + " is already closed and cannot be removed."));

    }


    @Test
    void cantDeleteSomehtingThatDoesntExist() throws Exception {
        UUID uuid = UUID.randomUUID();


        mvc.perform(delete("/park/stand/" + uuid).with(jwt()
                                .authorities(new SimpleGrantedAuthority("user")))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("The stand with UUID " + uuid + " does not exist."));
    }


}
