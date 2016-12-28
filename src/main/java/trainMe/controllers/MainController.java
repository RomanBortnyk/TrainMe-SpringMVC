package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import trainMe.dao.implementation.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class MainController {


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

  @RequestMapping(value = "expotential-backoff",method = GET)
  public String toExpotentialBackoffSimulation() {
    return "expotentialBackoff";
  }


  @RequestMapping(value = "login", method = POST)
  public String login (Model model){

      return "redirect:/profile/me";

  }


  @RequestMapping(value = "test" , method = GET)
  public String test(){

    return "TEST";
  }

  @RequestMapping(value="/logout", method = POST)
  public String logout (HttpServletRequest request, HttpServletResponse response) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null){
      new SecurityContextLogoutHandler().logout(request, response, auth);
    }

    return "redirect:/login?logout";
  }

}
