package stc.trains.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "waybill", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"order_number", "track_id"}, name = "uniq_order_on_track"),
        @UniqueConstraint(columnNames = {"wagon_passport_id", "id"}, name = "uniq_waybill_wagon_passport")})
public class Waybill extends BaseEntity {

    @Column(name = "order_number")
    private Integer orderNumber;

    @JoinColumn(name = "wagon_passport_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private WagonPassport wagonPassport;

    @JoinColumn(name = "cargo_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cargo cargo;

    @Column(name = "cargo_weight")
    private Integer cargoWeight;

    @Column(name = "full_wagon_weight")
    private Integer fullWagonWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonBackReference("track")
    private Track track;

    public Waybill(Integer id, Integer orderNumber, WagonPassport wagonPassport, Cargo cargo, Integer cargoWeight, Integer fullWagonWeight, Track track) {
        super(id);
        this.orderNumber = orderNumber;
        this.wagonPassport = wagonPassport;
        this.cargo = cargo;
        this.cargoWeight = cargoWeight;
        this.fullWagonWeight = fullWagonWeight;
        this.track = track;
    }
}
