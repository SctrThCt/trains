package stc.trains.to;

import lombok.Data;
import stc.trains.model.Station;
import stc.trains.model.Track;
import stc.trains.model.Waybill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class StationTo {

    private int id;

    private String name;

    private Map<Integer, List<Waybill>> wagonsOnTrack;

    public StationTo(Station station)
    {
        this.id=station.getId();
        this.name = station.getName();
        wagonsOnTrack = new HashMap<>();
        for(Track t: station.getTracks())
        {
            Integer track = t.getTrackNumber();
            List<Waybill> wagons = t.getWagons();
            wagonsOnTrack.put(track,wagons);
        }
    }
}
