package stc.trains.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import stc.trains.CargoTestData;
import stc.trains.StationTestData;
import stc.trains.TrackTestData;
import stc.trains.WaybillTestData;
import stc.trains.model.Cargo;
import stc.trains.model.Station;
import stc.trains.model.Waybill;
import stc.trains.service.CargoService;
import stc.trains.service.StationService;
import stc.trains.service.TrackService;
import stc.trains.service.WaybillService;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static stc.trains.StationTestData.*;

class StationRestControllerTest extends BaseControllerTest{

    @Autowired
    private StationService service;
    @Autowired
    private TrackService trackService;
    @Autowired
    private WaybillService waybillService;

    private static String URL = "/api/station";

    @Test
    @WithUserDetails(value = "test")
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + STATION_1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(station_1)));
    }

    @Test
    @WithUserDetails(value = "test")
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(StationTestData.all())));
    }

    @Test
    @WithUserDetails(value = "test")
    void create() throws Exception {
        Station newStation = station_3;
        ResultActions action = perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newStation)))
                .andExpect(status().isOk());
        newStation.setId(STATION_3_ID);
        action.andExpect(content().json(mapper.writeValueAsString(newStation)));
    }

    @Test
    @WithUserDetails(value = "test")
    void update() throws Exception {
        Station updated = getUpdated();
        trackService.create(TrackTestData.track_7);
        perform(MockMvcRequestBuilders.patch(URL+"/"+STATION_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(updated)))
                .andExpect(status().isOk());
        assertEquals(mapper.writeValueAsString(updated),mapper.writeValueAsString(service.get(STATION_1_ID)));
    }

    @Test
    @WithUserDetails(value = "test")
    void dispatch() throws Exception
    {
        perform(MockMvcRequestBuilders.post(URL+"/"+STATION_1_ID+"/track/0/dispatch"))
                .andExpect(status().isOk())
                .andDo(print());
        Waybill dispatched = waybillService.get(1);
        String expected = mapper.writeValueAsString(WaybillTestData.getDispatched());
        String actual = mapper.writeValueAsString(dispatched);
        assertEquals(expected,actual);
    }

    @Test
    @WithUserDetails(value = "test")
    void addToHead() throws Exception {
        perform(MockMvcRequestBuilders.post(URL+"/"+STATION_1_ID+"/move?track=2&head=true")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(List.of(WaybillTestData.waybill_2))))
                .andExpect(status().isOk())
                .andDo(print());
        Waybill moved = waybillService.get(2);
        String expected = mapper.writeValueAsString(WaybillTestData.movedToTrack2().get(0));
        String actual = mapper.writeValueAsString(moved);
        assertEquals(expected,actual);
    }
}