package com.example.Store.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PlainTextRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

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
     * @return a copy of this record
     */
    @Override
    public PlainTextRecord clone() throws CloneNotSupportedException {
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
