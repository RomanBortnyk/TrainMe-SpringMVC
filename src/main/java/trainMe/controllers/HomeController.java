package trainMe.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @RequestMapping(method = GET)
  public String home(Model model) {
    return "index";
  }

  @RequestMapping(value = "registration",method = GET)
  public String toRegistrationPage() {
    return "registration";
  }

}
