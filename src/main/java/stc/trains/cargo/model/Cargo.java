package stc.trains.cargo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stc.trains.model.NamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cargo", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "code"}, name = "uniq_cargo_name_code")})

public class Cargo extends NamedEntity {
    @Column(name = "code", columnDefinition = "VARCHAR")
    private String code;

    public Cargo(Integer id, String name, String code)
    {
        super(id,name);
        this.code = code;
    }


}
