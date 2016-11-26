package su.elevenets.devicemanagerclient.managers.loc;

/**
 * Created by eugene.levenetc on 26/11/2016.
 */
public class Loc {

	private final double lat;
	private final double lon;

	public Loc(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
}
