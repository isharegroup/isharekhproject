package com.isharekh.domain.models.base;


import javax.persistence.*;
import java.util.Date;

/*
Create By: Ron Rith
Create Date: 12/12/2017
*/
@MappedSuperclass
public class BaseEntity {
    private Long id;

    private String createBy;

    private String updateBy;

    private Date dateCreate;

    private Date dateUpdate;

    private Boolean objectStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "who_create")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name = "who_update")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name = "create_date")
    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Column(name = "update_date")
    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Column(name = "status")
    public Boolean getObjectStatus() {
        return objectStatus;
    }

    public void setObjectStatus(Boolean objectStatus) {
        this.objectStatus = objectStatus;
    }
}
