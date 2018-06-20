package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.TweetDao;
import com.adacademy.tweeter.model.Tweet;
import com.sdacademy.utils.Utils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name="editTweetServlet", value = "/editTweet")
public class EditTweetServlet extends HttpServlet {
    private TweetDao tweetDao;
    public EditTweetServlet(){
        tweetDao = TweetDao.create();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String message = req.getParameter("message");
        final Long id = Long.valueOf(req.getParameter("tweetId"));
        final Optional<Long> userId= Utils.getUserIdFromSession(req);

        Optional<Tweet> tweetFromDb = tweetDao.get(id);
        if (tweetFromDb.isPresent()) {
            final Tweet tweet = tweetFromDb.get();
            if (userId.get().equals(tweet.getAuthor().getId())){
                tweet.setMessage(message);
                tweetDao.update(tweet);
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
