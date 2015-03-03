package locator.selector;

import locator.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Parth on 12/20/2014.
 */
public class NearestNlocationSelector extends AbstractLocationSelector {

    static class LocationDistance {

        final Location location;

        final double distanceFromUser;

        LocationDistance(Location location, double distanceFromUser) {

            this.location = location;

            this.distanceFromUser = distanceFromUser;

        }
    }

    private List<LocationDistance> locationDistance = new ArrayList<>();

    @Override
    protected void processCurrentLocation(List<Location> locations, Location currentLocation, boolean allDone) {

        final Location userLocation = locations.get(0);

        if (allDone) {

            Collections.sort(locationDistance, new Comparator<LocationDistance>() {
                @Override
                public int compare(LocationDistance ld1, LocationDistance ld2) {

                    return ld1.distanceFromUser.compareTo(ld2.distanceFromUser);

                }
            });

            for (int i = 0; i < locationDistance.size() && i < 10; i++)
                locations.add(locationDistance.get(i).location);

        } else
            locationDistance.add(new LocationDistance(currentLocation,
                    userLocation.getDistanceFrom(currentLocation)));

    }
}
