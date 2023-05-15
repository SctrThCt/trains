package stc.trains.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import stc.trains.model.Waybill;
import stc.trains.service.WaybillService;

import java.util.List;

import static stc.trains.util.ValidationUtil.assureIdConsistent;
import static stc.trains.web.WaybillRestController.REST_URL;

@RestController
@RequestMapping(value = REST_URL)
@AllArgsConstructor
public class WaybillRestController {
    public static final String REST_URL = "/api/waybill";

    private final WaybillService waybillService;

    @GetMapping("/{id}")
    public Waybill get(@PathVariable int id) {
        return waybillService.get(id);
    }

    @GetMapping
    public List<Waybill> getAll() {
        return waybillService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        waybillService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Waybill create(@RequestBody Waybill waybill) {
        return waybillService.create(waybill);
    }

    @PostMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Waybill waybill) {
        assureIdConsistent(waybill, id);
        waybillService.update(waybill);
    }
}
