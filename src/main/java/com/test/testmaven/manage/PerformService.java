/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.manage;

import TableDAO.*;
import com.test.hibernate.entities.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author HL94NVT
 */
public class PerformService {

    private int service;
    private int type;
    private Object obj;
    private final Session session;
    private static final int CTDONHANG = 1;
    private static final int CTPHIEUNHAP = 2;
    private static final int CTPHIEUXUAT = 3;
    private static final int DONDH = 4;
    private static final int NHACUNGCAP = 5;
    private static final int PNHAP = 6;
    private static final int PXUAT = 7;
    private static final int TONKHO = 8;
    private static final int VATTU = 9;
    private final ChiTietDonHangServiceimpl serviceCTDH = new ChiTietDonHangServiceimpl();
    private final ChiTietPhieuNhapServiceimpl serviceCTPN = new ChiTietPhieuNhapServiceimpl();
    private final ChiTietPhieuXuatServiceimpl serviceCTPX = new ChiTietPhieuXuatServiceimpl();
    private final DonDhServiceimpl serviceDDH = new DonDhServiceimpl();
    private final NhaCungCapServiceimpl serviceNCC = new NhaCungCapServiceimpl();
    private final PhieuNhapServiceimpl servicePN = new PhieuNhapServiceimpl();
    private final PhieuXuatServiceimpl servicePX = new PhieuXuatServiceimpl();
    private final TonKhoServiceimpl serviceTK = new TonKhoServiceimpl();
    private final VatTuServiceimpl serviceVT = new VatTuServiceimpl();

    public PerformService(Session session) {
        this.session = session;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public int process() {
        if (service == Job.SAVE) {
            try {
                session.save(obj);
            } catch (HibernateException ex) {
                return -1;
            }
            return 1;
        }
        if (service == Job.UPDATE) {
            try {
                switch (type) {
                    case CTDONHANG:
                        serviceCTDH.update((ChiTietDonHang) obj);
                        break;
                    case CTPHIEUNHAP:
                        serviceCTPN.update((ChiTietPhieuNhap) obj);
                        break;
                    case CTPHIEUXUAT:
                        serviceCTPX.update((ChiTietPhieuXuat) obj);
                        break;
                    case DONDH:
                        serviceDDH.update((DonDh) obj);
                        break;
                    case NHACUNGCAP:
                        serviceNCC.update((NhaCungCap) obj);
                        break;
                    case PNHAP:
                        servicePN.update((PhieuNhap) obj);
                        break;
                    case PXUAT:
                        servicePX.update((PhieuXuat) obj);
                        break;
                    case TONKHO:
                        serviceTK.update((TonKho) obj);
                        break;
                    case VATTU:
                        serviceVT.update((VatTu) obj);
                        break;
                    default:
                        break;
                }
                return 2;
            } catch (Exception ex) {
                return -1;
            }
        }
        if (service == Job.DELETE) {
            try {
                switch (type) {
                    case CTDONHANG:
                        serviceCTDH.deleteStatus((ChiTietDonHang) obj);
                        break;
                    case CTPHIEUNHAP:
                        serviceCTPN.deleteStatus((ChiTietPhieuNhap) obj);
                        break;
                    case CTPHIEUXUAT:
                        serviceCTPX.deleteStatus((ChiTietPhieuXuat) obj);
                        break;
                    case DONDH:
                        serviceDDH.deleteStatus((DonDh) obj);
                        break;
                    case NHACUNGCAP:
                        serviceNCC.deleteStatus((NhaCungCap) obj);
                        break;
                    case PNHAP:
                        servicePN.deleteStatus((PhieuNhap) obj);
                        break;
                    case PXUAT:
                        servicePX.deleteStatus((PhieuXuat) obj);
                        break;
                    case TONKHO:
                        serviceTK.deleteStatus((TonKho) obj);
                        break;
                    case VATTU:
                        serviceVT.deleteStatus((VatTu) obj);
                        break;
                    default:
                        break;
                }
                return 3;
            } catch (Exception ex) {
                return -1;
            }
        }
        return 0;
    }
}
