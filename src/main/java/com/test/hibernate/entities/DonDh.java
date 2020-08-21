package com.test.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dondh")
public class DonDh {

    private String soDh;
    private String maNhaCc;
    private Date ngayDh;

    public DonDh() {

    }

    public DonDh(String soDh, String maNhaCc, Date ngayDh) {
        this.soDh = soDh;
        this.maNhaCc = maNhaCc;
        this.ngayDh = ngayDh;
    }

    @Id
    @Column(name = "SoDh", length = 4, nullable = false)
    public String getSoDh() {
        return soDh;
    }

    public void setSoDh(String soDh) {
        this.soDh = soDh;
    }

    @Column(name = "MaNhaCc", length = 3, nullable = false)
    public String getMaNhaCc() {
        return maNhaCc;
    }

    public void setMaNhaCc(String maNhaCc) {
        this.maNhaCc = maNhaCc;
    }

    @Column(name = "NgayDh", length = 11, nullable = false)
    public Date getNgayDh() {
        return ngayDh;
    }

    public void setNgayDh(Date ngayDh) {
        this.ngayDh = ngayDh;
    }

}
