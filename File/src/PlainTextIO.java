import java.io.*;
import java.sql.Date;
import java.util.ArrayList;

public class PlainTextIO implements FileIO {
    @Override
    public ArrayList<MyFileRecord> getRecords(File file) {
        ArrayList<MyFileRecord> res=new ArrayList<>();
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

    @Override
    public void saveRecord(File file, MyFileRecord data) {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))) {
            bw.append("\n")
                    .append(data.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
