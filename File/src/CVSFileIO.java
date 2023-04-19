import java.io.*;
import java.util.ArrayList;

public class CVSFileIO  implements FileIO{

    private  final char separator =';';
    @Override
    public ArrayList<MyFileRecord> getRecords(File file)  {
        try(BufferedReader fr=new BufferedReader(new FileReader(file))) {
            var list=fr.lines().toList();
            ArrayList<MyFileRecord> records=new ArrayList<>(list.size());
            for (var a:list) {
                var b=a.split(String.valueOf(separator));
                records.add(new CVSRecord(b[0],Integer.parseInt(b[1]),b[2]));
            }
            return records;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void saveRecord(File file, MyFileRecord data)  {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(file))) {
            bw.append("\n")
                    .append(data.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
