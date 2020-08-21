/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableDAO;

import com.test.hibernate.entities.ChiTietPhieuXuat;
import com.test.testmaven.manage.ManageChiTietPhieuXuat;
import com.test.testmaven.manage.ManagePhieuXuat;
import com.test.testmaven.manage.ManageVatTu;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuXuatServiceimpl {
     private ManageChiTietPhieuXuat manageChiTietPhieuXuat = null;

    public ChiTietPhieuXuatServiceimpl() {
        this.manageChiTietPhieuXuat = new ManageChiTietPhieuXuat();
    }
    
    public List<ChiTietPhieuXuat> getList(){
        return manageChiTietPhieuXuat.getList();
    }
    
    public List<String> getListSoPx(){
        return new ManagePhieuXuat().getListSoPx();
    }
    
    public List<String> getListMaVTu(){
        return new ManageVatTu().getListMaVTu();
    }
    
    public List<ChiTietPhieuXuat> getListWithInPhieuXuat(String soPxIn){
        return manageChiTietPhieuXuat.getListWithConstraint(soPxIn);
    }
    
    public boolean create(ChiTietPhieuXuat chiTietPhieuXuat){
        if(manageChiTietPhieuXuat.addChiTietPhieuXuat(chiTietPhieuXuat.getSoPx(), chiTietPhieuXuat.getMaVTu(), chiTietPhieuXuat.getSlXuat(), chiTietPhieuXuat.getDgXuat())){
            return true;
        }
        return false;
    }
    
    public boolean update(ChiTietPhieuXuat chiTietPhieuXuat){
        if(manageChiTietPhieuXuat.updateChiTietPhieuXuat(chiTietPhieuXuat.getSoPx(), chiTietPhieuXuat.getMaVTu(), chiTietPhieuXuat.getSlXuat(), chiTietPhieuXuat.getDgXuat())){
            return true;
        }
        return false;
    }
    
    public boolean deleteStatus(ChiTietPhieuXuat chiTietPhieuXuat){
        if(manageChiTietPhieuXuat.deleteChiTietPhieuXuat(chiTietPhieuXuat.getSoPx(), chiTietPhieuXuat.getMaVTu())){
            return true;
        }
        return false;
    }
}
