package trainMe.messenger.controllers;



import com.google.gson.Gson;
import trainMe.messenger.Database;
import trainMe.messenger.NewMessageListener;
import trainMe.model.User;


import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by romab on 10/27/16.
 */
@WebServlet(urlPatterns = "/sendEventListenerRequest", asyncSupported = true)
public class SendEventController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        String currentUserLogin = request.getParameter("login");

        AsyncContext asyncContext = request.startAsync();
        asyncContext.setTimeout(0);


        ServletContext servletContext = request.getServletContext();
        Database database = (Database) servletContext.getAttribute("database");

        User currentUser = (User)request.getSession().getAttribute("currentSessionUser");
        database.getWaitingUsers().add(currentUser);

        NewMessageListener newMsgListener = new NewMessageListener();

        database.registerObserver(newMsgListener);

        newMsgListener.justWait(currentUser);


        HttpServletResponse peer = (HttpServletResponse) asyncContext.getResponse();
        String message = new Gson().toJson(newMsgListener.getCurrentMessage());
        response.getWriter().write(message);

//        peer.getWriter().write(new JSONArray().put(newMsgListener.getCurrentUser().getFirstName() +" :"+ newMsgListener.getMessageText()).toString());
        peer.setStatus(HttpServletResponse.SC_OK);
        peer.setContentType("application/json");
        asyncContext.complete();

        database.getWaitingUsers().remove(currentUser);
        database.getObservers().remove(newMsgListener);
//
//
//

    }
}
