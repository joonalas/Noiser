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
@Table(name = "SoundFile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SoundFile.findAll", query = "SELECT s FROM SoundFile s")
    , @NamedQuery(name = "SoundFile.findById", query = "SELECT s FROM SoundFile s WHERE s.id = :id")
    , @NamedQuery(name = "SoundFile.findByPath", query = "SELECT s FROM SoundFile s WHERE s.path = :path")
    , @NamedQuery(name = "SoundFile.findBySize", query = "SELECT s FROM SoundFile s WHERE s.size = :size")
    , @NamedQuery(name = "SoundFile.findByFormat", query = "SELECT s FROM SoundFile s WHERE s.format = :format")
    , @NamedQuery(name = "SoundFile.findByLastModified", query = "SELECT s FROM SoundFile s WHERE s.lastModified = :lastModified")
    , @NamedQuery(name = "SoundFile.findByCreated", query = "SELECT s FROM SoundFile s WHERE s.created = :created")})
public class SoundFile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "path")
    private String path;
    @Column(name = "size")
    private Integer size;
    @Column(name = "format")
    private String format;
    @Column(name = "lastModified")
    private Integer lastModified;
    @Column(name = "created")
    private Integer created;
    @JoinColumn(name = "song_id", referencedColumnName = "id")
    @ManyToOne
    private Song songId;

    public SoundFile() {
    }

    public SoundFile(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getLastModified() {
        return lastModified;
    }

    public void setLastModified(Integer lastModified) {
        this.lastModified = lastModified;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
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
        if (!(object instanceof SoundFile)) {
            return false;
        }
        SoundFile other = (SoundFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.SoundFile[ id=" + id + " ]";
    }

}
