package stc.trains;

import stc.trains.model.Station;

import java.util.List;
import java.util.Map;

import static stc.trains.TrackTestData.*;

public class StationTestData {
    public static Integer STATION_1_ID = 1;
    public static Integer STATION_2_ID = 2;
    public static Integer STATION_3_ID = 3;
    public static Station station_1 = new Station(STATION_1_ID, "Москва", Map.of(track_1.getTrackNumber(),track_1, track_2.getTrackNumber(),track_2, track_3.getTrackNumber(),track_3, track_4.getTrackNumber(),track_4));
    public static Station station_2 = new Station(STATION_2_ID, "Екатеринбург", Map.of(track_5.getTrackNumber(),track_5,track_6.getTrackNumber(),track_6));
    public static Station station_3 = new Station(null, "Кукуево", Map.of(track_8.getTrackNumber(),track_8));

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
