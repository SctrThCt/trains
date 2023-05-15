package stc.trains;

import stc.trains.model.Station;
import stc.trains.to.StationTo;
import stc.trains.util.StationUtil;

import java.util.Collections;
import java.util.List;

import static stc.trains.TrackTestData.*;

public class StationTestData {
    public static Integer STATION_1_ID = 1;
    public static Integer STATION_2_ID = 2;
    public static Integer STATION_3_ID = 3;
    public static Station station_1 = new Station(STATION_1_ID, "Москва", List.of(track_1,track_2,track_3,track_4));
    public static Station station_2 = new Station(STATION_2_ID, "Екатеринбург", List.of(track_5,track_6));
    public static Station station_3 = new Station(null, "Кукуево", List.of(track_8));

    public static List<Station> all() {
        return List.of(station_1, station_2);
    }

    public static Station getUpdated() {
        Station out = new Station();
        out.setId(STATION_1_ID);
        out.setName(station_1.getName());
        out.setTracks(updatedForStation1());
        return out;
    }
}
