package stc.trains;

import stc.trains.wagonpassport.model.WagonPassport;
import stc.trains.wagonpassport.model.WagonType;

import java.util.List;

public class WagonPassportTestData {
    public static Integer PASSPORT_1_ID = 1;
    public static Integer PASSPORT_2_ID = 2;
    public static Integer PASSPORT_3_ID = 3;
    public static Integer PASSPORT_4_ID = 4;
    public static Integer PASSPORT_5_ID = 5;

    public static WagonPassport passport_1 = new WagonPassport(PASSPORT_1_ID,1, WagonType.COVERED,23000,68000);
    public static WagonPassport passport_2 = new WagonPassport(PASSPORT_2_ID,2, WagonType.REFRIGERATOR,48000,36000);
    public static WagonPassport passport_3 = new WagonPassport(PASSPORT_3_ID,3, WagonType.PLATFORM,21400,71000);
    public static WagonPassport passport_4 = new WagonPassport(PASSPORT_4_ID,4, WagonType.PLATFORM,21400,71000);
    public static WagonPassport passport_5 = new WagonPassport(null,5, WagonType.GONDOLA,20000,50000);

    public static List<WagonPassport> all()
    {
        return List.of(passport_1,passport_2,passport_3,passport_4);
    }
    public static WagonPassport getUpdated()
    {
        WagonPassport out = new WagonPassport();
        out.setId(PASSPORT_1_ID);
        out.setNumber(passport_5.getNumber());
        out.setCarryingWeight(passport_5.getCarryingWeight());
        out.setCarWeight(passport_5.getCarWeight());
        out.setType(passport_5.getType());
        return out;
    }
}
