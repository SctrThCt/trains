package stc.trains.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import stc.trains.model.Station;
import stc.trains.model.Waybill;
import stc.trains.service.StationService;

import java.util.List;

import static stc.trains.util.ValidationUtil.assureIdConsistent;
import static stc.trains.web.StationRestController.REST_URL;

@RestController
@RequestMapping(value = REST_URL)
@AllArgsConstructor
public class StationRestController {
    public static final String REST_URL = "/api/station";

    private final StationService stationService;

    @GetMapping("/{id}")
    public Station get(@PathVariable int id) {
        return stationService.get(id);
    }

    @GetMapping
    public List<Station> getAll() {
        return stationService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        stationService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Station create(@RequestBody Station station) {
        return stationService.create(station);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Station station) {
        assureIdConsistent(station, id);
        stationService.update(station);
    }

    @PostMapping("/{stationId}/accept")
    public List<Waybill> acceptWagons(@PathVariable int stationId, @RequestParam int track, @RequestBody List<Waybill> waybills) {
        return stationService.acceptWagons(stationId, track, waybills);
    }

    @PostMapping("/{stationId}/move")
    public void moveWagons(@PathVariable int stationId, @RequestParam int track, @RequestParam boolean head, @RequestBody List<Waybill> waybills) {
        if (head) {
            stationService.moveWagonsToHead(stationId, track, waybills);
        } else {
            stationService.moveWagonsToTail(stationId, track, waybills);
        }
    }

    @PostMapping("/{stationId}/track/{trackNumber}/dispatch")
    public void dispatchWagon(@PathVariable int stationId, @PathVariable int trackNumber) {
        stationService.dispatchWagon(stationId, trackNumber);
    }


}
