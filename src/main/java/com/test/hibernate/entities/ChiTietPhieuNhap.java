package com.test.hibernate.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctpnhap")
public class ChiTietPhieuNhap implements Serializable {
	private String soPn;
	private String maVTu;
	private int slNhap;
	private float dgNhap;
	
	public ChiTietPhieuNhap() {
		
	}
	
	public ChiTietPhieuNhap(String soPn, String maVTu, int slNhap, float dgNhap) {
		this.soPn = soPn;
		this.maVTu = maVTu;
		this.slNhap = slNhap;
		this.dgNhap = dgNhap;
	}


	@Id
	@Column(name = "SoPn", length = 4, nullable = false)
	public String getSoPn() {
		return soPn;
	}
	public void setSoPn(String soPn) {
		this.soPn = soPn;
	}
	
	@Id
	@Column(name = "MaVTu", length = 4, nullable = false)
	public String getMaVTu() {
		return maVTu;
	}
	public void setMaVTu(String maVTu) {
		this.maVTu = maVTu;
	}
	
	@Column(name = "SLNhap", length=11,nullable = false)
	public int getSlNhap() {
		return slNhap;
	}
	public void setSlNhap(int slNhap) {
		this.slNhap = slNhap;
	}
	
	@Column(name = "DgNhap", nullable = false)
	public float getDgNhap() {
		return dgNhap;
	}
	public void setDgNhap(float dgNhap) {
		this.dgNhap = dgNhap;
	}
	
}
