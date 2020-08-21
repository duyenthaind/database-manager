/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableDAO;

import com.test.hibernate.entities.ChiTietPhieuNhap;
import com.test.testmaven.manage.ManageChiTietPhieuNhap;
import com.test.testmaven.manage.ManagePhieuNhap;
import com.test.testmaven.manage.ManageVatTu;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapServiceimpl {
    private ManageChiTietPhieuNhap manageChiTietPhieuNhap = null;

    public ChiTietPhieuNhapServiceimpl() {
        this.manageChiTietPhieuNhap = new ManageChiTietPhieuNhap();
    }
    
    
    public List<ChiTietPhieuNhap> getList(){
        return manageChiTietPhieuNhap.getList();
    }
    
    public List<ChiTietPhieuNhap> getListWithInPhieuNhap(String soPnIn){
        return manageChiTietPhieuNhap.getListWithConstraint(soPnIn);
    }
    
    public List<String> getListSoPn(){
        return new ManagePhieuNhap().getListSoPn();
    }
    
    public List<String> getListMaVTu(){
        return new ManageVatTu().getListMaVTu();
    }
    
    public boolean create(ChiTietPhieuNhap chiTietPhieuNhap){
        if(manageChiTietPhieuNhap.addChiTietPhieuNhap(chiTietPhieuNhap.getSoPn(), chiTietPhieuNhap.getMaVTu(), chiTietPhieuNhap.getSlNhap(), chiTietPhieuNhap.getDgNhap())){
            return true;
        }
        return false;
    }
    
    public boolean update(ChiTietPhieuNhap chiTietPhieuNhap){
        if(manageChiTietPhieuNhap.updateChiTietPhieuNhap(chiTietPhieuNhap.getSoPn(), chiTietPhieuNhap.getMaVTu(), chiTietPhieuNhap.getSlNhap(), chiTietPhieuNhap.getDgNhap())){
            return true;
        }
        return false;
    }
    
    public boolean deleteStatus(ChiTietPhieuNhap chiTietPhieuNhap){
        if(manageChiTietPhieuNhap.deleteChiTietPhieuNhap(chiTietPhieuNhap.getSoPn(), chiTietPhieuNhap.getMaVTu())){
            return true;
        }
        return false;
    }
}
