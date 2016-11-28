package trainMe.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import trainMe.dao.implementation.DisciplineUserLinkDao;
import trainMe.dao.implementation.FeedbackDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  UserDao userDao;

  @RequestMapping(value = {"login"}, method = GET)
  public String home() {
    return "index";
  }

  @RequestMapping(value = "registration",method = GET)
  public String toRegistrationPage() {
    return "registration";
  }

  @RequestMapping(value = "conversations",method = GET)
  public String toConversationPage() {
    return "conversations";
  }

  @RequestMapping(value = "search",method = GET)
  public String toSearchPage() {
    return "searchPage";
  }


  @RequestMapping(value = "login", method = POST)
  public String login (Model model){

      return "redirect:/profile/me";

  }

  @RequestMapping(value = "profile/me", method = GET)
  public String redirectToAuthentictedUserProfile(Model model){

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    User user = authentication == null ? null : userDao.read( authentication.getName() );

    model.addAttribute("authenticatedUser", user);

    return "userPage";
  }


  @RequestMapping(value = "test" , method = GET)
  public String test(){

    return "TEST";
  }


  @RequestMapping(value="/logout", method = GET)
  public String logout (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    return "redirect:/login?logout";
  }

}
