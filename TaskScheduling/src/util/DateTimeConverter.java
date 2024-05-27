package util;

public class DateTimeConverter {
    public static String convert(String item){
        return item.substring(0,10) + " " + item.substring(11,16);
    }
}
