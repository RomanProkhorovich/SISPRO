package com.example.filesdb.Store.Model;
import lombok.*;

/**
 * store one CVS record: file data, file version,file name
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public  class CSVRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String fileRecord;
    private int version;
    private String name;
    private final char separator=';';


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
