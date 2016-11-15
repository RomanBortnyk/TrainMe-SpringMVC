package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainMe.dao.implementation.UserDao;
import trainMe.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by romab on 11/14/16.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String renderProfilePage (HttpServletRequest request, Model model,
                                     @PathVariable ("id") int userId){

        User user = userDao.read(userId);
        model.addAttribute("user", user);

        return "profile";

    }

}
