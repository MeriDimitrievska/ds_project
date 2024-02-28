Overview

This project is designed to efficiently fetch cryptocurrency data from an API, store it in a database, and perform various analytics using a service layer. 
The analytics include calculating average prices, minimal prices, maximal prices, and identifying the top 5 cryptocurrencies by price. The entire system is 
orchestrated through Kafka, with four dedicated producers handling each specific logic and one consumer responsible for receiving the aggregated
data.

Features
- Data Fetching: Utilizes API calls to fetch real-time cryptocurrency data.
- Data Storage: Stores the retrieved data in a database for future reference and analysis.
- Service Logic:
  
  --Average Price Calculation: Computes the average price of cryptocurrencies.
  
  --Minimal Price Calculation: Identifies the minimum price among the collected data.
  
  --Maximal Price Calculation: Determines the maximum price observed.
  
  --Top 5 Cryptocurrencies: Ranks and lists the top 5 cryptocurrencies based on their prices.
  
- Kafka Integration:
  
  --Uses Kafka as the messaging system to facilitate communication between different components.
  
  --Employs four producers, each dedicated to a specific service logic, and one consumer for receiving aggregated data.
