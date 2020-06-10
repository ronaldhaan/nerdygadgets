package nerdygadgets.algorithm;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import nerdygadgets.shared.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Postal {

    private Postal() {

    }

    /**
     * Get coordinates by postal and place
     *
     * @param postal
     * @param place
     * @return JSONObject
     * @exception IOException
     */
    public static JSONObject getCoordinatesByPostalAndPlace(String postal, String place) {
        postal = postal.replace(" ", "");
        String urlString = "https://api.opencagedata.com/geocode/v1/json?q=" + postal + "&key=91e980ccac324648a202946b23b32659";
        JSONObject coordinates = null;

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));
            JSONArray results = jsonObject.getJSONArray("results");
            
            JSONObject annotations = getObjectByKey(results, "annotations");
            if(annotations != null) {
                coordinates = annotations.getJSONObject("Mercator");
                coordinates.put("place", place);
            }
        }  catch (Exception e) {
            Utility.handleUnexpectedException(e);
        }

        return coordinates;
    }

    /**
     * Get JSONObject by array key
     *
     * @param array
     * @param key
     * @return JSONObject
     */
    public static JSONObject getObjectByKey(JSONArray array, String key) {
        JSONObject value = null;
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);
            if (item.keySet().contains(key)) {
                value = (JSONObject) item.get(key);
                break;
            }
        }

        return value;
    }

    /**
     * Get String by array key
     *
     * @param array
     * @param key
     * @return
     */
    public static String getStringByKey(JSONArray array, String key) {
        String value = null;
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.getJSONObject(i);
            if (item.keySet().contains(key)) {
                value = (String) item.get(key);
                break;
            }
        }

        return value;
    }
}
