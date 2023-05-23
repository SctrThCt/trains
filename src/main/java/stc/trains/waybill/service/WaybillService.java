package stc.trains.waybill.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.waybill.model.Waybill;
import stc.trains.waybill.repository.WaybillRepository;

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
