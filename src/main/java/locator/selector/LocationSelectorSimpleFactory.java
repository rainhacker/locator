package locator.selector;

/**
 * Created by Parth on 12/27/2014.
 *
 * Simple factory to select nearest N (1/10) location(s) available in db from user
 */
public final class LocationSelectorSimpleFactory {

    private LocationSelectorSimpleFactory(){}

    public static AbstractLocationSelector createLocationSelector(String locationChoice) {

        if (locationChoice.equals("one")) return new NearestOneLocationSelector();

        else if (locationChoice.equals("ten")) return new NearestNlocationSelector();

        return null;
    }
}
