/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.manage;

import com.test.hibernate.entities.ChiTietDonHang;
import com.test.hibernate.entities.ChiTietPhieuNhap;
import com.test.hibernate.entities.ChiTietPhieuXuat;
import com.test.hibernate.entities.DonDh;
import com.test.hibernate.entities.NhaCungCap;
import com.test.hibernate.entities.PhieuNhap;
import com.test.hibernate.entities.PhieuXuat;
import com.test.hibernate.entities.TonKho;
import com.test.hibernate.entities.VatTu;

/**
 *
 * @author HL94NVT
 */
public class CheckInstance {

    private Object obj;
    private static final int CTDONHANG = 1;
    private static final int CTPHIEUNHAP = 2;
    private static final int CTPHIEUXUAT = 3;
    private static final int DONDH = 4;
    private static final int NHACUNGCAP = 5;
    private static final int PNHAP = 6;
    private static final int PXUAT = 7;
    private static final int TONKHO = 8;
    private static final int VATTU = 9;

    public CheckInstance() {
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int returnType() {
        if (obj instanceof ChiTietDonHang) {
            return CTDONHANG;
        }
        if (obj instanceof ChiTietPhieuNhap) {
            return CTPHIEUNHAP;
        }
        if (obj instanceof ChiTietPhieuXuat) {
            return CTPHIEUXUAT;
        }
        if (obj instanceof DonDh) {
            return DONDH;
        }
        if (obj instanceof NhaCungCap) {
            return NHACUNGCAP;
        }
        if (obj instanceof PhieuNhap) {
            return PNHAP;
        }
        if (obj instanceof PhieuXuat) {
            return PXUAT;
        }
        if (obj instanceof TonKho) {
            return TONKHO;
        }
        if (obj instanceof VatTu) {
            return VATTU;
        }
        return 0;
    }
}
