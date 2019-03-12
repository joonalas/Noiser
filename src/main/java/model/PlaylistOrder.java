package model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "playlistOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlaylistOrder.findAll", query = "SELECT p FROM PlaylistOrder p")
    , @NamedQuery(name = "PlaylistOrder.findById", query = "SELECT p FROM PlaylistOrder p WHERE p.id = :id")
    , @NamedQuery(name = "PlaylistOrder.findByOrderNumber", query = "SELECT p FROM PlaylistOrder p WHERE p.orderNumber = :orderNumber")})
public class PlaylistOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "orderNumber")
    private Integer orderNumber;
    @JoinColumn(name = "playlist_id", referencedColumnName = "id")
    @ManyToOne
    private Playlist playlistId;
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    @ManyToOne
    private Song songId;

    public PlaylistOrder() {
    }

    public PlaylistOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Playlist getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(Playlist playlistId) {
        this.playlistId = playlistId;
    }

    public Song getSongId() {
        return songId;
    }

    public void setSongId(Song songId) {
        this.songId = songId;
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
        if (!(object instanceof PlaylistOrder)) {
            return false;
        }
        PlaylistOrder other = (PlaylistOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PlaylistOrder[ id=" + id + " ]";
    }

}
