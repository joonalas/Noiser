package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.SoundFile;
import util.DbUtil;


public class SoundFileDao extends DaoBase<SoundFile> {
    private static SoundFileDao instance = null;

    private SoundFileDao() {
        setClazz(SoundFile.class);
    }
    
    public static SoundFileDao getInstance() {
        if(instance == null) {
            instance = new SoundFileDao();
        }
        return instance;
    }
    
    public List<SoundFile> getBySong(int song) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        List<SoundFile> files = null;
        try {
            TypedQuery<SoundFile> query = em.createNamedQuery("SoundFile.findBySong", SoundFile.class);
            query.setParameter("song", song);
            files = query.getResultList();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            files = null;
        } finally {
            em.close();
        }
        return files;
        
    }
    
}
