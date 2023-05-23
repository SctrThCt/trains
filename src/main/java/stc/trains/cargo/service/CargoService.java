package stc.trains.cargo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.cargo.model.Cargo;
import stc.trains.cargo.repository.CargoRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class CargoService {

    private CargoRepository cargoRepository;

    public Cargo get(int id)
    {
        return cargoRepository.findById(id).orElseThrow(()->new NoSuchElementException("Cargo with id "+ id + "not found"));
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
