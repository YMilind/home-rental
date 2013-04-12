package web.common;

import java.util.List;
import web.model.Property;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
public class StaticMap {

    private final static String BASE_URL = "http://maps.googleapis.com/maps/api/staticmap?";
    private final static String ZOOM = "zoom=13";
    private final static String SIZE = "size=600x300";
    private final static String KEY = "key=AIzaSyCIE1oJyR-XDU30PRTTMhadZtRVI1Spf7I";
    private final static String MARKER = "markers=";
    private final static String MARKER_SEPARATOR = "%7C";
    private final static String COLOR_RED = "color:red";
    private final static String LABEL = "label:";
    private final static String SENSOR = "sensor=false";
    private final static String SEPARATOR = "&";

    public StaticMap() {
    }

     public static String buildMapURL(List<Property> properties) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL);
        url.append(ZOOM);
        url.append(SEPARATOR);
        url.append(SIZE);
        url.append(SEPARATOR);
        url.append(KEY);
        url.append(SEPARATOR);
        url.append(addMarkers(properties));
        url.append(SEPARATOR);
        url.append(SENSOR);
        return url.toString();
    }
    
    public static String buildMapURL(Property property) {
        StringBuilder url = new StringBuilder();
        url.append(BASE_URL);
        url.append(ZOOM);
        url.append(SEPARATOR);
        url.append(SIZE);
        url.append(SEPARATOR);
        url.append(KEY);
        url.append(addMarkers(property));
        url.append(SEPARATOR);
        url.append(SENSOR);
        return url.toString();
    }

    private static String addMarkers(List<Property> properties) {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < properties.size(); i++) {
            if (i > 0) {
                string.append(SEPARATOR);
            }
            if (properties.get(i).getCoordinates() != null) {
                string.append(SEPARATOR);
                string.append(MARKER);
                string.append(COLOR_RED);
                string.append(MARKER_SEPARATOR);
                string.append(LABEL);
                string.append(i);
                string.append(MARKER_SEPARATOR);
                string.append(properties.get(i).getCoordinates());
//            } else {
//                string.append(properties.get(i).getAddress());
            }
        }
        return string.toString();
    }

    private static String addMarkers(Property property) {
        StringBuilder string = new StringBuilder();
        if (property.getCoordinates() != null) {
            string.append(SEPARATOR);
            string.append(MARKER);
            string.append(COLOR_RED);
            string.append(MARKER_SEPARATOR);
            string.append(LABEL);
            string.append(1);
            string.append(MARKER_SEPARATOR);
            string.append(property.getCoordinates());
//            } else {
//                string.append(properties.get(i).getAddress());
        }
        return string.toString();
    }
}
