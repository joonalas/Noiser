package services;

import model.Song;


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
    
}
