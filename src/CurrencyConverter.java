import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverter {

    private static final String API_KEY = "56746dbf54d694c7beca225f";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fromCurrency = "";
        String toCurrency = "";
        double amount = 0;

        while (true) {
            System.out.print("Introduce la moneda de origen (Ej: USD): ");
            fromCurrency = scanner.next().toUpperCase();

            if (!isValidCurrency(fromCurrency)) {
                System.out.println("La moneda de origen '" + fromCurrency + "' no es válida. Intenta de nuevo.");
                continue;
            }

            while (true) {
                System.out.print("Introduce la cantidad a convertir: ");
                try {
                    amount = scanner.nextDouble();
                    break;
                } catch (Exception e) {
                    System.out.println("Cantidad no válida. Intenta de nuevo.");
                    scanner.next();
                }
            }

            while (true) {
                System.out.print("Introduce la moneda de destino (Ej: EUR): ");
                toCurrency = scanner.next().toUpperCase();

                if (isValidCurrency(toCurrency)) {
                    break;
                } else {
                    System.out.println("La moneda de destino '" + toCurrency + "' no es válida. Intenta de nuevo.");
                }
            }

            double result = convertirMoneda(fromCurrency, toCurrency, amount);

            if (result != -1) {
                System.out.printf("El valor de %.2f %s en %s es: %.2f %s\n", amount, fromCurrency, toCurrency, result, toCurrency);
            } else {
                System.out.println("Error en la conversión.");
            }

            break;
        }

        scanner.close();
    }

    public static boolean isValidCurrency(String currency) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + currency))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            System.out.println("Error al validar la moneda: " + e.getMessage());
            return false;
        }
    }

    public static double convertirMoneda(String fromCurrency, String toCurrency, double amount) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL + fromCurrency))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String jsonString = response.body();
                JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
                JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

                if (conversionRates.has(toCurrency)) {
                    double exchangeRate = conversionRates.get(toCurrency).getAsDouble();
                    return amount * exchangeRate;
                } else {
                    System.out.println("La moneda de destino '" + toCurrency + "' no es válida.");
                    return -1;
                }
            } else {
                System.out.println("Error en la solicitud a la API. Código de estado: " + response.statusCode());
                return -1;
            }
        } catch (Exception e) {
            System.out.println("Error en la solicitud a la API: " + e.getMessage());
            return -1;
        }
    }
}
