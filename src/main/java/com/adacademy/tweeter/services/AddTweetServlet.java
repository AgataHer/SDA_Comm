package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.TweetDao;
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

@WebServlet(name ="addTweetServlet", value = "/addTweet")
public class AddTweetServlet extends HttpServlet {
    private UserDao userDao;
    private TweetDao tweetDao;

    public AddTweetServlet(){
        userDao = UserDao.getInstance();
        tweetDao = TweetDao.create();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String message = req.getParameter("message");
        final Optional<Long> userID = Utils.getUserIdFromSession(req);
        if (userID.isPresent()) {
            Optional<User> user = userDao.get(userID.get());
            if (user.isPresent()) {
                Tweet tweet = Tweet.builder()
                        .message(message)
                        .author(user.get())
                        .creationTS(System.currentTimeMillis())
                        .build();
                tweetDao.add(tweet);
            }
        }
        resp.sendRedirect("/index.jsp");
    }
}
