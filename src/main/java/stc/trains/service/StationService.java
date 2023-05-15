package stc.trains.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stc.trains.model.Station;
import stc.trains.model.Track;
import stc.trains.model.Waybill;
import stc.trains.repository.StationRepository;
import stc.trains.repository.WaybillRepository;
import stc.trains.to.StationTo;
import stc.trains.util.StationUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class StationService {
    private StationRepository stationRepository;
    private WaybillRepository waybillRepository;

    public Station get(int id) {
        return stationRepository.findById(id).orElseThrow();
    }

    public StationTo getTo(int id)
    {
        return new StationTo(get(id));
    }

    public Station create(Station station) {
        return stationRepository.save(station);
    }

    public void update(Station station) {
        stationRepository.save(station);
    }

    public void delete(int id) {
        stationRepository.deleteExisted(id);
    }

    public List<Station> getAll()
    {
        return stationRepository.findAll();
    }

    @Transactional
    public List<Waybill> acceptWagons(int stationId, int trackNumber, List<Waybill> wagons)
    {
        Station station = get(stationId);
        Track track = station.getTracks().get(trackNumber-1);
        List<Waybill> wagonsOnTrack = track.getWagons();
        Integer wagonNumber = wagonsOnTrack.isEmpty() ? 0 : wagonsOnTrack.size()-1;
        for (Waybill w:wagons)
        {
            w.setOrderNumber(wagonNumber++);
            w.setTrack(track);
            wagonsOnTrack.add(w);
        }
        return waybillRepository.saveAll(wagonsOnTrack);
    }

    @Transactional
    public List<Waybill> moveWagonsToHead(int stationId, int trackNumber, List<Waybill> wagons)
    {
        Station station = get(stationId);
        Track track = station.getTracks().get(trackNumber-1);
        List<Waybill> wagonsOnTrack = track.getWagons();
        wagons.addAll(wagonsOnTrack.stream()
                .sorted(Comparator.comparingInt(Waybill::getOrderNumber))
                .collect(Collectors.toList()));
        track.setWagons(wagons);
        return waybillRepository.saveAll(wagonsOnTrack);
    }
    @Transactional
    public List<Waybill> moveWagonsToTail(int stationId, int trackNumber, List<Waybill> wagons)
    {
        return acceptWagons(stationId,trackNumber,wagons);
    }

    @Transactional
    public void dispatchWagon(int stationId, int trackNumber)
    {
        log.info("getting station");
        Station station = get(stationId);
        log.info("getting track");
        Track track = station.getTracks().get(trackNumber);
        log.info("getting wagons");
        List<Waybill> wagonsOnTrack = track.getWagons();
        log.info("getting wagon");
        Waybill wagonToDispatch = wagonsOnTrack.get(0);
        log.info("nulling track");
        wagonToDispatch.setTrack(null);
        log.info("returning wagon");
        waybillRepository.save(wagonToDispatch);
    }

}
