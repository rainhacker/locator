package locator.selector;

import locator.Location;

import java.util.List;

/**
 *
 * @author Parth Solanki
 */
public class NearestOneLocationSelector extends AbstractLocationSelector {

    private Location closestLocation;

    @Override
    protected void processCurrentLocation(List<Location> locations, Location currentLocation, boolean allDone) {

        if (allDone)
            locations.add(closestLocation);

        else if (closestLocation == null) closestLocation = currentLocation;

        else {
            Location userLocation = locations.get(0);

            double minDistance = userLocation.getDistanceFrom(closestLocation);

            double currentDistance = userLocation.getDistanceFrom(currentLocation);

            if (currentDistance < minDistance)
                closestLocation = currentLocation;
        }
    }
}
