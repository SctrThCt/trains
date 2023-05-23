package stc.trains.track.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import stc.trains.track.model.Track;
import stc.trains.track.service.TrackService;

import java.util.List;

import static stc.trains.util.ValidationUtil.assureIdConsistent;
import static stc.trains.track.controller.TrackRestController.REST_URL;

@RestController
@RequestMapping(value = REST_URL)
@AllArgsConstructor
public class TrackRestController {
    public static final String REST_URL = "/api/track";

    private final TrackService trackService;

    @GetMapping("/{id}")
    public Track get(@PathVariable int id) {
        return trackService.get(id);
    }

    @GetMapping
    public List<Track> getAll() {
        return trackService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        trackService.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Track create(@RequestBody Track track) {
        return trackService.create(track);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable int id, @RequestBody Track track) {
        assureIdConsistent(track, id);
        trackService.update(track);
    }
}
