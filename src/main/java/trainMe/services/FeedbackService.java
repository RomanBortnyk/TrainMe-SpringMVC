package trainMe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainMe.dao.implementation.FeedbackDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Feedback;
import trainMe.model.User;

import java.util.ArrayList;

/**
 * Created by romab on 11/14/16.
 */
@Service
public class FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    @Autowired
    private UserDao userDao;


    public Feedback add(int destUsrId, User currentUser, String text){

        Feedback feedback = new Feedback(currentUser,userDao.read(destUsrId),text);

        return feedbackDao.create(feedback);
    }

    public Feedback getFeedbackById(int feedbackId){

        return feedbackDao.read(feedbackId);
    }

    public ArrayList<Feedback> getFeedbacksByUserId(int id) {

        ArrayList<Feedback> feedbacks = (ArrayList<Feedback>) feedbackDao.getUsersFeedbacks(id);

        return feedbacks;
    }


}
