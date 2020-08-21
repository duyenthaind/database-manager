package com.test.hibernate.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vattu")
public class VatTu {
	private String maVTu;
	private String tenVTu;
	private String dvTinh;
	private double phanTram;
	
	public VatTu() {
	}

	public VatTu(String maVTu, String tenVTu, String dvTinh, double phanTram) {
		this.maVTu = maVTu;
		this.tenVTu = tenVTu;
		this.dvTinh = dvTinh;
		this.phanTram = phanTram;
	}
	
	@Id
	@Column(name = "MaVTu", length = 4, nullable = false)
	public String getMaVTu() {
		return maVTu;
	}
	
	public void setMaVTu(String maVTu) {
		this.maVTu = maVTu;
	}
	
	@Column(name = "TenVTu", length = 100, nullable = false)
	public String getTenVTu() {
		return tenVTu;
	}

	public void setTenVTu(String tenVTu) {
		this.tenVTu = tenVTu;
	}
	
	@Column(name = "DvTinh", length = 10, nullable = false)
	public String getDvTinh() {
		return dvTinh;
	}

	public void setDvTinh(String dvTinh) {
		this.dvTinh = dvTinh;
	}
	
	@Column(name = "PhanTram", length = 4, nullable = false)
	public double getPhanTram() {
		return phanTram;
	}

	public void setPhanTram(double phanTram) {
		this.phanTram = phanTram;
	}
	
	
	
}
