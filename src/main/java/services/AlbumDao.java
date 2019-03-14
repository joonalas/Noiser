package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Album;
import util.DbUtil;

//CRUD

public class AlbumDao extends DaoBase<Album>{
    
    private static AlbumDao instance = null;

    private AlbumDao() {
        setClazz(Album.class);
    }
    
    public static AlbumDao getInstance() {
        if(instance == null) {
            instance = new AlbumDao();
        }
        return instance;
    }
    
    
    public List<Album> getByArtistName(int artist) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        List<Album> albums = null;
        try {
            TypedQuery<Album> query = em.createNamedQuery("Album.findByArtist", Album.class);
            query.setParameter("artist", artist);
            albums = query.getResultList();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            albums = null;
        } finally {
            em.close();
        }
        return albums;
        
    }
    
    
}
