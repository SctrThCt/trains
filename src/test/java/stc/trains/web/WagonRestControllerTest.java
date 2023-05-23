package stc.trains.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stc.trains.WagonPassportTestData;
import stc.trains.wagonpassport.model.WagonPassport;
import stc.trains.wagonpassport.service.WagonPassportService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stc.trains.WagonPassportTestData.*;
import static stc.trains.WagonPassportTestData.PASSPORT_1_ID;

public class WagonRestControllerTest extends BaseControllerTest{

    @Autowired
    private WagonPassportService service;

    private static String URL = "/api/passport";

    @Test
    @WithUserDetails(value = "test")
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + PASSPORT_1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(passport_1)));
    }

    @Test
    @WithUserDetails(value = "test")
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(WagonPassportTestData.all())));
    }

    @Test
    @WithUserDetails(value = "test")
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + "/" + PASSPORT_4_ID))
                .andExpect(status().isOk())
                .andDo(print());
        assertThrows(NoSuchElementException.class, () -> service.get(PASSPORT_4_ID));
    }

    @Test
    @WithUserDetails(value = "test")
    void create() throws Exception {
        WagonPassport newPassport = passport_5;
        ResultActions action = perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(passport_5)))
                .andExpect(status().isOk());
        newPassport.setId(PASSPORT_5_ID);
        action.andExpect(content().json(mapper.writeValueAsString(newPassport)));
    }

    @Test
    @WithUserDetails(value = "test")
    void update() throws Exception {
        WagonPassport updated = getUpdated();
        perform(MockMvcRequestBuilders.patch(URL+"/"+PASSPORT_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isOk());
        assertEquals(mapper.writeValueAsString(updated),mapper.writeValueAsString(service.get(PASSPORT_1_ID)));
    }
}
