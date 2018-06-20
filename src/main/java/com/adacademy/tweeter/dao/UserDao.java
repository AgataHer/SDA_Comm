package com.adacademy.tweeter.dao;

import com.adacademy.tweeter.model.User;
import com.sdacademy.utils.HibernateUtils;
import lombok.NonNull;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao<User>{
    public static final String USER_SESSION = "mySessionCookie";
    private static UserDao instance;

    private UserDao(){
        super();
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }

    public static UserDao getInstance(){
        if (instance==null){
            instance = new UserDao();
        }
        return instance;
    }

    /**
     * Method check data in login operation.
     * @param email
     * @param password
     * @return User
     */
    public Optional<User> checkUserByEmailAndPAssword(final @NonNull String email, final @NonNull String password){
        Session session = HibernateUtils.getHibernateSession();
        try {
            String hql = "from User u where upper(u.email) = upper(:email) and u.password = :password";
            List<User> users = session.createQuery(hql)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .list();

            if (users.size() == 1) {
                return Optional.of(users.get(0));
            }
            return Optional.empty();
        } catch (HibernateException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
