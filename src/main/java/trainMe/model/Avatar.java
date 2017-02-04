package trainMe.model;

import javax.persistence.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by romab on 10/2/16.
 */
@javax.persistence.Entity
@Table(name = "avatar")
public class Avatar implements Entity {

    private int id;
    private byte[] image;

    public Avatar() {

    }

    public Avatar(byte[] image) {
        this.image = image;
    }

    public Avatar(File image) {
        setImage(image);
    }

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "image", nullable = false)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public void setImage (File file){

        byte[] bFile = new byte[(int) file.length()];

        try {

            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        this.image = bFile;

    }

}
