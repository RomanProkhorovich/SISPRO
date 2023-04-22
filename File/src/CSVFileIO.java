import java.io.*;
import java.util.ArrayList;

public class CSVFileIO {

    private  final char separator =';';
    public ArrayList<CSVRecord> getRecords(File file)  {
        try(BufferedReader fr=new BufferedReader(new FileReader(file))) {
            var list=fr.lines().toList();
            ArrayList<CSVRecord> records=new ArrayList<>(list.size());
            for (var a:list) {
                var b=a.split(String.valueOf(separator));
                records.add(new CSVRecord(b[0],Integer.parseInt(b[1]),b[2]));
            }
            return records;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveRecord(File file, CSVRecord data)  {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))) {
            bw.append(data.toString()).append("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
