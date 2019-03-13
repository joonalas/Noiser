package model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@Entity
@Table(name = "Album")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a")
    , @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a WHERE a.id = :id")
    , @NamedQuery(name = "Album.findByName", query = "SELECT a FROM Album a WHERE a.name = :name")
    , @NamedQuery(name = "Album.findByYear", query = "SELECT a FROM Album a WHERE a.year = :year")
    , @NamedQuery(name = "Album.findByCoverPath", query = "SELECT a FROM Album a WHERE a.coverPath = :coverPath")
    , @NamedQuery(name = "Album.findByArtistName", query = "SELECT a FROM Album a WHERE a.artistId.name = :artistName")
})
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @Expose
    private Integer id;
    @Column(name = "name")
    @Expose
    private String name;
    @Column(name = "year")
    @Expose
    private Integer year;
    @Column(name = "coverPath")
    @Expose
    private String coverPath;
    @OneToMany(mappedBy = "albumId")
    private Collection<Song> songCollection;
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    @ManyToOne
    private Artist artistId;

    public Album() {
    }

    public Album(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    @XmlTransient
    public Collection<Song> getSongCollection() {
        return songCollection;
    }

    public void setSongCollection(Collection<Song> songCollection) {
        this.songCollection = songCollection;
    }

    public Artist getArtistId() {
        return artistId;
    }

    public void setArtistId(Artist artistId) {
        this.artistId = artistId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Album[ id=" + id + " ]";
    }

}
