package com.yi.jetpackDemo.room.greenDaoDB;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

@Entity(active = true)
public class User2 {
    @Id
    private Long uid;
    private String firstName;
    private String lastName;
    private String telephone;
    @ToOne(joinProperty = "uid")
    private Address2 address;
//    @ToMany(referencedJoinProperty = "userId")
//    private List<Address> addresses;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 759911719)
    private transient User2Dao myDao;
    @Generated(hash = 1530258019)
    public User2(Long uid, String firstName, String lastName, String telephone) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
    }
    @Generated(hash = 377798761)
    public User2() {
    }
    public Long getUid() {
        return this.uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    @Generated(hash = 1156467801)
    private transient Long address__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1491619003)
    public Address2 getAddress() {
        Long __key = this.uid;
        if (address__resolvedKey == null || !address__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            Address2Dao targetDao = daoSession.getAddress2Dao();
            Address2 addressNew = targetDao.load(__key);
            synchronized (this) {
                address = addressNew;
                address__resolvedKey = __key;
            }
        }
        return address;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1010140115)
    public void setAddress(Address2 address) {
        synchronized (this) {
            this.address = address;
            uid = address == null ? null : address.getId();
            address__resolvedKey = uid;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1719718852)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUser2Dao() : null;
    }

}
