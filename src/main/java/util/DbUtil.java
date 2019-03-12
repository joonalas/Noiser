package util;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class DbUtil {
    public static EntityManager getEntityManager(String persistenceUnit) {
        return Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
    }
}
