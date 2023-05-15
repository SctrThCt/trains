package stc.trains.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stc.trains.model.Station;

import java.util.Optional;

public interface StationRepository extends BaseRepository<Station>{

    @Modifying
    @EntityGraph(attributePaths = {"tracks"})
    Optional<Station> findById(int id);

}
