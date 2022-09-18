package org.glopusni.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if (userName != null || password != null) {
            if (!userName.equals("root") || !password.equals("pa$$word")) {
                RequestDispatcher dispatcher = request
                        .getServletContext()
                        .getRequestDispatcher("/");
                dispatcher.forward(request, response);
                return;
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", userName);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession();

        String userName = (String) session.getAttribute("username");
        if (userName == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
            return;
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        StringBuilder sb = new StringBuilder("<!DOCTYPE html>\n<html>\n");
        sb.append("<head><title>Home</title></head><body>\n");
        sb.append("<h3>HOME PAGE</h3><br><br><div>Welcome <b>");
        sb.append(userName);
        sb.append("<div><a href='Logout'>Logout</a></div>\n</body></html>");

        out.write(sb.toString());
    }
}
