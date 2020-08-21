package com.test.hibernate.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tonkho")
public class TonKho implements Serializable{
	private String namThang;
	private String maVTu;
	private int slDau;
	private int tongSLN;
	private int tongSLX;
	private int sLCuoi;
	
	public TonKho() {
	}

	public TonKho(String namThang, String maVTu, int slDau, int tongSLN, int tongSLX, int sLCuoi) {
		this.namThang = namThang;
		this.maVTu = maVTu;
		this.slDau = slDau;
		this.tongSLN = tongSLN;
		this.tongSLX = tongSLX;
		this.sLCuoi = sLCuoi;
	}

	@Id
	@Column(name = "NamThang", length = 6, nullable = false)
	public String getNamThang() {
		return namThang;
	}

	public void setNamThang(String namThang) {
		this.namThang = namThang;
	}
	
	@Id
	@Column(name = "MaVTu", length = 4, nullable = false)
	public String getMaVTu() {
		return maVTu;
	}

	public void setMaVTu(String maVTu) {
		this.maVTu = maVTu;
	}
	
	@Column(name = "SLDau", length=11,nullable = false)
	public int getSlDau() {
		return slDau;
	}

	public void setSlDau(int slDau) {
		this.slDau = slDau;
	}
	
	@Column(name = "TongSLN", length=11,nullable = false)
	public int getTongSLN() {
		return tongSLN;
	}

	public void setTongSLN(int tongSLN) {
		this.tongSLN = tongSLN;
	}
	
	@Column(name = "TongSLX", length=11,nullable = false)
	public int getTongSLX() {
		return tongSLX;
	}

	public void setTongSLX(int tongSLX) {
		this.tongSLX = tongSLX;
	}

	@Column(name = "SLCuoi", length=11,nullable = false)
	public int getSLCuoi() {
		return sLCuoi;
	}

	public void setSLCuoi(int sLCuoi) {
		this.sLCuoi = sLCuoi;
	}

	
}
