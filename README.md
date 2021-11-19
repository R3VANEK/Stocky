# Stocky
[![forthebadge](https://forthebadge.com/images/badges/made-with-java.svg)](https://forthebadge.com)
![forthebadge](https://forthebadge.com/images/badges/open-source.svg)

## Table of Contents
* [How to get started](#How-to-get-started)
* [Functionalites of app](#Functionalites-of-app)
* [Comments and adnotations](#Comments-and-adnotations)
* [Installation](#Installation)
* [Technologies used](#Technologies-used)
* [Authors](#Authors)
* [License](#License)



## How to get started

This program was constructed for lectures about JavaFX. It is simple app for viewing your stock exchange gains and losses.
To start using it, modify `userData.json` that is filled with sample data

``` JSON
{
  "myStocks": [
    {
      "Ticker" : "MSFT",
      "BoughtAt" : 300
    },
    {
      "Ticker" : "GME",
      "BoughtAt" : 400
    },
    {
      "Ticker" : "SHOP",
      "BoughtAt" : 1669.52
    },
    {
      "Ticker" : "JNJ",
      "BoughtAt" : 100
    }
  ]
}
```

You can find stock ticker for your company [here](https://www.marketwatch.com/tools/quotes/lookup.asp)<br/>
values in properties `BoughtAt` represents money in dollars

## Functionalites of app

After setting up `userData.json` when you start app it will automatically display visual cards with current stock info related to your interests<br/><br/>

You can there view basic info like 
* company name
* company's ticker 
* price you paid for shares
* current market price 
* calculated loss or gain in percentage


Stocky also provide a way to save session data so you always can see how your stocks behave from day to day. To do this just click button "Save session data"
it will be saved under `/data` folder as `.json` file with current date as name<br/><br/>
Example `2021-11-19.json`

```json
{
 "StockData" : [
		{
			"Ticker" : "MSFT",
			"Currentprice" : 341.27
		},
		{
			"Ticker" : "GME",
			"Currentprice" : 210.13
		},
		{
			"Ticker" : "SHOP",
			"Currentprice" : 1683.18
		},
		{
			"Ticker" : "JNJ",
			"Currentprice" : 162.41
		}
	]
}
```

## Comments and adnotations

* Due to deadline users must modify `personalData.json` directly, the app doesn't provide any graphical help for that


## Technologies used

* Java
* JavaFX
* Maven
* Jsoup Library
* Gson library
* [Styvio API v1](https://www.styvio.com/)


## Authors
Jan Napieralski  [R3VANEK](https://github.com/R3VANEK)

## License
[GPL-3.0](https://github.com/R3VANEK/Stocky/blob/main/LICENSE)
