package util;

public class StringToArray {
    public static String[] convert(String item){
        return item.replace("[","").replace("]","").split(", ");
    }
}
