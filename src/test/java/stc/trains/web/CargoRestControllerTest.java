package stc.trains.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stc.trains.CargoTestData;
import stc.trains.model.Cargo;
import stc.trains.service.CargoService;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stc.trains.CargoTestData.*;

class CargoRestControllerTest extends BaseControllerTest {

    @Autowired
    private CargoService service;

    private static String URL = "/api/cargo";

    @Test
    @WithUserDetails(value = "test")
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + CARGO_1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(cargo_1)));
    }

    @Test
    @WithUserDetails(value = "test")
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(CargoTestData.all())));
    }

    @Test
    @WithUserDetails(value = "test")
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL + "/" + CARGO_3_ID))
                .andExpect(status().isOk())
                .andDo(print());
        assertThrows(NoSuchElementException.class, () -> service.get(CARGO_3_ID));
    }

    @Test
    @WithUserDetails(value = "test")
    void create() throws Exception {
        Cargo newCargo = cargo_4;
        ResultActions action = perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(cargo_4)))
                .andExpect(status().isOk());
        newCargo.setId(CARGO_4_ID);
        action.andExpect(content().json(mapper.writeValueAsString(newCargo)));
    }

    @Test
    @WithUserDetails(value = "test")
    void update() throws Exception {
        Cargo updated = getUpdated();
        perform(MockMvcRequestBuilders.patch(URL+"/"+CARGO_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isOk());
        assertEquals(mapper.writeValueAsString(updated),mapper.writeValueAsString(service.get(CARGO_1_ID)));
    }
}