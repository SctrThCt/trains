package stc.trains.track.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.track.model.Track;
import stc.trains.track.repository.TrackRepository;
import stc.trains.station.service.StationService;

import java.util.List;

@AllArgsConstructor
@Service
public class TrackService {
    private TrackRepository trackRepository;
    private StationService stationService;

    public Track get(int id) {
        return trackRepository.findById(id).orElseThrow();
    }

    public Track create(Track track) {
        if(track.getStation()==null)
        {
            track.setStation(stationService.get(Math.toIntExact(track.getStationId())));
        }
        return trackRepository.save(track);
    }

    public void update(Track track) {
        trackRepository.save(track);
    }

    public void delete(int id) {
        trackRepository.deleteExisted(id);
    }

    public List<Track> getAll()
    {
        return trackRepository.findAll();
    }
}
