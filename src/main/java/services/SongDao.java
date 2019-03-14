package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Song;
import util.DbUtil;


public class SongDao extends DaoBase<Song> {
    private static SongDao instance = null;

    private SongDao() {
        setClazz(Song.class);
    }
    
    public static SongDao getInstance() {
        if(instance == null) {
            instance = new SongDao();
        }
        return instance;
    }
    
    public List<Song> getByAlbumName(int album) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        List<Song> songs = null;
        try {
            TypedQuery<Song> query = em.createNamedQuery("Song.findByAlbum", Song.class);
            query.setParameter("album", album);
            songs = query.getResultList();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            songs = null;
        } finally {
            em.close();
        }
        return songs;
        
    }
    
}
