package com.test.hibernate.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nhacc")
public class NhaCungCap {
	private String maNhaCc;
	private String tenNhaCc;
	private String diaChi;
	private String dienThoai;
	
	public NhaCungCap() {
	}
	
	public NhaCungCap(String maNhaCc, String tenNhaCc, String diaChi, String dienThoai) {
		this.maNhaCc = maNhaCc;
		this.tenNhaCc = tenNhaCc;
		this.diaChi = diaChi;
		this.dienThoai = dienThoai;
	}
	
	@Id
	@Column(name = "MaNhaCc", length = 3, nullable = false)
	public String getMaNhaCc() {
		return maNhaCc;
	}

	public void setMaNhaCc(String maNhaCc) {
		this.maNhaCc = maNhaCc;
	}
	
	@Column(name = "TenNhaCc", length = 100, nullable=false)
	public String getTenNhaCc() {
		return tenNhaCc;
	}

	public void setTenNhaCc(String tenNhaCc) {
		this.tenNhaCc = tenNhaCc;
	}
	
	@Column(name = "DiaChi", length = 200, nullable = false)
	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Column(name = "DienThoai", length = 20, nullable = false)
	public String getDienThoai() {
		return dienThoai;
	}

	public void setDienThoai(String dienThoai) {
		this.dienThoai = dienThoai;
	}

	
}
