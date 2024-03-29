package com.asset.foundation.utility.abstractclass;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@MappedSuperclass
public abstract class AbstractClass<PK extends Serializable> implements Serializable {


    private static final long serialVersionUID = 8453654076725018243L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;

//    @Version
//    @Column
//    private int version;

    public AbstractClass() {
        setCreated(new Date());
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getCreated() {
        return created;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }

//    public void setVersion(int version) {
//        this.version = version;
//    }
//
//    public int getVersion() {
//        return version;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
