package pt.ulusofona.aed.deisiworldmeter;

public class HaversineDistances {

    String citiesByOrder;

    Float haversineDistance;

    public HaversineDistances(String city1, String city2, Float haversineDistance) {
        if (city1.compareTo(city2) < 0) {
            this.citiesByOrder = city1 + "->" + city2;
        } else {
            this.citiesByOrder = city2 + "->" + city1;
        }
        this.haversineDistance = haversineDistance;
    }
}
