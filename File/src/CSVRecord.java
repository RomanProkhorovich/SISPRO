/**
 * store one CVS record: file data, file version,file name
 */
public  class CSVRecord implements MyFileRecord  {
    private String fileRecord;
    private int version;
    private String name;
    private final char separator=';';

    public CSVRecord(String fileRecord, int version, String name) {
        this.fileRecord = fileRecord;
        this.version = version;
        this.name = name;
    }

    public String getFileRecord() {
        return fileRecord;
    }

    public void setFileRecord(String fileRecord) {
        this.fileRecord = fileRecord;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public CSVRecord clone() throws CloneNotSupportedException {
        CSVRecord file= (CSVRecord) super.clone();
        file.name=new String(name);
        file.fileRecord=new String(fileRecord);
        return  file;
    }

    @Override
    public String toString() {
        return new String(fileRecord + separator+version+separator+name);
    }
}
