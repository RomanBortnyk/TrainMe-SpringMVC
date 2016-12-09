package trainMe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.api.apiModel.DisciplineApiType;
import trainMe.dao.implementation.*;
import trainMe.model.*;

import java.util.*;

/**
 * Created by romab on 11/13/16.
 */
@Service
public class RestAPIService {

    @Autowired
    UserDao userDao;
    @Autowired
    FeedbackDao feedbackDao;
    @Autowired
    DisciplineUserLinkDao discUsrLnkDao;
    @Autowired
    DisciplineDao disciplineDao;
    @Autowired
    ChatDao chatDao;
    @Autowired
    MessageDao messageDao;

    public Feedback getFeedbackById(int feedbackId){

        return feedbackDao.read(feedbackId);
    }

    public ArrayList<Feedback> getFeedbacksByUserId(int id) {

        ArrayList<Feedback> feedbacks = (ArrayList<Feedback>) feedbackDao.getUsersFeedbacks(id);

        return feedbacks;
    }

    public List getDisciplinesByUserId(int id) {

        ArrayList<DisciplineApiType> result = new ArrayList<DisciplineApiType>();
        List disciplinesLinks = discUsrLnkDao.getUsersDisciplineLinks(id);

        for (Object discLink : disciplinesLinks) {
            Discipline discipline = ((DisciplineUserLink) discLink).getDiscipline();
            result.add(new DisciplineApiType(discipline.getId(), discipline.getName()));
        }

        return result;

    }

    public List getSortedDisciplinesNamesToAdd(int id) {
        List currentDisciplines = getDisciplinesByUserId(id);
        List allDisciplinesList = disciplineDao.readAll();

        ArrayList<String> result = new ArrayList<String>();

        //create list of disciplines names
        for (Object discipline : allDisciplinesList) {
            int i = 0;
            for (Object currentDiscipline : currentDisciplines) {
                if (((Discipline) discipline).getName().equals(((DisciplineApiType) currentDiscipline).getName())) i++;

            }
            if (i == 0) result.add(((Discipline) discipline).getName());
        }
        Collections.sort(result);
        return result;
    }

    public List getUsersChatsList(int id) {
        List<Chat> result = chatDao.getUserChats(id);
//        ArrayList<ChatApiType> result = new ArrayList<ChatApiType>();
//        for (Chat chat : chats) {
//            result.add(new ChatApiType(chat.getId(), chat.getUser2().getFirstName(),
//                    chat.getUser2().getLastName(), chat.getUser2().getId()));
//        }
        return result;
    }

    public List getUsersChatsList(String login) {
        List<Chat> result = chatDao.getUserChats(login);

        return result;
    }


    public List getChatMessages(int id) {

        List<Message> result = messageDao.getChatMessages(id);

        return result;

    }

    public ArrayList<String> getDisciplinesNamesStartsWithParam(String parameter) {

        List<Discipline> disciplineList = disciplineDao.readAll();
        ArrayList<String> result = new ArrayList<String>();

        //create result list
        Iterator it = disciplineList.iterator();
        while (it.hasNext()) {
            Discipline current = (Discipline) it.next();
            if (parameter != null) {
                if (current.getName().startsWith(parameter.toLowerCase())) {
                    result.add(current.getName());
                }
            }
        }

        return result;
    }

    public ArrayList<String> getUsersFullNamesStartsWithParam(String parameter) {

        List<Discipline> disciplineList = disciplineDao.readAll();
        ArrayList<String> result = new ArrayList<String>();

        List usersFullNamesList = userDao.readAllFullNames();

        //create list of users  full names
        for (Object listObject: usersFullNamesList){
            Object[] oarray = (Object[]) listObject;
            String[] names = Arrays.asList(oarray).toArray(new String[oarray.length]);

            if ( names[0].toLowerCase().startsWith(parameter.toLowerCase() )
                    || names[1].toLowerCase().startsWith(parameter.toLowerCase() )){
                result.add(names[1]+" "+ names[0]);
            }

        }

        return result;
    }

}
