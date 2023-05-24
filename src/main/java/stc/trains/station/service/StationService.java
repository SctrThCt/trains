package stc.trains.station.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stc.trains.station.model.Station;
import stc.trains.track.model.Track;
import stc.trains.util.ValidationUtil;
import stc.trains.waybill.model.Waybill;
import stc.trains.station.repository.StationRepository;
import stc.trains.waybill.repository.WaybillRepository;

import java.util.Comparator;
import java.util.List;

import static stc.trains.util.ValidationUtil.checkNew;

@RequiredArgsConstructor
@Service
@Slf4j
public class StationService {
    final private StationRepository stationRepository;
    final private WaybillRepository waybillRepository;

    public Station get(int id) {
        return stationRepository.findById(id).orElseThrow();
    }

    public Station create(Station station) {
        checkNew(station);
        return stationRepository.save(station);
    }

    public void update(Station station) {
        stationRepository.save(station);
    }

    public void delete(int id) {
        ValidationUtil.checkModification(stationRepository.delete(id),id);
    }

    public List<Station> getAll() {
        return stationRepository.findAll();
    }

    @Transactional
    public List<Waybill> acceptWagons(int stationId, int trackNumber, List<Waybill> wagons) {
        Station station = get(stationId);
        Track track = station.getTracks().get(trackNumber - 1);
        List<Waybill> wagonsOnTrack = track.getWagons();
        Integer wagonNumber = wagonsOnTrack.isEmpty() ? 0 : wagonsOnTrack.size() - 1;
        for (Waybill w : wagons) {
            w.setOrderNumber(wagonNumber++);
            w.setTrack(track);
            wagonsOnTrack.add(w);
        }
        return waybillRepository.saveAll(wagonsOnTrack);
    }

    @Transactional
    public void moveWagonsToHead(int stationId, int trackNumber, List<Waybill> wagons) {
        Station station = get(stationId);
        Track track = station.getTracks().get(trackNumber - 1);
        List<Waybill> wagonsOnTrack = track.getWagons();
        wagons.addAll(wagonsOnTrack.stream()
                .sorted(Comparator.comparingInt(Waybill::getOrderNumber))
                .toList());
        track.setWagons(wagons);
        waybillRepository.saveAll(wagonsOnTrack);
    }

    @Transactional
    public void moveWagonsToTail(int stationId, int trackNumber, List<Waybill> wagons) {
        acceptWagons(stationId, trackNumber, wagons);
    }

    @Transactional
    public void dispatchWagon(int stationId, int trackNumber) {
        Station station = get(stationId);
        Track track = station.getTracks().get(trackNumber);
        List<Waybill> wagonsOnTrack = track.getWagons();
        Waybill wagonToDispatch = wagonsOnTrack.get(0);
        wagonToDispatch.setTrack(null);
        waybillRepository.save(wagonToDispatch);
    }

}
