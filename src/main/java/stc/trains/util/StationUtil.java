package stc.trains.util;

import stc.trains.model.Station;
import stc.trains.to.StationTo;

import java.util.List;
import java.util.stream.Collectors;

public class StationUtil {

    public static List<StationTo> getTos(List<Station> stations)
    {
        return stations.stream().map(e->new StationTo(e)).collect(Collectors.toList());
    }
}
