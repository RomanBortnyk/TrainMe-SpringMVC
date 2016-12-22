package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import trainMe.model.Discipline;
import trainMe.model.User;
import trainMe.services.AvatarService;
import trainMe.services.DescriptionService;
import trainMe.services.DisciplineService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by romab on 11/13/16.
 */
@Controller
@RequestMapping("/modify/")
public class ModifyUsersDataController {


    @Autowired
    DisciplineService disciplineService;
    @Autowired
    AvatarService avatarService;
    @Autowired
    DescriptionService descriptionService;

    @RequestMapping(value = "avatar", method = RequestMethod.POST)
    public String updateAvatar (@RequestParam("newAvatar") MultipartFile file) throws IOException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        avatarService.update(authentication.getName(), file);
        return "redirect:/profile/me";
    }



    @RequestMapping(value = "discipline/add", method = RequestMethod.POST)
    public String addDiscipline(@RequestParam(value="disciplineToAdd", required=false) String disciplineToAdd){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        disciplineService.addDiscipline(authentication.getName(), disciplineToAdd);

        return "redirect:/profile/me";

    }

    @RequestMapping(value = "discipline/remove", method = RequestMethod.POST)
    public String removeDiscipline(@RequestParam(value="disciplineToRemove", required=false) String disciplineToRemove){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        disciplineService.removeDiscipline(authentication.getName(), disciplineToRemove);

        return "redirect:/profile/me";

    }

    @RequestMapping(value = "description", method = RequestMethod.POST)
    public String updateDescription(HttpServletRequest request,
                                   @RequestParam(value="newDescription", required=false) String newDescription){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        descriptionService.update(authentication.getName(), newDescription);

        return "redirect:/profile/me";

    }


}
