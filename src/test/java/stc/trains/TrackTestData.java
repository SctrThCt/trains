package stc.trains;

import stc.trains.model.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static stc.trains.StationTestData.*;
import static stc.trains.WaybillTestData.*;

public class TrackTestData {
    public static Integer TRACK_1_ID = 1;
    public static Integer TRACK_2_ID = 2;
    public static Integer TRACK_3_ID = 3;
    public static Integer TRACK_4_ID = 4;
    public static Integer TRACK_5_ID = 5;
    public static Integer TRACK_6_ID = 6;
    public static Integer TRACK_7_ID = 7;
    public static Integer TRACK_8_ID = 8;

    public static Track track_1 = new Track(TRACK_1_ID,station_1,1L,0, WaybillTestData.all());
    public static Track track_2 = new Track(TRACK_2_ID,station_1,1L,1, new ArrayList<>());
    public static Track track_3 = new Track(TRACK_3_ID,station_1,1L,2, new ArrayList<>());
    public static Track track_4 = new Track(TRACK_4_ID,station_1,1L,3, new ArrayList<>());
    public static Track track_5 = new Track(TRACK_5_ID,station_2,2L,0, new ArrayList<>());
    public static Track track_6 = new Track(TRACK_6_ID,station_2,2L,1, new ArrayList<>());
    public static Track track_7 = new Track(TRACK_7_ID,station_1,1L,4, new ArrayList<>());
    public static Track track_8 = new Track(null,station_3,3L,0,new ArrayList<>());

    public static List<Track> all()
    {
        return List.of(track_1,track_2,track_3,track_4,track_5,track_6);
    }
    public static Map<Integer,Track> updatedForStation1()
    {
        return Map.of(track_1.getTrackNumber(),track_1, track_2.getTrackNumber(),track_2, track_3.getTrackNumber(),track_3, track_4.getTrackNumber(),track_4,track_7.getTrackNumber(),track_7);
    }

}
