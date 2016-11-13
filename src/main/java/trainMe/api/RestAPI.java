package trainMe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.api.apiModel.DisciplineApiModel;
import trainMe.api.apiModel.FeedbackApiModel;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.dao.implementation.DisciplineUserLinkDao;
import trainMe.dao.implementation.FeedbackDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Discipline;
import trainMe.model.DisciplineUserLink;
import trainMe.model.Feedback;

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

    public ArrayList<FeedbackApiModel> getFeedbacksByUserId(int id){

        ArrayList<FeedbackApiModel> result = new ArrayList<FeedbackApiModel>();
        ArrayList<Feedback> feedbacks = (ArrayList<Feedback>) feedbackDao.getUsersFeedbacks(id);

        for (Feedback feedback: feedbacks){
            result.add(new FeedbackApiModel(feedback.getAuthor().getId(),feedback.getAuthor().getFirstName(),
                    feedback.getAuthor().getLastName(),feedback.getText()));
        }

        return result;
    }

    public List getDisciplinesByUserId(int id){

        ArrayList<DisciplineApiModel> result = new ArrayList<DisciplineApiModel>();
        List disciplinesLinks = discUsrLnkDao.getUsersDisciplineLinks(id);

        for (Object discLink : disciplinesLinks){
            Discipline discipline = ((DisciplineUserLink) discLink).getDiscipline();
            result.add(new DisciplineApiModel(discipline.getId(),discipline.getName()));
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
                if (((Discipline)discipline).getName().equals(((DisciplineApiModel)currentDiscipline).getName())) i++;

            }
            if (i==0) result.add(((Discipline)discipline).getName());
        }
        Collections.sort(result);
        return result;
    }

}
