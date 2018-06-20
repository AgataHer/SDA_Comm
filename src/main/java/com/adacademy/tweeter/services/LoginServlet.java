package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.UserDao;
import com.adacademy.tweeter.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet responsible for login action.
 */
@WebServlet(name="loginServlet", value="/login")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    public LoginServlet(){
        userDao=UserDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String email = req.getParameter("email");
        final String pass = req.getParameter("pass");
        Optional<User> user = userDao.checkUserByEmailAndPAssword(email,pass);
        if (user.isPresent()){
            //TODO: dodać sesje użytkownika!
            resp.addCookie(new Cookie(UserDao.USER_SESSION, user.get().getId().toString()));
            resp.sendRedirect("index.jsp");
        }else{
            resp.sendRedirect("index.jsp?err=nouser");
        }
    }
}
