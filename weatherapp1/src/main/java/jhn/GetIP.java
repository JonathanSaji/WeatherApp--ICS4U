package jhn;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import org.json.simple.JSONObject;


public class GetIP {
    double latitude, longitude;
    StringBuilder informationString;
    JSONObject dataObject;

    public GetIP() {
        // https://api.ipify.org/
        try {

            URL url = new URL("https://api.ipify.org/");

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

                informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    // just puts everything in one big line
                    informationString.append(scanner.nextLine());
                }
                // close the scanner
                scanner.close();

                System.out.println("IP Address: " + informationString);
            }
            // not the best way to catch but it works!
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getIP(){
            return informationString.toString();
        }

    public static void main(String[] args) {
        new GetIP();
    }












}
