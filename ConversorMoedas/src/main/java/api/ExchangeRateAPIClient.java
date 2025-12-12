package api;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import api.ExchangeRateResponse;
import java.util.Map.*;

public class ExchangeRateAPIClient {

    //1. Definir o EndPoint
    private static final String API_KEY = "4ee6a7d000e7a27fc1223be1";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";

    public String fetchExchangeRates() {
        try {
            // Cria o objeto URL com o endpoint
            URL url = new URL(BASE_URL);

            // Abre a conexão (cast para HttpURLConnection)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Define o método como GET (Para recuperar dados)
            connection.setRequestMethod("GET");

            //2. Responde Code Check (HTTP Status)
            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) { //HTTP_OK é o mesmo que 200 (Erro seria 404 ou 500)
                System.err.println("API request failed. HTTP Status Code " + responseCode);
                return null;
            }

            //3. Data Stream Reading (Leitura de Fluxo de Dados)
            //Cria um leitor para ler a resposta da conexão
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            //Lê a linha de resposta até o fim
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Fecha o leitor e desconecta a conexão
            reader.close();
            connection.disconnect();

            return response.toString();
        } catch (Exception e) {
            System.err.println("An error occurred during API request: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ExchangeRateResponse parseRates(String jsonResponse){
        if(jsonResponse == null){
            System.err.println("Cannot parse null response");
            return null;
        }

        try{
            Gson gson = new Gson();

            // 1. O método fromJson é a chave. Ele recebe a string JSON e a CLASSE
            // que você quer que ele preencha.
            ExchangeRateResponse response = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

            return response;
        }catch(Exception e){
            System.err.println("An error occurred during JSON parsing: " + e.getMessage());
            e.printStackTrace();
            return null;
        }


    }

    /*public static void main(String[] args) {
        ExchangeRateAPIClient client = new ExchangeRateAPIClient();
        String jsonResponse = client.fetchExchangeRate();

        if (jsonResponse != null) {
            ExchangeRateResponse rates = client.parseRates(jsonResponse);

            if (rates != null) {
                System.out.println("\n--- SUCCESS: Parsed Data ---");
                // Tenta pegar a taxa de Real (BRL)
                Double brlRate = rates.getConversionRates().get("BRL");

                System.out.println("Base Currency: " + rates.getBaseCode());
                System.out.println("BRL Rate (1 USD =): " + brlRate);
                System.out.println("----------------------------------------");
            }
        } else {
            System.err.println("API call failed.");
        }
    }*/
}
