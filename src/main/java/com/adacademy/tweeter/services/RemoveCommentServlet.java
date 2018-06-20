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
import java.util.Optional;

@WebServlet(name = "removeCommentServlet", value = "/removeComment")
public class RemoveCommentServlet extends HttpServlet {
    private CommentDao commentDao;
    public RemoveCommentServlet(){
        commentDao = CommentDao.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Long id = Long.valueOf(req.getParameter("id"));
        final Optional<Long> userId= Utils.getUserIdFromSession(req);
        if (id != null){
            final Long lId = Long.valueOf(id);
            Optional<Comments> comment = commentDao.get(lId);
            if (comment.isPresent() && userId.isPresent()){
                if (comment.get().getAuthor().getId().equals(userId.get())){
                    commentDao.remove(comment.get());
                }
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
