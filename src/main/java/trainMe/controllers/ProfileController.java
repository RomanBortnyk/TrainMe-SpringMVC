package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainMe.dao.implementation.UserDao;
import trainMe.jsonObjects.ReceivedMessageJson;
import trainMe.model.User;
import trainMe.services.MessengerService;
import trainMe.services.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by romab on 11/14/16.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    MessengerService messengerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String renderProfilePage ( Model model, @PathVariable ("id") int userId){

//        User user = userService.readById(userId);
        model.addAttribute("user", userService.readById(userId));

        return "profile";

    }

    @RequestMapping(value = "/me", method = GET)
    public String redirectToAuthentictedUserProfile(Model model){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authentication == null ? null : userService.readByLogin( authentication.getName() );

        model.addAttribute("authenticatedUser", user);

        return "userPage";
    }

    @RequestMapping(value = "/message", method = POST)
    public String handleNewMessage (@RequestBody ReceivedMessageJson incoming, Principal principal){

        messengerService.checkDoesChatExistAndSendToUser(incoming,principal.getName());

        return "success";

    }



}
