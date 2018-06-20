package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.CommentDao;
import com.adacademy.tweeter.dao.TweetDao;
import com.adacademy.tweeter.dao.UserDao;
import com.adacademy.tweeter.model.Comments;
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

@WebServlet(name = "addCommentServlet", value = "/addComment")
public class AddCommentServlet extends HttpServlet {
    private UserDao userDao;
    private TweetDao tweetDao;
    private CommentDao commentDao;

    public AddCommentServlet() {
    userDao = UserDao.getInstance();
    commentDao = CommentDao.create();
    tweetDao= TweetDao.create();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String message = req.getParameter("message");
        final Optional<Long> userID = Utils.getUserIdFromSession(req);
        final Optional<Tweet> tweet= tweetDao.get(Long.valueOf(req.getParameter("tweetId")));

        if (userID.isPresent()) {
            Optional<User> user = userDao.get(userID.get());
            if (user.isPresent()) {
                Comments comments = Comments.builder()
                        .text(message)
                        .Tweet(tweet.get())
                        .author(user.get())
                        .creationTS(System.currentTimeMillis())
                        .build();
                //tweet.get().getComments().add(comments);
                commentDao.add(comments);
            }
        }
        resp.sendRedirect("/index.jsp");
    }
}

