package trainMe.dao.implementation;

import org.springframework.stereotype.Repository;
import trainMe.dao.interfaces.AbstractDao;
import trainMe.model.Avatar;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by romab on 10/2/16.
 */
@Repository
public class AvatarDao extends AbstractDao {

    public List readAll() {
        return super.readAll(Avatar.class);
    }

    public Avatar read(int id) {

        return (Avatar) super.read(Avatar.class, id);
    }

    public void delete(Avatar obj) {
        super.delete(obj);
    }

    public Avatar create (Avatar avatar){
        return (Avatar) super.create(avatar);
    }

    public Avatar createFromFile (File image){

        byte[] bFile = new byte[(int) image.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(image);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Avatar avatar = new Avatar();
        avatar.setImage(bFile);

        return (Avatar) super.create(avatar);
    }

    public Avatar createFromByteArray (byte[] bFile){

        Avatar avatar = new Avatar();
        avatar.setImage(bFile);

        return (Avatar) super.create(avatar);
    }

    // receive image from DB
    public void putAvatarToFile (int id, String path){

        Avatar avatar = this.read(id);
        byte [] bavatar = avatar.getImage();

        try{
            FileOutputStream fos = new FileOutputStream(new File(path));
            fos.write(bavatar);
            fos.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
