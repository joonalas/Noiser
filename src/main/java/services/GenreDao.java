package services;

import model.Genre;


public class GenreDao extends DaoBase<Genre> {
    private static GenreDao instance = null;

    private GenreDao() {
        setClazz(Genre.class);
    }
    
    public static GenreDao getInstance() {
        if(instance == null) {
            instance = new GenreDao();
        }
        return instance;
    }
    
}
