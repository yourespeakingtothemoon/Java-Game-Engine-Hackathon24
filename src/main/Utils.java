package main;

import java.awt.image.BufferedImage;
import java.util.Objects;

public class Utils {


    public static int[] readMapData(String filename, int width, int height){
        int[] result = new int[width * height];
        try {
            //read array of numbers from text file delimited by comma and whitespace
            String[] data = Utils.readFile(filename).split(",\\s*");
            for (int i = 0; i < data.length; i++) {
                result[i] = Integer.parseInt(data[i]);
            }
        }
            catch (Exception e){
                e.printStackTrace();
            }

       return result;
    }

    public static String readFile(String filename){
        String result = "";
        try {
            //read text file
            result = new String(Objects.requireNonNull(Utils.class.getClassLoader().getResourceAsStream(filename)).readAllBytes());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
