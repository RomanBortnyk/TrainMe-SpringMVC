package trainMe.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trainMe.dao.implementation.DisciplineUserLinkDao;
import trainMe.dao.implementation.FeedbackDao;
import trainMe.model.User;
import trainMe.services.CheckUsersRegistrationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

  @Autowired
  CheckUsersRegistrationService registrationService;
  @Autowired
  FeedbackDao feedbackDao;
  @Autowired
  DisciplineUserLinkDao disUsrlinkDao;

  @RequestMapping(method = GET)
  public String home(Model model) {
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


  @RequestMapping(value = "signIn", method = POST)
  public String signIn (HttpServletRequest request ,Model model,
                        @RequestParam(value="login", required=false) String login,
                        @RequestParam(value="password", required=false) String password){

    if (registrationService.check(login, password)){

      HttpSession session = request.getSession(true);
      User user = registrationService.getRegisteredUser();

      session.setAttribute("currentSessionUser", user);

      return "redirect:/userPage";

    }else {
      model.addAttribute("error", registrationService.getErrorMessage());
      return "notifications/signInError";
    }

  }

  @RequestMapping(value = "userPage", method = GET)
  public String redirect(){
    return "userPage";
  }

  @RequestMapping(value = "authenticationError" , method = GET)
  public String authError(){
    return "notifications/authenticationError";
  }


}
