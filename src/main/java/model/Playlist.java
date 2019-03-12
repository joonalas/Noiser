package model;

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
@Table(name = "Playlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM Playlist p")
    , @NamedQuery(name = "Playlist.findById", query = "SELECT p FROM Playlist p WHERE p.id = :id")
    , @NamedQuery(name = "Playlist.findByName", query = "SELECT p FROM Playlist p WHERE p.name = :name")
    , @NamedQuery(name = "Playlist.findByCreated", query = "SELECT p FROM Playlist p WHERE p.created = :created")})
public class Playlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "created")
    private Integer created;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;
    @OneToMany(mappedBy = "playlistId")
    private Collection<PlaylistOrder> playlistOrderCollection;

    public Playlist() {
    }

    public Playlist(Integer id) {
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

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public Collection<PlaylistOrder> getPlaylistOrderCollection() {
        return playlistOrderCollection;
    }

    public void setPlaylistOrderCollection(Collection<PlaylistOrder> playlistOrderCollection) {
        this.playlistOrderCollection = playlistOrderCollection;
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
        if (!(object instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Playlist[ id=" + id + " ]";
    }

}
