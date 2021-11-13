package com.example.stocky;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private VBox mainVbox;

    @FXML
    protected void onHelloButtonClick() throws IOException {

        welcomeText.setText("Welcome to JavaFX Application!");




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        JsonArray userStocks = null;

        File file = new File("./personalData.json");
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.toURI())));
            JsonElement jsonObject = new JsonParser().parse(content);
            userStocks = jsonObject.getAsJsonObject().get("myStocks").getAsJsonArray();

            } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        System.out.println(userStocks);

        assert userStocks != null;
        for(JsonElement stockInfo : userStocks){
            try {
                String ticker = stockInfo.getAsJsonObject().get("Ticker").getAsString();
                JsonObject apiStockData = ScrapingAPI.getJSONCard(ticker);

                HBox apiCard = new HBox(90);
                apiCard.getStyleClass().add("stock-div");
                apiCard.setAlignment(Pos.CENTER_LEFT);
                VBox.setMargin(apiCard,new Insets(50,0,0,50));
                double currentPrice = apiStockData.get("dailyPrices").getAsJsonArray().get(apiStockData.get("dailyPrices").getAsJsonArray().size()-1).getAsDouble();
                double userPrice = stockInfo.getAsJsonObject().get("BoughtAt").getAsDouble();

                double investPercentage = (currentPrice/userPrice)*100-100;
                Circle investmentCircle;
                if(investPercentage >= 0.0)
                    investmentCircle = new Circle(30, Paint.valueOf("#9bf542"));
                else
                    investmentCircle = new Circle(30, Paint.valueOf("#f54e42"));
                HBox.setMargin(investmentCircle, new Insets(0,0,70,10));







                apiCard.getChildren().addAll(investmentCircle);
                mainVbox.getChildren().add(apiCard);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


//
//        JsonObject gme = null;
//        try {
//            gme = ScrapingAPI.getJSONCard("gme");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        HBox test = new HBox(90);
//        test.getStyleClass().add("stock-div");
//        test.setAlignment(Pos.CENTER_LEFT);
//        VBox.setMargin(test,new Insets(50,0,0,50));
//
//
//        // setting color circle indicating potential grade of stock
//        String tradeScore = gme.get("tradeScore").getAsString();
//        Circle investmentCircle;
//        if(tradeScore.contains("A") || tradeScore.contains("B"))
//            investmentCircle = new Circle(30, Paint.valueOf("#9bf542"));
//        else
//            investmentCircle = new Circle(30, Paint.valueOf("#f54e42"));
//        HBox.setMargin(investmentCircle, new Insets(0,0,70,10));
//
//
//
//
//
//
//
//        test.getChildren().addAll(investmentCircle);
//        mainVbox.getChildren().add(test);
    }
}