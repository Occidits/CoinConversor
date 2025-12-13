# Coin Conversor: Real-Time Currency Exchange

[![Java](https://img.shields.io/badge/Java-8+-blue.svg)](https://www.java.com/)
[![Maven](https://img.shields.io/badge/Build-Maven-orange.svg)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)

---

## Overview

This project is a desktop/console application developed in Java to demonstrate real-time data integration via external APIs. The goal is to provide users with up-to-date currency exchange rates and allow them to perform conversions between different currencies.

This application highlights proficiency in handling synchronous/asynchronous network requests, data parsing, and clean application architecture.

## Key Features

* **Real-Time API Integration:** Fetches the latest exchange rates from an external REST API.
* **Data Parsing:** Handles and parses JSON/XML responses to extract relevant rate information.
* **Currency Conversion:** Performs accurate calculations based on the retrieved rates.
* **Error Handling:** Implements robust logic to manage connection failures or API response errors.
* **Basic UI (Optional):** If applicable, mention the UI: "Simple user interface built with Swing for input/output."

## Technologies Used

This project was built using a standard Java development stack, focusing on backend core functionalities:

* **Language:** Java 8+
* **Build Tool:** Maven (Dependency Management)
* **Communication:**
    * `java.net.HttpURLConnection` or external library (e.g., OkHttp/HttpClient) for API calls.
* **Data Handling:**
    * **JSON/XML Parsing:** Handling data structures from the API.
* **Design Pattern:** Object-Oriented Programming (OOP) principles.

## How to Run the Project

### Prerequisites

* Java Development Kit (JDK 8 or higher)
* Apache Maven

### Installation and Execution

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/Occidits/CoinConversor.git]
    cd CoinConversor
    ```

2.  **Build the project using Maven:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    * *If using an IDE (IntelliJ/Eclipse):* Run the `main` method in the primary execution class: `AppFinal.java`
    * *If running from the command line (after creating a JAR):*
        ```bash
        java -jar target/ConversorMoedas-1.0-SNAPSHOT.jar
        ```

## Project Structure
.
├── .idea/                      # IntelliJ IDE configuration files
├── .mvn/                       # Maven wrapper files
├── src/
│   └── main/
│       ├── java/               # Core application source code
│       │   ├── api/            # API Integration Layer
│       │   │   ├── ExchangeRateAPIClient.java # Handles external HTTP requests
│       │   │   └── ExchangeRateResponse.java  # POJO for API response data
│       │   ├── db/             # Data Access Layer (DAO Pattern)
│       │   │   ├── DBManager.java           # Manages database connection
│       │   │   ├── Moeda.java               # Model/Entity for a currency
│       │   │   └── MoedasDAO.java           # DAO implementation for currency persistence
│   │   │   
│   │   ├── ConverterForm.java  # UI logic (Swing)
│   │   ├── AppFinal.java       # Main entry point of the application
│   │   └── ...                 # Other source files
│   └── resources/              # Configuration files and assets
│       └── moedas.db           # SQLite database file
├── target/                     # Compiled classes and build artifacts
├── .gitignore                  # Files/folders ignored by Git
└── pom.xml                     # Maven project configuration and dependencies

## Screenshot

<img width="1365" height="720" alt="image" src="https://github.com/user-attachments/assets/ac8b2c4c-3d33-4468-829e-6e2dfa31bc2d" />
