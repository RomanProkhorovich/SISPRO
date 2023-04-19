import java.util.ArrayList;

/**
 * store ArrayList of File
 */
public class FileOfRecords<T extends MyFileRecord> {
    ArrayList<T> records;
    String directoryPath;

    public FileOfRecords(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public ArrayList<T> getRecords() {
        return records;
    }

    public String getDirectoryPath() {
        return directoryPath;
    }

    public T getRecord(int id){
        return records.get(id);
    }
    public void addRecord(T record){
        records.add(record);
    }
}
