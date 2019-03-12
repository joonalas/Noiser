package services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import model.Artist;
import util.DbUtil;


public class ArtistDao extends DaoBase<Artist>{

    private static ArtistDao instance = null;
    
    private ArtistDao(){
        setClazz(Artist.class);
    }
    
    public static ArtistDao getInstance() {
        if(instance == null) {
            instance = new ArtistDao();
        }
        return instance;
    }
    
    public List<Artist> getByGenre(String genre) {
        EntityManager em = DbUtil.getEntityManager("NoiserPU");
        List<Artist> artists = null;
        try {
            TypedQuery<Artist> query = em.createNamedQuery("Artist.findByGenre", Artist.class);
            query.setParameter("genre", genre);
            artists = query.getResultList();
        } catch(Exception e) {
            System.err.println(e.getMessage() + ":\n");
            for(StackTraceElement stackElement : e.getStackTrace()) {
                System.err.println(stackElement.toString());
            }
            artists = null;
        } finally {
            em.close();
        }
        return artists;
    }
    
}
