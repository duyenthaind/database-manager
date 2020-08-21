package com.test.hibernate.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pxuat")
public class PhieuXuat {
	private String soPx;
	private Date ngayXuat;
	private String tenKh;
	
	public PhieuXuat() {
	}

	public PhieuXuat(String soPx, Date ngayXuat, String tenKh) {
		this.soPx = soPx;
		this.ngayXuat = ngayXuat;
		this.tenKh = tenKh;
	}
	
	@Id
	@Column(name = "SoPx", length = 4, nullable = false)
	public String getSoPx() {
		return soPx;
	}

	public void setSoPx(String soPx) {
		this.soPx = soPx;
	}
	
	@Column(name = "NgayXuat", length = 11, nullable = false) 
	public Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	@Column(name = "TenKh", length = 100, nullable = false)
	public String getTenKh() {
		return tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}
	
}
