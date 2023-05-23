package stc.trains.user.repository;

import org.springframework.transaction.annotation.Transactional;
import stc.trains.repository.BaseRepository;
import stc.trains.user.model.User;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends BaseRepository<User> {
    Optional<User> getByName(String name);
}