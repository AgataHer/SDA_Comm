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

@WebServlet(name="editCommentServlet", value = "/editComment")
public class EditCommentServlet extends HttpServlet {
    private CommentDao commentDao;
    public EditCommentServlet(){
        commentDao = CommentDao.create();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String message = req.getParameter("message");
        final Long id = Long.valueOf(req.getParameter("commentId"));
        final Optional<Long> userId= Utils.getUserIdFromSession(req);

        Optional<Comments> commentFromDb = commentDao.get(id);
        if (commentFromDb.isPresent()) {
            final Comments comment = commentFromDb.get();
            if (userId.get().equals(comment.getAuthor().getId())){
                comment.setText(message);
                comment.setCreationTS(System.currentTimeMillis());
                commentDao.update(comment);
            }
        }
        resp.sendRedirect("index.jsp");
    }
}
