package stc.trains.track.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.track.model.Track;
import stc.trains.track.repository.TrackRepository;
import stc.trains.station.service.StationService;
import stc.trains.util.ValidationUtil;

import java.util.List;
import java.util.NoSuchElementException;

import static stc.trains.util.ValidationUtil.checkNew;

@RequiredArgsConstructor
@Service
public class TrackService {
    private final TrackRepository trackRepository;
    private final StationService stationService;

    public Track get(int id) {
        return trackRepository.findById(id).orElseThrow(()->new NoSuchElementException("Track with id "+id+" not found"));
    }

    public Track create(Track track) {
        checkNew(track);
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
        ValidationUtil.checkModification(trackRepository.delete(id),id);
    }

    public List<Track> getAll()
    {
        return trackRepository.findAll();
    }
}
