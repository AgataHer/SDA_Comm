package com.adacademy.tweeter.services;

import com.adacademy.tweeter.dao.CommentDao;
import com.adacademy.tweeter.dao.TweetDao;
import com.adacademy.tweeter.model.Comments;
import com.adacademy.tweeter.model.Tweet;
import com.sdacademy.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "removeTweetServlet", value = "/removeTweet")
public class RemoveTweetServlet extends HttpServlet {
    private TweetDao tweetDao;
    private CommentDao commentDao;
    public RemoveTweetServlet(){
        tweetDao = TweetDao.create();
        commentDao = CommentDao.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.valueOf(req.getParameter("id"));
        final Optional<Long> userId= Utils.getUserIdFromSession(req);
        if (id != null){
            final Long lId = Long.valueOf(id);
            Optional<Tweet> tweet = tweetDao.get(lId);
            //List<Comments> comments = tweet.get().getComments();

            if (tweet.isPresent() && userId.isPresent()){
//                if (!comments.isEmpty()){
//                    comments.clear();
//                }
                if (tweet.get().getAuthor().getId().equals(userId.get())){
                    tweetDao.remove(tweet.get());
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
