package com.adacademy.tweeter.dao;

import com.adacademy.tweeter.model.Tweet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TweetDao extends AbstractDao<Tweet>{

    private static TweetDao instance;

    private TweetDao(){
        super();
    }

    @Override
    protected Class<Tweet> getClazz() {
        return Tweet.class;
    }

    public static TweetDao create(){
        if (instance==null){
            instance = new TweetDao();
        }
        return instance;
    }
}
