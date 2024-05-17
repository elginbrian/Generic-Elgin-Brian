package Latihan;
import java.io.*;

public class JavaIO {
    public static void main(String[] args) {
        try{
            File io = new File("D:\\CODE\\Java OOP\\Java Operation File\\Latihan","IO.txt");
            System.out.println(io.getCanonicalPath());
            
            FileReader reader = new FileReader(io);
            BufferedReader buffer = new BufferedReader(reader);

            String output = buffer.readLine();
            while (output != null) {
                System.out.println(output);
                output = buffer.readLine();
            }

            buffer.close();
            reader.close();

        } catch(Exception e){
            System.out.println(e.toString());
        }
    }
}