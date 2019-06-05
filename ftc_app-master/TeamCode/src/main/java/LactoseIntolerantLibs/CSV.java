package LactoseIntolerantLibs;

import java.io.File;
import java.io.PrintWriter;

public class CSV {
    public static void writeToCSV(String data) {
        try (PrintWriter pw = new PrintWriter(new File("C:\\Users\\dkasa\\Documents\\GitHub\\LactoseIntolerant_\\ftc_app-master\\TeamCode\\src\\main\\java\\LactoseIntolerantLibs\\test.csv"))) {
            pw.write(data);
            pw.close();
            System.out.println("Success");

        } catch (Exception e) {
            System.out.println(e);

        }
    }
}
