package stc.trains.cargo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stc.trains.cargo.model.Cargo;
import stc.trains.cargo.repository.CargoRepository;
import stc.trains.util.ValidationUtil;

import java.util.List;
import java.util.NoSuchElementException;

import static stc.trains.util.ValidationUtil.assureIdConsistent;
import static stc.trains.util.ValidationUtil.checkNew;

@Service
@AllArgsConstructor
public class CargoService {

    private CargoRepository cargoRepository;

    public Cargo get(int id)
    {
        return cargoRepository.findById(id).orElseThrow(()->new NoSuchElementException("Cargo with id "+ id + "not found "));
    }

    public Cargo create(Cargo cargo)
    {
        checkNew(cargo);
        return cargoRepository.save(cargo);
    }
    public void update(Cargo cargo)
    {
        cargoRepository.save(cargo);
    }
    public void delete(int id)
    {
        ValidationUtil.checkModification(cargoRepository.delete(id),id);
    }
    public List<Cargo> getAll()
    {
        return cargoRepository.findAll();
    }
}
