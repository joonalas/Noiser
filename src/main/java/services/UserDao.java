package services;

import javax.persistence.EntityManager;
import model.User;
import util.DbUtil;
import de.mkammerer.argon2.*;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;


public class UserDao extends DaoBase<User> {
    private static UserDao instance = null;

    private UserDao() {
        setClazz(User.class);
    }
    
    public static UserDao getInstance() {
        if(instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    
    public User getByUsername(String username) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        User user = null;
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter("username", username);
            user = query.getSingleResult();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            user = null;
        } finally {
            em.close();
        }
        return user;
    }
    
    public boolean isValidUser(String username, char[] password) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        boolean valid = false;
        try {
            TypedQuery<User> query = em.createNamedQuery("User.findByUsername", User.class);
            query.setParameter("username", username);
            String hash = query.getSingleResult().getPasswordHash();
            if(argon2.verify(hash, password)) {
                valid = true;
            }
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            valid = false;
        } finally {
            argon2.wipeArray(password);
        }
        
        
        return valid;
    }
    
    
}
