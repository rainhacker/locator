package locator.selector;

import au.com.bytecode.opencsv.CSVReader;
import locator.Location;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Parth on 12/27/2014.
 */
public abstract class AbstractLocationSelector {

    protected abstract void processCurrentLocation(List<Location> locations, Location currentLocation, boolean allDone);

    /**
     *
     * @param locations: Required current user location as the only element added to locations
     * @param servletContext : Context of Servlet to read data file
     * @throws IOException
     */
    public void setNearestLocationList(List<Location> locations,
                                                    ServletContext servletContext) throws IOException {

        final String geolocationDataFile = "/WEB-INF/data/NYC_Free_Public_WiFi_12052014.csv";

        CSVReader reader;

        reader = new CSVReader(new BufferedReader(
                new InputStreamReader(
                        servletContext.getResourceAsStream(geolocationDataFile))));

        String[] geolocationRecord;

        boolean firstLine = true;

        while ((geolocationRecord = reader.readNext()) != null) {

            if (firstLine) {

                firstLine = false;

                continue;
            }

            //call appropriate location chooser (nearest one / nearest few)
            processCurrentLocation(locations, getCurrentLocation(geolocationRecord), false);
        }

        processCurrentLocation(locations, null, true);
    }

    private Location getCurrentLocation(String[] geolocationRecord) {

        double currentLatitude = Double.parseDouble(geolocationRecord[6]);

        double currentLongitude = Double.parseDouble(geolocationRecord[7]);

        String currentAddress = geolocationRecord[5] + "<br/>" + geolocationRecord[12];

        return new Location(currentLatitude, currentLongitude, currentAddress, geolocationRecord[4]);
    }
}
