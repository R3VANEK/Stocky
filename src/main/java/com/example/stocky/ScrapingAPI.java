package com.example.stocky;


import java.lang.String;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public interface ScrapingAPI{

    String[] defaultStockTickers = {"GME", "TSLA","DELL","BP","NN","F","ZG","BNGO"};


    static String getStockShortName(String givenName){
        return "not implemented";
    }


    private static String getBodyFromAPI(String givenURL) throws IOException{
        URL expansionsListUrl = new URL(givenURL);
        HttpURLConnection conn = (HttpURLConnection) expansionsListUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();
        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        }

        StringBuilder body = new StringBuilder();
        Scanner scanner = new Scanner(expansionsListUrl.openStream());

        while (scanner.hasNext()) {
            body.append(scanner.nextLine());
        }
        scanner.close();
        return String.valueOf(body);
    }


    static JsonObject getJSONCard(String ticker) throws IOException {
        String body = getBodyFromAPI("https://www.styvio.com/api/"+ticker);
        return new JsonParser().parse(body).getAsJsonObject();
    }
}