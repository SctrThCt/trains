package stc.trains.waybill.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.util.ValidationUtil;
import stc.trains.waybill.model.Waybill;
import stc.trains.waybill.repository.WaybillRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static stc.trains.util.ValidationUtil.checkNew;

@RequiredArgsConstructor
@Service
public class WaybillService {
    private final WaybillRepository waybillRepository;

    public Waybill get(int id) {
        return waybillRepository.findById(id).orElseThrow(()->new NoSuchElementException("Waybill with id "+id+" not found"));
    }

    public Waybill create(Waybill waybill) {
        checkNew(waybill);
        return waybillRepository.save(waybill);
    }

    public Waybill update(Waybill waybill) {
        return waybillRepository.save(waybill);
    }

    public void delete(int id) {
        ValidationUtil.checkModification(waybillRepository.delete(id),id);
    }

    public List<Waybill> getAll()
    {
        return waybillRepository.findAll();
    }
}
