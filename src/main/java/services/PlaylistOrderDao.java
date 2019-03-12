package services;

import model.PlaylistOrder;


public class PlaylistOrderDao extends DaoBase<PlaylistOrder> {
    private static PlaylistOrderDao instance = null;

    private PlaylistOrderDao() {
        setClazz(PlaylistOrder.class);
    }
    
    public static PlaylistOrderDao getInstance() {
        if(instance == null) {
            instance = new PlaylistOrderDao();
        }
        return instance;
    }
}
