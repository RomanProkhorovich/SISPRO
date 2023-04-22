import java.io.File;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        CSVFileIO file=new CSVFileIO();
        file.saveRecord(new File("D:\\univer\\SISPRO\\File\\foiles\\file.csv")
                ,new CSVRecord("asd",1, "Name"));

        PlainTextIO plainTextIO=new PlainTextIO();
        plainTextIO.saveRecord(new File("D:\\univer\\SISPRO\\File\\foiles\\plain.txt"),
                new PlainTextRecord(new Date(),"path1",true));
    }
}
