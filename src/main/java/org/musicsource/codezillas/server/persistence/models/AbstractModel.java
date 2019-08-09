package org.musicsource.codezillas.server.persistence.models;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class AbstractModel implements Model, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Version
    private Integer version;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
