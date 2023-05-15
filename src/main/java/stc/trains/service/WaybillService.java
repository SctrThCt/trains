package stc.trains.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.model.Cargo;
import stc.trains.model.Track;
import stc.trains.model.Waybill;
import stc.trains.repository.TrackRepository;
import stc.trains.repository.WaybillRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class WaybillService {
    private WaybillRepository waybillRepository;

    public Waybill get(int id) {
        return waybillRepository.findById(id).orElseThrow();
    }

    public Waybill create(Waybill waybill) {
        return waybillRepository.save(waybill);
    }

    public void update(Waybill track) {
        waybillRepository.save(track);
    }

    public void delete(int id) {
        waybillRepository.deleteExisted(id);
    }

    public List<Waybill> getAll()
    {
        return waybillRepository.findAll();
    }
}
