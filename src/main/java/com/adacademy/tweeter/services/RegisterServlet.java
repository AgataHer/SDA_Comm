package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.UserDao;
import com.adacademy.tweeter.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDao;
    public RegisterServlet(){
        userDao = UserDao.getInstance();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String email = req.getParameter("email");
        final String pass = req.getParameter("pass");
        final String pass2 = req.getParameter("pass2");
        final String nick = req.getParameter("nick");
        final Long TS = System.currentTimeMillis();

        if (isNullOrEmpty(nick)){
            resp.sendRedirect("register.jsp?err=nonick");
            return;
        }
        if (isNullOrEmpty(email)){
            resp.sendRedirect("register.jsp?err=noemail");
            return;
        }
        if (isNullOrEmpty(pass)){
            resp.sendRedirect("register.jsp?err=nopass");
            return;
        }
        if (isNullOrEmpty(pass2)){
            resp.sendRedirect("register.jsp?err=nopass");
            return;
        }
        if (!(pass.equals(pass2))){
            resp.sendRedirect("register.jsp?err=differentPasswords");
            return;
        }
        userDao.add(User.builder().email(email).password(pass).nick(nick).creationTS(TS).build());
        resp.sendRedirect("index.jsp");
    }
    public boolean isNullOrEmpty(final String val){
        return(val==null|| val=="");
    }
}
