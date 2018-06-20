package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.UserDao;
import com.adacademy.tweeter.model.Tweet;
import com.adacademy.tweeter.model.User;
import com.sdacademy.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name="editUserServlet", value = "/editUser")
public class EditUserServlet extends HttpServlet {

    private UserDao userDao;

    public EditUserServlet(){
        userDao = UserDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String email = req.getParameter("email");
        final String pass = req.getParameter("pass");
        final String nick = req.getParameter("nick");
        final Long TS = System.currentTimeMillis();
        final Optional<User> user= userDao.get(Utils.getUserIdFromSession(req).get());

        if (user.isPresent()){
            final User newUser = user.get();
            newUser.setId(user.get().getId());
            newUser.setNick(nick);
            newUser.setEmail(email);
            newUser.setPassword(pass);
            newUser.setCreationTS(TS);
            userDao.update(newUser);

            resp.sendRedirect("index.jsp");
        }else {
            resp.sendRedirect("index.jsp");
        }
    }
}