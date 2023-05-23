package stc.trains.wagonpassport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stc.trains.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "wagon_passport")
@NoArgsConstructor
@Getter
@Setter
@Entity

public class WagonPassport extends BaseEntity implements Serializable {
    @Column(name = "number")
    private Integer number;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private WagonType type;

    @Column(name = "wagon_weight")
    private Integer carWeight;

    @Column(name = "carrying_weight")
    private Integer carryingWeight;

    public WagonPassport(Integer id, Integer number, WagonType type, Integer carWeight, Integer carryingWeight) {
        super(id);
        this.number = number;
        this.type = type;
        this.carWeight = carWeight;
        this.carryingWeight = carryingWeight;
    }
}
