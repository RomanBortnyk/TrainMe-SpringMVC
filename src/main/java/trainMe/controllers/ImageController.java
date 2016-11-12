package trainMe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import trainMe.dao.implementation.DisciplineDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Discipline;
import trainMe.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by romab on 11/12/16.
 */
@Controller
@RequestMapping("/image/")
public class ImageController {

    @Autowired
    UserDao userDao;

    @Autowired
    DisciplineDao disciplineDao;

    @RequestMapping(value="avatar/{id}", method = RequestMethod.GET)
    public void getAvatar (HttpServletResponse response,
                       @PathVariable("id") int id) throws IOException{

        User user = userDao.read(id);

        byte[] content = user.getAvatar().getImage();
        if (content == null) return;
        response.setContentLength(content.length);
        response.getOutputStream().write(content);

    }

    @RequestMapping(value="icon/{id}", method = RequestMethod.GET)
    public void getIcon (HttpServletResponse response,
                       @PathVariable("id") int id) throws IOException{

        Discipline discipline = disciplineDao.read(id);

        byte[] content = discipline.getIcon();
        response.setContentLength(content.length);
        response.getOutputStream().write(content);

    }
}
