package stc.trains.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.model.Cargo;
import stc.trains.repository.CargoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class CargoService {

    private CargoRepository cargoRepository;

    public Cargo get(int id)
    {
        return cargoRepository.findById(id).orElseThrow();
    }

    public Cargo create(Cargo cargo)
    {
        return cargoRepository.save(cargo);
    }
    public void update(Cargo cargo)
    {
        cargoRepository.save(cargo);
    }
    public void delete(int id)
    {
        cargoRepository.deleteExisted(id);
    }
    public List<Cargo> getAll()
    {
        return cargoRepository.findAll();
    }
}
