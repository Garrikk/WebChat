package ua.chat.webchat.controller;

import java.util.Map;
import java.io.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;

import ua.chat.webchat.core.Chat;
import ua.chat.webchat.core.ChatPerson;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nickname = request.getParameter("nickname").toLowerCase();
        String password = request.getParameter("password");

        if (nickname == "" || password == "") {
            String errorString = "You should fill in both values - nickname and password.";
            request.setAttribute("IsNoAllowed", errorString);
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        } else {
            @SuppressWarnings("unchecked")
            Map parameters = request.getParameterMap();

            boolean isLoginEvent = parameters.containsKey("btnLogin");
            boolean isRegisterEvent = parameters.containsKey("btnRegister");
            boolean isSendEvent = parameters.containsKey("btnSend");
            boolean isLogoutEvent = parameters.containsKey("btnLogout");

            Chat chat = new Chat();

            if (isLoginEvent == true) {
                int result = chat.login(nickname, password);

                if (result == 0) {
                    request.setAttribute("Username", nickname);
                    request.setAttribute("Password", password);
                    request.setAttribute("UserList", chat.getOnlineUsers());
                    request.setAttribute("AllMessages", chat.getAllMessages());
                    request.getRequestDispatcher("/chat.jsp").forward(request, response);
                } else {
                    String errorString = "Wrong username or password. Also it is possible that current user has been already logged in. Please try to register a new user.";
                    request.setAttribute("IsNoAllowed", errorString);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }

            if (isRegisterEvent == true) {
                int result = chat.registerNewUser(nickname, password);

                if (result == 0) {
                    request.setAttribute("Username", nickname);
                    request.setAttribute("Password", password);
                    request.setAttribute("UserList", chat.getOnlineUsers());
                    request.setAttribute("AllMessages", chat.getAllMessages());
                    request.getRequestDispatcher("/chat.jsp").forward(request, response);
                } else {
                    String errorString = "Such user is already existed. Please try another one.";
                    request.setAttribute("IsNoAllowed", errorString);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            }

            if (isSendEvent == true) {
                String message = request.getParameter("outcomingmess");
                chat.postMessage(message, nickname);
                request.setAttribute("Username", nickname);
                request.setAttribute("Password", password);
                request.setAttribute("UserList", chat.getOnlineUsers());
                request.setAttribute("AllMessages", chat.getAllMessages());
                request.getRequestDispatcher("/chat.jsp").forward(request, response);
            }
            if (isLogoutEvent == true) {
                chat.logout(nickname, password);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }
}
