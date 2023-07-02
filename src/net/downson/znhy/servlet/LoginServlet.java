package net.downson.znhy.servlet;

import net.downson.znhy.bean.OneThread;
import net.downson.znhy.bean.User;
import net.downson.znhy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.sql.SQLException;

@ServerEndpoint("/LoginServlet")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);


    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("nuse");
        UserService userService = new UserService();
        User user = null;
        try {
            user = userService.login(userName, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if (user != null) {
//            request.getSession().setAttribute("user",user);
            request.getRequestDispatcher("1.html").forward(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        OneThread thread = null;
        thread = new OneThread(session);
        thread.start();
    }
    @OnError
    public void onError(Session session, Throwable thr)
    {

    }

}