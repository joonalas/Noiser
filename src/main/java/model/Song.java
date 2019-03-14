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
@Table(name = "Song")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Song.findAll", query = "SELECT s FROM Song s")
    , @NamedQuery(name = "Song.findById", query = "SELECT s FROM Song s WHERE s.id = :id")
    , @NamedQuery(name = "Song.findByName", query = "SELECT s FROM Song s WHERE s.name = :name")
    , @NamedQuery(name = "Song.findByDuration", query = "SELECT s FROM Song s WHERE s.duration = :duration")
    , @NamedQuery(name = "Song.findByTrack", query = "SELECT s FROM Song s WHERE s.track = :track")
    , @NamedQuery(name = "Song.findByAlbumName", query = "SELECT s FROM Song s WHERE s.albumId.name = :albumName")
})
public class Song implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @Expose
    private Integer id;
    @Column(name = "name")
    @Expose
    private String name;
    @Column(name = "duration")
    @Expose
    private Integer duration;
    @Column(name = "track")
    @Expose
    private Integer track;
    @JoinColumn(name = "album_id", referencedColumnName = "id")
    @ManyToOne
    private Album albumId;
    @OneToMany(mappedBy = "songId")
    private Collection<PlaylistOrder> playlistOrderCollection;
    @OneToMany(mappedBy = "songId")
    private Collection<SoundFile> soundFileCollection;

    public Song() {
    }

    public Song(Integer id) {
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getTrack() {
        return track;
    }

    public void setTrack(Integer track) {
        this.track = track;
    }

    public Album getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Album albumId) {
        this.albumId = albumId;
    }

    @XmlTransient
    public Collection<PlaylistOrder> getPlaylistOrderCollection() {
        return playlistOrderCollection;
    }

    public void setPlaylistOrderCollection(Collection<PlaylistOrder> playlistOrderCollection) {
        this.playlistOrderCollection = playlistOrderCollection;
    }

    @XmlTransient
    public Collection<SoundFile> getSoundFileCollection() {
        return soundFileCollection;
    }

    public void setSoundFileCollection(Collection<SoundFile> soundFileCollection) {
        this.soundFileCollection = soundFileCollection;
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
        if (!(object instanceof Song)) {
            return false;
        }
        Song other = (Song) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Song[ id=" + id + " ]";
    }

}
