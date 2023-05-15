package stc.trains.web;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import stc.trains.model.Cargo;
import stc.trains.service.CargoService;

import java.util.List;

import static stc.trains.web.CargoRestController.REST_URL;
import static stc.trains.util.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(value = REST_URL)
@AllArgsConstructor
public class CargoRestController {
    public static final String REST_URL = "/api/cargo";

    private final CargoService cargoService;

    @GetMapping("/{id}")
    public Cargo get(@PathVariable int id)
    {
        return cargoService.get(id);
    }

    @GetMapping
    public List<Cargo> getAll()
    {
        return cargoService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id)
    {
        cargoService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Cargo create(@RequestBody Cargo cargo)
    {
        return cargoService.create(cargo);
    }

    @PatchMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Cargo cargo)
    {
        assureIdConsistent(cargo,id);
        cargoService.update(cargo);
    }
}
