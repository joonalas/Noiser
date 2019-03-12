package services;

import model.SoundFile;


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
    
}
