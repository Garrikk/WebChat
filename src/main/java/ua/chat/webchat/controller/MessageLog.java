/**
 *
 */
package ua.chat.webchat.controller;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import javax.servlet.annotation.WebServlet;
import ua.chat.webchat.core.*;

@WebServlet(name = "MessageLog", urlPatterns = {"/MessageLog"})
public class MessageLog extends HttpServlet {

    private static final long serialVersionUID = -8801342575055798322L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<ul id=\"chatLog\">");

        Chat chat = new Chat();
        ChatMessage[] messages = chat.getAllMessages();

        for (int i = 0; i < messages.length; i++) {

            out.println("<li>" + "[" + DateFormat.getDateTimeInstance().format(messages[i].timestamp)
                    + "]" + messages[i].author.nickname + ": " + messages[i].text
                    + "</li>");
        }

        out.println("</ul>");
        out.close();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Chat chat = new Chat();

        String nickname = request.getParameter("nickname").toLowerCase();
        String message = request.getParameter("outcomingmess");
        chat.postMessage(message, nickname);
    }
}
