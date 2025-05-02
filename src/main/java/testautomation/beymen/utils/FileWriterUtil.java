package testautomation.beymen.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import testautomation.beymen.model.ProductDetails;

public class FileWriterUtil {
    public static void writeProductDetailsToFile(ProductDetails productDetails, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(productDetails.toString());
        } catch (IOException e) {
            LoggerUtil.logger.error("File write error: " + e.getMessage());
        }
    }
}
