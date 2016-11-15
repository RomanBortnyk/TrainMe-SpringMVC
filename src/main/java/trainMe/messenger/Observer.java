package trainMe.messenger;


import trainMe.model.Message;

/**
 * Created by romab on 10/28/16.
 */
public interface Observer {

    void update(Message message);

}
