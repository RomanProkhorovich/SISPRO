import java.util.Date;

/**
 * store one Plain Text record: access date, file URL, access mode(public or private)
 * @author Roman Prohorovvich,Viktoria Andreeva
 */
public class PlainTextRecord implements MyFileRecord {
    /**
     * field to store date of access to file
     */
    private Date accessDate;
    /**
     * field to store file path on the Internet
     */
    private String path;
    /**
     * field to store file access mode. true-public; false-private
     */
    private boolean isPublic;
    /**
     * @return access date
     */
    public Date getAccessDate() {
        return accessDate;
    }

    /**
     * set access date
     */

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    /**
     * @return file path on the Internet
     */
    public String getPath() {
        return path;
    }
    /**
     * set file path on the Internet
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * @return access mode
     */
    public boolean isPublic() {
        return isPublic;
    }
    /**
     * set the access mode
     */
    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    /**
     * Конструктор - создание нового объекта с определенными значениями
     * @param accessDate - date of access
     * @param path - file path
     * @param isPublic-access mode
     */
    public PlainTextRecord(Date accessDate, String path, boolean isPublic) {
        this.accessDate = accessDate;
        this.path = path;
        this.isPublic = isPublic;
    }


    /**
     * @return a copy of this record
     */
    @Override
    public MyFileRecord clone() throws CloneNotSupportedException {
        PlainTextRecord rec= (PlainTextRecord) super.clone();
        rec.accessDate= (Date) accessDate.clone();
        rec.path=new String(path);
        return  rec;
    }

    @Override
    public String toString() {
        return new String(String.valueOf(accessDate)+" "+path+" "+ String.valueOf(isPublic));
    }
}
