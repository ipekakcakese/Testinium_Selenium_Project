package testautomation.beymen.utils;

public class PriceHelper {

    public static String normalizePrice(String priceText) {
            String cleaned = priceText.replaceAll("[^0-9,]", "")
                    .replace(".", "")
                    .replace(",", ".");
            double value = Double.parseDouble(cleaned);
            return String.format("%.2f", value);
    }
}