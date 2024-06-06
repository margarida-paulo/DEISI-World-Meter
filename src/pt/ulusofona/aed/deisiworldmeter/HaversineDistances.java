package pt.ulusofona.aed.deisiworldmeter;

public class HaversineDistances {

    String cityAlphaFirst;
    String cityAlphaSecond;

    Float haversineDistance;

    public HaversineDistances(String cityAlphaFirst, String cityAlphaSecond, Float haversineDistance) {
        this.cityAlphaFirst = cityAlphaFirst;
        this.cityAlphaSecond = cityAlphaSecond;
        this.haversineDistance = haversineDistance;
    }
}
