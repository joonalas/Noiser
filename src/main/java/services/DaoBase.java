package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import util.DbUtil;


//Abstract parent for Db services implementing basic CRUD functionality

public abstract class DaoBase<T> {
    
    
    private Class<T> clazz;
    
    
    public void setClazz(Class< T> clazzToSet) {
        this.clazz = clazzToSet;
    }
    
    //------------------------Create---------------------------------
    public void insert(T object) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(object);
            transaction.commit();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            transaction.rollback();
        } finally {
            em.close();
        }
    }
    //------------------------Read-----------------------------------
    public List<T> getAll() {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        List<T> objects = null;
        try {
            TypedQuery<T> query = em.createNamedQuery(clazz.getSimpleName() + ".findAll", clazz);
            objects = query.getResultList();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            objects = null;
        } finally {
            em.close();
        }
        return objects;
    }
    
    public T getById(int id) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        T object = em.find(clazz, id);
        em.close();
        return object;
    }
    
    //------------------------Update---------------------------------
    public void update(T object) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(object);
            transaction.commit();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            transaction.rollback();
        } finally {
            em.close();
        }
    }
    
    //-----------------------Delete-----------------------------------
    public void delete(T object) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.merge(object));
            transaction.commit();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            transaction.rollback();
        } finally {
            em.close();
        }
    }
     
    
}
