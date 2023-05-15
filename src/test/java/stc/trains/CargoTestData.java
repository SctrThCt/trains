package stc.trains;

import stc.trains.model.Cargo;

import java.util.List;

public class CargoTestData {
    public static Integer CARGO_1_ID = 1;
    public static Integer CARGO_2_ID = 2;
    public static Integer CARGO_3_ID = 3;
    public static Integer CARGO_4_ID = 4;

    public static Cargo cargo_1 = new Cargo(CARGO_1_ID,"ПШЕНИЦА","011005");
    public static Cargo cargo_2 = new Cargo(CARGO_2_ID,"ЛЕСОМАТЕРИАЛЫ КРЕПЕЖНЫЕ","082000");
    public static Cargo cargo_3 = new Cargo(CARGO_3_ID,"АВТОМОБИЛИ И ИХ ЧАСТИ","380008");
    public static Cargo cargo_4 = new Cargo(null,"ЭТО Я САМ ПРИДУМАЛ","000000");

    public static Cargo getUpdated()
    {
        Cargo out = new Cargo();
        out.setId(CARGO_1_ID);
        out.setCode(cargo_4.getCode());
        out.setName(cargo_4.getName());
        return out;
    }
    public static List<Cargo> all()
    {
        return List.of(cargo_1,cargo_2,cargo_3);
    }
}
