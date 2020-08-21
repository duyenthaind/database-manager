package com.test.hibernate.entities;



import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctpxuat")
public class ChiTietPhieuXuat implements Serializable{
	private String soPx;
	private String maVTu;
	private int slXuat;
	private float dgXuat;
	
	
	public ChiTietPhieuXuat() {
		
	}
	
	public ChiTietPhieuXuat(String soPx, String maVTu, int slXuat, float dgXuat) {
		this.soPx = soPx;
		this.maVTu = maVTu;
		this.slXuat = slXuat;
		this.dgXuat = dgXuat;
	}
	
	@Id
	@Column(name = "SoPx", length = 4, nullable = false)
	public String getSoPx() {
		return soPx;
	}
	public void setSoPx(String soPx) {
		this.soPx = soPx;
	}
	
	@Id
	@Column(name = "MaVTu", length = 4, nullable = false)
	public String getMaVTu() {
		return maVTu;
	}
	public void setMaVTu(String maVTu) {
		this.maVTu = maVTu;
	}
	
	@Column(name = "SLXuat", length=11,nullable = false)
	public int getSlXuat() {
		return slXuat;
	}
	public void setSlXuat(int slXuat) {
		this.slXuat = slXuat;
	}
	
	@Column(name = "DgXuat", nullable = false)
	public float getDgXuat() {
		return dgXuat;
	}
	public void setDgXuat(float dgXuat) {
		this.dgXuat = dgXuat;
	}
	
}
