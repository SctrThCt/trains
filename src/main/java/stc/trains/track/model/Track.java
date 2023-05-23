package stc.trains.track.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import stc.trains.model.BaseEntity;
import stc.trains.station.model.Station;
import stc.trains.waybill.model.Waybill;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "track", uniqueConstraints = {@UniqueConstraint(columnNames = {"station_id", "track_number"}, name = "uniq_station_track_number")})
public class Track extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private Station station;

    @Column(name = "station_id",insertable = false,updatable = false)
    Long stationId;
    @Column(name = "track_number")
    private Integer trackNumber;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "track")
    private List<Waybill> wagons;

    public Track(Integer id, Station station,Long stationId, Integer trackNumber, List<Waybill> wagons) {
        super(id);
        this.stationId=stationId;
        this.station = station;
        this.trackNumber = trackNumber;
        this.wagons = wagons;
    }
}
