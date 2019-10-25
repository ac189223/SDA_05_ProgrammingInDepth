package exercise04;

import com.google.gson.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class ListOfPokemons {
    private static final String FILE_POKE = "src/main/java/exercise04/poke.json";

    public static void makeJsonPrettyToFile(String uglyJson) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(uglyJson);
        try (FileWriter writer = new FileWriter(FILE_POKE)) {
            gson.toJson(jsonElement, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
    String urlString = "";
        for (int i = 1; i < 1000; i++){
            if (i % 6 == 0)
                System.out.println();
            urlString = "https://pokeapi.co/api/v2/pokemon/" + i + "/";
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
                makeJsonPrettyToFile(response);
                filterPrintoutPokemon();
            } catch (IOException e) {
            }
        }
    }

    private static void filterPrintoutPokemon() throws Exception {
        JsonObject jsonObject = (JsonObject) readJson(FILE_POKE);
        System.out.print("" + jsonObject.get("name") + ",");
    }

    public static Object readJson(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parse(reader);
    }

}
