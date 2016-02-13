package locator;

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import locator.selector.AbstractLocationSelector;
import locator.selector.LocationSelectorSimpleFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Servelet hanlder that processes requests to fetch nearby WiFi hotspots
 * 
 * @author Parth Solanki
 */
 
public class MapServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String show = request.getParameter("show");

        String userLatitudeString = request.getParameter("curr_lat");

        String userLongitudeString = request.getParameter("curr_long");

        PrintWriter out = response.getWriter();

        if (invalidRequestParameters(show, userLatitudeString, userLongitudeString, out)) return;

        Location userLocation = new Location(Double.parseDouble(userLatitudeString)
                , Double.parseDouble(userLongitudeString), "You are here!!!", "Awesome User");

        List<Location> locations = new ArrayList<>();

        locations.add(userLocation);

        Gson gson = new GsonBuilder().create();

        AbstractLocationSelector locationSelector =
                LocationSelectorSimpleFactory.createLocationSelector(show);
                
        if (locationSelector == null) {

            out.println("Error: Invalid choice");

            return;
        }

        locationSelector.setNearestLocationList(locations, getServletContext());

        request.setAttribute("locationList", gson.toJson(locations));

        request.getRequestDispatcher("/showMap.jsp").forward(request, response);
    }

    private boolean invalidRequestParameters(String show, String userLatitudeString, String userLongitudeString, PrintWriter out) {
        if (isEmptyOrNull(show) || isEmptyOrNull(userLatitudeString) || isEmptyOrNull(userLongitudeString)) {
            out.println("Invalid input received. show : " + show + "currentLatitude : "
                    + userLatitudeString + "currentLongitude : " + userLongitudeString);
            return true;
        }
        return false;
    }

    private boolean isEmptyOrNull(String str) {
        return str == null || str.length() == 0;
    }
}
