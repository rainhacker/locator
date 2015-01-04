package locator;

/**
 * Created by Parth on 12/20/2014.
 *
 * POJO representation of geolocation record in db
 */
public class Location {

    private final double latitude;

    private final double longitude;

    private final String address;

    private final String name;

    public Location(double latitude, double longitude, String address, String name) {

        this.latitude = latitude;

        this.longitude = longitude;

        //single quotes added to assign variables properly to java script variables
        this.address = "'" + address + "'";

        this.name = "'" + name + "'";
    }

    //to ensure Location object is created only by using parametrized constructor.
    // Attributes have to be initialized to default values as they are final.
    private Location() {

        this.latitude = 0.0;

        this.longitude = 0.0;

        this.address = null;

        this.name = null;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    /**
     *
     * @param that : other location coordinate
     * @return : distance between caller location object and that
     */
    /*getDistanceFrom source code is based on http://www.geodatasource.com/developers/java*/
    //TODO: Check if BigDecimal might be needed instead of double (overflow)
    public final double getDistanceFrom(Location that) {

        double theta = this.longitude - that.longitude;

        double distance = Math.sin(degreeToRadian(this.latitude)) * Math.sin(degreeToRadian(that.latitude))
                + Math.cos(degreeToRadian(this.latitude)) * Math.cos(degreeToRadian(that.latitude))
                * Math.cos(degreeToRadian(theta));

        distance = Math.acos(distance);

        distance = radianToDegree(distance);

        distance = distance * 60 * 1.1515;

        return distance * 1.609344; //in kilometers
    }

    private final double degreeToRadian(double degree) {
        return (degree * Math.PI / 180.0);
    }

    private final double radianToDegree(double radian) {
        return (radian * 180 / Math.PI);
    }

}
