package trainMe.dao.interfaces;

import trainMe.model.Item;

import java.util.List;

/**
 * Created by romab on 10/2/16.
 */

public interface GenericDao<T extends Item> {

//    /** Persist the newInstance object into database */
//    void create(T newObject) throws DataAccessLayerException;

    /** Save changes made to a persistent object.  */
    T create(T transientObject) ;
    T update(T transientObject) ;

    /** Retrieve an object that was previously persisted to the database using
     *   the indicated id as primary key
     */
    T read(Class<T> clazz, int id);

    /** Remove an object from persistent storage in the database */
    void delete(T persistentObject);

    List readAll(Class<T> clazz);

}
