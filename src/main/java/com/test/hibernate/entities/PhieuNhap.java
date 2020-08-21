package com.test.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pnhap")
public class PhieuNhap {
	private String soPn;
	private Date ngayNhap;
	private String soDh;
	
	public PhieuNhap() {
	}
	
	public PhieuNhap(String soPn, Date ngayNhap, String soDh) {
		this.soPn = soPn;
		this.ngayNhap = ngayNhap;
		this.soDh = soDh;
	}

	@Id
	@Column(name = "SoPn", length = 4, nullable = false)
	public String getSoPn() {
		return soPn;
	}

	public void setSoPn(String soPn) {
		this.soPn = soPn;
	}
	
	@Column(name = "NgayNhap", length = 11, nullable = false) 
	public Date getNgayNhap() {
		return ngayNhap;
	}

	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	
	@Column(name = "SoDh", length = 4, nullable = false)
	public String getSoDh() {
		return soDh;
	}

	public void setSoDh(String soDh) {
		this.soDh = soDh;
	}
	
}
