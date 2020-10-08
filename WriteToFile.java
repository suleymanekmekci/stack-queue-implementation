import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    public static void writeFile(String output, String outputFileName){
        FileWriter writer = null;
        try {
            writer = new FileWriter(outputFileName);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
