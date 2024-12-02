package utilidad;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ExchangeRateService {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/eac5683f8a6a6e2e03860881/latest/";

    public Map<String, Double> getExchangeRates(String baseCurrency) throws Exception {
        String url_str = API_URL + baseCurrency;

        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        try (InputStream inputStream = request.getInputStream();
             JsonReader jsonReader = Json.createReader(new InputStreamReader(inputStream))) {

            JsonObject jsonobj = jsonReader.readObject();
            String result = jsonobj.getString("result");

            if (!"success".equals(result)) {
                throw new Exception("La API devolvió un error: " + result);
            }

            JsonObject conversionRates = jsonobj.getJsonObject("conversion_rates");
            Map<String, Double> exchangeRates = new HashMap<>();

            for (String currency : conversionRates.keySet()) {
                exchangeRates.put(currency, conversionRates.getJsonNumber(currency).doubleValue());
            }

            return exchangeRates;
        }
    }
}
