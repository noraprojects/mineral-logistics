package be.kdg.prog6.family.testcontainers;


import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.family.domain.Person;
import be.kdg.prog6.family.domain.PiggyBank;
import be.kdg.prog6.family.ports.out.PiggyBankLoadPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
public class PiggyBankControllerIntegrationTest extends AbstractBaseTest {

    private static final String BOBBY_UUID = "ef01c728-ce36-46b5-a110-84f53fdd9668";
    private static final String ENDPOINT = "/money/20/person/" + BOBBY_UUID;

    /**
     * Making sure we are not actually need to spin up a AMQP server.
     */
    @MockBean
    private RabbitTemplate rabbitTemplate;
    @MockBean
    private SimpleMessageListenerContainer simpleMessageListenerContainer;

    @Autowired
    private PiggyBankLoadPort piggyBankLoadPort;

    @Autowired
    protected MockMvc mvc;


    @Test
    void receiveMoney() throws Exception {

        mvc.perform(post(ENDPOINT).with(jwt()
                                .authorities(new SimpleGrantedAuthority("user")))
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk());

        Optional<PiggyBank> piggyBank = piggyBankLoadPort.loadPiggyBankForOwner(new Person.PersonUUID(UUID.fromString(BOBBY_UUID)));

        Assertions.assertEquals(BigDecimal.valueOf(20).intValue(), piggyBank.get().getBalance().intValue());
        //for creating the piggybank what happens when he gets his first donation
        //for creating the activity.
        verify(rabbitTemplate, times(2)).convertAndSend(anyString(), anyString(), any(EventMessage.class));
    }

}