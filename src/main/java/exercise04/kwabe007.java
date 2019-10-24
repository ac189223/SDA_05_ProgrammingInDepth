package exercise04;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class kwabe007 {
    public static void main(String[] args) {
        System.out.println(makeJsonPretty(makeHTTPRequestForLavenderTown()));
    }

    public static String makeJsonPretty(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(uglyJson);
        String prettyJson = gson.toJson(jsonElement);
        return prettyJson;
    }

    public static String makeHTTPRequestForLavenderTown() {

        String urlString = "https://pokeapi.co/api/v2/location/lavender-town";
        String response = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Scanner responseScanner = new Scanner(connection.getInputStream());
            while (responseScanner.hasNext()) {
                response = responseScanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
