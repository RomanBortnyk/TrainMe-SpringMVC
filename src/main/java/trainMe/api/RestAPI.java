package trainMe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.api.apiModel.ChatApiType;
import trainMe.api.apiModel.DisciplineApiType;
import trainMe.api.apiModel.FeedbackApiType;
import trainMe.api.apiModel.MessageApiType;
import trainMe.dao.implementation.*;
import trainMe.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by romab on 11/13/16.
 */
@Service
public class RestAPI{

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

    public ArrayList<FeedbackApiType> getFeedbacksByUserId(int id){

        ArrayList<FeedbackApiType> result = new ArrayList<FeedbackApiType>();
        ArrayList<Feedback> feedbacks = (ArrayList<Feedback>) feedbackDao.getUsersFeedbacks(id);

        for (Feedback feedback: feedbacks){
            result.add(new FeedbackApiType(feedback.getAuthor().getId(),feedback.getAuthor().getFirstName(),
                    feedback.getAuthor().getLastName(),feedback.getText()));
        }

        return result;
    }

    public List getDisciplinesByUserId(int id){

        ArrayList<DisciplineApiType> result = new ArrayList<DisciplineApiType>();
        List disciplinesLinks = discUsrLnkDao.getUsersDisciplineLinks(id);

        for (Object discLink : disciplinesLinks){
            Discipline discipline = ((DisciplineUserLink) discLink).getDiscipline();
            result.add(new DisciplineApiType(discipline.getId(),discipline.getName()));
        }

        return result;

    }

    public List getSortedDisciplinesNamesToAdd(int id){
        List currentDisciplines = getDisciplinesByUserId(id);
        List allDisciplinesList =  disciplineDao.readAll();

        ArrayList<String> result = new ArrayList<String>();

        //create list of disciplines names
        for (Object discipline: allDisciplinesList){
            int i=0;
            for (Object currentDiscipline: currentDisciplines){
                if (((Discipline)discipline).getName().equals(((DisciplineApiType)currentDiscipline).getName())) i++;

            }
            if (i==0) result.add(((Discipline)discipline).getName());
        }
        Collections.sort(result);
        return result;
    }

    public List getUsersChatsList (int id){
        List<Chat> chats = chatDao.getUserChats (id);
        ArrayList<ChatApiType> result = new ArrayList<ChatApiType>();
        for (Chat chat : chats){
            result.add( new ChatApiType(chat.getId(), chat.getUser2().getFirstName(),
                        chat.getUser2().getLastName() ,chat.getUser2().getId() ));
        }
        return result;
    }

    public List getChatMessages (int id){

        List<Message> messagesList = messageDao.getChatMessages(id);
        List<MessageApiType> result = new ArrayList<MessageApiType>();

        for (Message message: messagesList){
            result.add(new MessageApiType(message.getAuthor().getId() , message.getText(),
                    message.getAuthor().getFirstName(), message.getAuthor().getLastName()));
        }

        return result;

    }

}
