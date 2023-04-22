import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class PlainTextIO  {
    public ArrayList<PlainTextRecord> getRecords(File file) {
        ArrayList<PlainTextRecord> res=new ArrayList<>();
        try(BufferedReader reader=new BufferedReader(new FileReader(file))){
            reader.lines().forEach(x->{
                var a=x.split(" ");
                res.add(new PlainTextRecord(Date.valueOf(a[0]),a[1],Boolean.parseBoolean(a[2])));
            });
            return res;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveRecord(File file, PlainTextRecord data) {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))) {
            bw.append("\n")
                    .append(data.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
