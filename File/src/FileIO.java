import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public interface FileIO {
     ArrayList<MyFileRecord> getRecords(File file) ;
     void saveRecord(File file,MyFileRecord data) ;

}
