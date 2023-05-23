package stc.trains.wagonpassport.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import stc.trains.wagonpassport.model.WagonPassport;
import stc.trains.wagonpassport.service.WagonPassportService;

import java.util.List;

import static stc.trains.util.ValidationUtil.assureIdConsistent;
import static stc.trains.wagonpassport.controller.WagonPassportRestController.REST_URL;

@RestController
@RequestMapping(value = REST_URL)
@AllArgsConstructor
public class WagonPassportRestController {
    public static final String REST_URL = "/api/passport";

    private final WagonPassportService wagonPassportService;

    @GetMapping("/{id}")
    public WagonPassport get(@PathVariable int id) {
        return wagonPassportService.get(id);
    }

    @GetMapping
    public List<WagonPassport> getAll() {
        return wagonPassportService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        wagonPassportService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public WagonPassport create(@RequestBody WagonPassport passport) {
        return wagonPassportService.create(passport);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody WagonPassport passport) {
        assureIdConsistent(passport, id);
        wagonPassportService.update(passport);

    }
}
