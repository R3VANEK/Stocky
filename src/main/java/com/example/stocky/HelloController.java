package com.example.stocky;

import com.google.gson.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private VBox mainVbox;

    @FXML
    private Button saveButton;

    private static final DecimalFormat df = new DecimalFormat("0.00");


    @FXML
    protected void onSaveButtonClick(){

        System.out.println("siema");





        // create popu Window about saving data
        //-------------------------------------------------------------
        Stage PopupWindow = new Stage();
        Label PopupLabel = new Label("Saved data in 123.json");
        PopupLabel.setFont(new Font(19));
        PopupWindow.initModality(Modality.APPLICATION_MODAL);
        PopupWindow.setTitle("Information");
        VBox layout= new VBox(10);

        layout.getChildren().addAll(PopupLabel);
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300, 250);
        PopupWindow.setScene(scene1);
        PopupWindow.showAndWait();
        //-------------------------------------------------------------
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

                HBox apiCard = new HBox(0);
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


                Label tickerLabel = new Label(ticker);
                tickerLabel.setFont(new Font(19));

                Label companyLabel = new Label(apiStockData.getAsJsonObject().get("shortName").getAsString());
                companyLabel.setFont(new Font(24));

                Label boughtAtLabel = new Label("Bought at: "+stockInfo.getAsJsonObject().get("BoughtAt").getAsString()+"$");
                boughtAtLabel.setFont(new Font(15));



                Label percentageLabel = new Label(df.format(investPercentage)+"%");
                if(investPercentage >= 0.0)
                    percentageLabel.setTextFill(Paint.valueOf("#9bf542"));
                else
                    percentageLabel.setTextFill(Paint.valueOf("#f54e42"));
                percentageLabel.setFont(new Font(40));
                HBox.setMargin(percentageLabel,new Insets(0,0,0,170));

                Label currentPriceLabel = new Label("Current price: "+currentPrice+"$");
                currentPriceLabel.setFont(new Font(15));

                VBox labelTextHolder = new VBox();
                VBox.setMargin(boughtAtLabel,new Insets(5,0,0,0));
                labelTextHolder.getChildren().addAll(tickerLabel,companyLabel,boughtAtLabel);
                HBox.setMargin(labelTextHolder, new Insets(10,0,0,20));

                VBox labelStockHolder = new VBox();
                VBox.setMargin(currentPriceLabel, new Insets(0,0,0,0));
                VBox.setMargin(percentageLabel, new Insets(20,0,0,0));

                labelStockHolder.getChildren().addAll(percentageLabel,currentPriceLabel);


                HBox.setMargin(labelStockHolder,new Insets(0,0,0,170));




                apiCard.getChildren().addAll(investmentCircle);
                apiCard.getChildren().addAll(labelTextHolder,labelStockHolder);
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