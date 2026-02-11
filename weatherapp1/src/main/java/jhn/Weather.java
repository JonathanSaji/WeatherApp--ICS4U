package jhn;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Weather {

    JSONArray time, temperatures;
    double latitude, longitude;
    JSONObject dataObject;

    public Weather(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        try {
            // Public API
            // https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m&timezone=America%2FNew_York
            // "https://api.open-meteo.com/v1/forecast?latitude=&longitude=-75&hourly=temperature_2m&models=best_match&timezone=America%2FNew_York"

            URL url = new URL(
    "https://api.open-meteo.com/v1/forecast?"
    + "latitude=" + latitude
    + "&longitude=" + longitude
    + "&models=gem_seamless"
    + "&hourly=temperature_2m,relative_humidity_2m,dew_point_2m,apparent_temperature,"
    + "precipitation,rain,showers,snowfall,pressure_msl,surface_pressure,cloud_cover,"
    + "wind_speed_10m,soil_temperature_0_to_10cm,soil_moisture_0_to_10cm,is_day,"
    + "wind_gusts_10m,wind_direction_10m"
    + "&timezone=auto"
    + "&past_days=61"
    + "&forecast_days=10"
);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // check if the connection is made (debugging)
            int responseCode = conn.getResponseCode();

            // 200 = ok
            if (responseCode != 200) {
                // throw an error
                throw new RuntimeException("HttpResponseCode: " + responseCode);

            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    // just puts everything in one big line
                    informationString.append(scanner.nextLine());
                }
                // close the scanner
                scanner.close();

                // Change 1: Parse as JSONObject, not JSONArray
                JSONParser parse = new JSONParser();
                dataObject = (JSONObject) parse.parse(String.valueOf(informationString));

                // Change 2: Now you can access keys directly from dataObject
                // System.out.println("Latitude: " + dataObject.get("latitude"));

                // hourly is nested so we are going to need multiple
                JSONObject hourly = (JSONObject) dataObject.get("hourly");
                temperatures = (JSONArray) hourly.get("temperature_2m");

                time = (JSONArray) hourly.get("time");
                // int index = 10;
                // System.out.println("Temperature: " + temperatures.get(index) + " Time: " +
                // time.get(index));
                // System.out.println("done printing");
            }

            // not the best way to catch but it works!
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getWeatherWithTime(int slot) {
        return "Temp: " + temperatures.get(slot) + " Time: " + time.get(slot) + " Latitude: "
                + dataObject.get("latitude") + " Longtitude: " + dataObject.get("longitude");
    }

    public String getLongLat() {
        return "<html>Latitude: " + dataObject.get("latitude") + "<br>Longtitude: " + dataObject.get("longitude")
                + "</html>";
    }

    public String getTime(int slot) {
        return String.valueOf(time.get(slot));
    }

    // NEW METHOD: Get temperature for a specific date and hour
    public String getTemperatureForDateTime(LocalDate date, int hour, boolean celcius) {
        String degree = celcius ? "°C" : "°F";
        // Format: "2025-12-12T14:00"
        String searchTime = String.format("%sT%02d:00", date.toString(), hour);

        for (int i = 0; i < time.size(); i++) {
            String timeStr = (String) time.get(i);
            if (timeStr.equals(searchTime)) {
                Object tempObj = temperatures.get(i);
                if (tempObj instanceof Double) {
                    double tempValue = Math.round(((Double) tempObj).doubleValue());
                    int temp = degree.equals("°C") ? (int) tempValue : (int) (tempValue * 1.8 + 32);
                    return String.valueOf(temp) + degree;
                }
            }
        }

        return null; // Not found
    }

    public boolean isThereTemp(LocalDate date, int hour) {
        String searchTime = String.format("%sT%02d:00", date.toString(), hour);
        for (int i = 0; i < time.size(); i++) {
            String timeStr = (String) time.get(i);
            if (timeStr.equals(searchTime)) {
                return true;
            }
        }
        return false;
    }

    // NEW METHOD: Get index for a specific date and hour
    public int getIndexForDateTime(LocalDate date, int hour) {
        String searchTime = String.format("%sT%02d:00", date.toString(), hour);

        for (int i = 0; i < time.size(); i++) {
            String timeStr = (String) time.get(i);
            if (timeStr.equals(searchTime)) {
                return i;
            }
        }

        return -1; // Not found
    }
}