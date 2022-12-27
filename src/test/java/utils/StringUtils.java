package utils;

public class StringUtils {
    public String getPriceNumbers(String currentPriceText){
        return currentPriceText.substring(0, currentPriceText.indexOf(" "));
    }

    public String[] splitBySpace(String text){
        return text.split(" ");
    }
}
