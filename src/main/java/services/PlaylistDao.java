package services;

import model.Playlist;


public class PlaylistDao extends DaoBase<Playlist> {
    private static PlaylistDao instance = null;

    private PlaylistDao() {
        setClazz(Playlist.class);
    }
    
    public static PlaylistDao getInstance() {
        if(instance == null) {
            instance = new PlaylistDao();
        }
        return instance;
    }
    
}
