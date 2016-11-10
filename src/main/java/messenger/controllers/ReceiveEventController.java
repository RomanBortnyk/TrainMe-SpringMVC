package messenger.controllers;

import messenger.Database;
import trainMe.dao.implementation.ChatDao;
import trainMe.dao.implementation.UserDao;
import trainMe.model.Chat;
import trainMe.model.Message;
import trainMe.model.User;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by romab on 10/27/16.
 */
@WebServlet("/sendEvent/*")
public class ReceiveEventController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletContext sctx = request.getServletContext();
        Database database = (Database)sctx.getAttribute("database");

        User messageAuthor = (User) request.getSession().getAttribute("currentSessionUser");
        UserDao userDao = new UserDao();

        Message receivedMessage = new Message();

        receivedMessage.setUser(messageAuthor);
        receivedMessage.setText(request.getParameter("messageText"));

        Date today = new Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        receivedMessage.setCreatedAt(timestamp);

        ChatDao chatDao = new ChatDao();

        String pathParts [] = request.getPathInfo().split("/");

        if (pathParts[1].equals("message")){

            receivedMessage.setChat(chatDao.read(Integer.valueOf(request.getParameter("chatId"))));

        }else {

            int destUsrId = Integer.parseInt(request.getParameter("destinationUserId"));
            if (chatDao.isExistByUsersId(messageAuthor.getId(), destUsrId)){
                receivedMessage.setChat(chatDao.readByUsersIds(messageAuthor.getId(), destUsrId));
            }else {

                receivedMessage.setChat(chatDao.create(new Chat(messageAuthor, userDao.read(destUsrId))));
            }
        }


        database.addMessage(receivedMessage);

    }
}
