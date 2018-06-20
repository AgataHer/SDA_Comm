package com.adacademy.tweeter.dao;

import com.adacademy.tweeter.model.Comments;

public class CommentDao extends AbstractDao<Comments>{

    private static CommentDao instance;

    private CommentDao(){
        super();
    }
    public static CommentDao create(){
        if (instance==null){
            instance = new CommentDao();
        }
        return instance;
    }

    @Override
    protected Class<Comments> getClazz() {
        return Comments.class;
    }
}
