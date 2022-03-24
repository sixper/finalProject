package com.softtech.graduationproject.gen.entity;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@Data
public abstract class BaseEntity implements BaseModel, Cloneable, Serializable {

    @Embedded
    private BaseAdditionalFields baseAdditionalFields;

}
