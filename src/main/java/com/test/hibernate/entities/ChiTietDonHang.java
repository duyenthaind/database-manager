package com.test.hibernate.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ctdondh")
public class ChiTietDonHang implements Serializable{
	private String soDh;
	private String maVTu;
	private int slDat;
	public ChiTietDonHang() {
		
	}
	
	public ChiTietDonHang(String maVTuIn, int slDatIn, String soDhIn) {
		this.maVTu = maVTuIn;
		this.slDat = slDatIn;
		this.soDh = soDhIn;
	}
	
	@Id
	@Column(name = "SoDh", length = 4, nullable = false)
	public String getSoDh() {
		return soDh;
	}
	public void setSoDh(String soDh) {
		this.soDh = soDh;
	}
	
	@Id
	@Column(name = "MaVTu", length = 4, nullable = false)
	public String getMaVTu() {
		return maVTu;
	}
	public void setMaVTu(String maVTu) {
		this.maVTu = maVTu;
	}
	
	@Column(name = "SlDat", nullable = false)
	public int getSlDat() {
		return slDat;
	}
	public void setSlDat(int slDat) {
		this.slDat = slDat;
	}
	
}
