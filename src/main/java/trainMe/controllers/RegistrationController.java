package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trainMe.model.User;
import trainMe.services.UserRegistrationService;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by romab on 11/11/16.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    UserRegistrationService registrationService;

    @RequestMapping(method = POST)
    public String registerUser(Model model,
                              @RequestParam(value="firstName", required=false) String firstName,
                              @RequestParam(value="lastName", required=false) String lastName,
                              @RequestParam(value="birthdayDate", required=false) String birthdayDate,
                              @RequestParam(value="email", required=false) String email,
                              @RequestParam(value="login", required=false) String login,
                              @RequestParam(value="password", required=false) String password,
                              @RequestParam(value="optionsRadios", required=false) String userType) {

        User newUser = new User(firstName,lastName,birthdayDate,login,password,email,userType);


        if (registrationService.register(newUser)){
            return "notifications/registrationStatus";
        }else {
            model.addAttribute("error",registrationService.getErrorMessage());
            return "notifications/registrationStatus";
        }
    }
}
