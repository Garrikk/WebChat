/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.chat.webchat.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ua.chat.webchat.core.service.ChatServiceImpl;
import ua.chat.webchat.core.entity.ChatPerson;

@WebServlet(name = "UserLog", urlPatterns = {"/UserLog"})
public class UserLog extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<ul id=\"userLog\">");

            ChatServiceImpl chat = new ChatServiceImpl();
            ChatPerson[] users = chat.getOnlineUsers();

            for (int i = 0; i < users.length; i++) {
                HttpSession session = request.getSession();

                if (session.getAttribute("user").equals(users[i].nickname)) {
                    out.println("<li>" + "<b>" + users[i].nickname + "(you)" + "</b>" + "</li>");
                } else {
                    out.println("<li>" + users[i].nickname + "</li>");
                }
            }
            out.println("</ul>");
            out.close();

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
