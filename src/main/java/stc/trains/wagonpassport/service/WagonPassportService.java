package stc.trains.wagonpassport.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.wagonpassport.model.WagonPassport;
import stc.trains.wagonpassport.repository.WagonPassportRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class WagonPassportService {

    private WagonPassportRepository wagonPassportRepository;

    public WagonPassport get(int id) {
        return wagonPassportRepository.findById(id).orElseThrow();
    }

    public WagonPassport create(WagonPassport wagonPassport) {
        return wagonPassportRepository.save(wagonPassport);
    }

    public void update(WagonPassport wagonPassport) {
        wagonPassportRepository.save(wagonPassport);
    }

    public void delete(int id) {
        wagonPassportRepository.deleteExisted(id);
    }

    public List<WagonPassport> getAll()
    {
        return wagonPassportRepository.findAll();
    }
}
