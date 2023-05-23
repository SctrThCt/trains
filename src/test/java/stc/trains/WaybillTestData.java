package stc.trains;

import stc.trains.waybill.model.Waybill;

import java.util.List;

import static stc.trains.WagonPassportTestData.*;
import static stc.trains.CargoTestData.*;
import static stc.trains.TrackTestData.*;

public class WaybillTestData {
    public static Integer WAYBILL_1_ID = 1;
    public static Integer WAYBILL_2_ID = 2;
    public static Waybill waybill_1 = new Waybill(WAYBILL_1_ID,1,passport_1,cargo_1,50000,73000,track_1);
    public static Waybill waybill_2 = new Waybill(WAYBILL_2_ID,2,passport_3,cargo_2,10000,31400,track_1);

    public static Waybill getDispatched()
    {
        Waybill out = new Waybill();
        out.setId(waybill_1.getId());
        out.setWagonPassport(waybill_1.getWagonPassport());
        out.setOrderNumber(waybill_1.getOrderNumber());
        out.setCargo(waybill_1.getCargo());
        out.setCargoWeight(waybill_1.getCargoWeight());
        out.setFullWagonWeight(waybill_1.getFullWagonWeight());
        out.setTrack(null);
        return out;
    }

    public static List<Waybill> all()
    {
        return List.of(waybill_1,waybill_2);
    }

    public static List<Waybill> movedToTrack2()
    {
        Waybill waybill = new Waybill();
        waybill.setId(waybill_2.getId());
        waybill.setWagonPassport(waybill_2.getWagonPassport());
        waybill.setOrderNumber(waybill_2.getOrderNumber());
        waybill.setCargo(waybill_2.getCargo());
        waybill.setCargoWeight(waybill_2.getCargoWeight());
        waybill.setFullWagonWeight(waybill_2.getFullWagonWeight());
        waybill.setTrack(track_2);
        return List.of(waybill);
    }

}
