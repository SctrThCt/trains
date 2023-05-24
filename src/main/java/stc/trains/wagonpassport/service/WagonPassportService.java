package stc.trains.wagonpassport.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.util.ValidationUtil;
import stc.trains.wagonpassport.model.WagonPassport;
import stc.trains.wagonpassport.repository.WagonPassportRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static stc.trains.util.ValidationUtil.checkNew;

@RequiredArgsConstructor
@Service
public class WagonPassportService {

    private final WagonPassportRepository wagonPassportRepository;

    public WagonPassport get(int id) {
        return wagonPassportRepository.findById(id).orElseThrow(()->new NoSuchElementException("Passport with id "+id+" not found"));
    }

    public WagonPassport create(WagonPassport wagonPassport) {
        checkNew(wagonPassport);
        return wagonPassportRepository.save(wagonPassport);
    }

    public void update(WagonPassport wagonPassport) {
        wagonPassportRepository.save(wagonPassport);
    }

    public void delete(int id) {
        ValidationUtil.checkModification(wagonPassportRepository.delete(id),id);
    }

    public List<WagonPassport> getAll()
    {
        return wagonPassportRepository.findAll();
    }
}
