package com.asset.foundation.utility.abstractclass;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Version;
import java.util.Date;

@Getter
@Setter
public class Auditing {

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date modifiedDate;
    @CreatedBy
    private String createdUser;
    @LastModifiedBy
    private String lastModifiedUser;
    @Version
    @Column
    private int version;


}
