package jhn;

public class WeatherApp {
    public static Menu menu;
    public static JsonHandler json;

    public static void main(String[] args) throws Exception {

        json = new JsonHandler("weatherapp1\\src\\main\\java\\jhn\\settings.json");

        menu = new Menu(new Weather(getLat(), getLong()));
        menu.setVisible(true);

    }

    // overloaded constructed so we can use static helper methods
    public WeatherApp() {
    }

    /*
     * Global Helper Variables
     */

    public static int getMiddleX(int sizeDiff) {
        return (1920 - sizeDiff) / 2;
    }

    public static int getMiddleY(int sizeDiff) {
        return (1080 - sizeDiff) / 2;
    }

    public static double getLat() {
        return getJsonHandler().getDouble("latitude");
    }

    public static double getLong() {
        return getJsonHandler().getDouble("longitude");
    }

    public static void setLat(double lat) {
        if(lat > 90) lat = 90;
        if(lat < -90) lat = -90;
        json.setValue("latitude", lat);
    }

    public static void setLong(double lon) {
        if(lon > 180) lon = 180;
        if(lon < -180) lon = -180;
        json.setValue("longitude", lon);
    }

    public static Menu getMenu() {
        return menu;
    }

    public static JsonHandler getJsonHandler() {
        return json;
    }
}
