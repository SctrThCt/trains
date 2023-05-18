package stc.trains.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "station",uniqueConstraints = {@UniqueConstraint(columnNames = {"name"},name = "uniq_station_name")})
@Getter
@Setter
@NoArgsConstructor
public class Station extends NamedEntity{

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "station")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonManagedReference
    @MapKey(name = "trackNumber")
    Map<Integer,Track> tracks;

    public Station(Integer id, String name, Map<Integer,Track> tracks)
    {
        super(id, name);
        this.tracks=tracks;
    }
}
