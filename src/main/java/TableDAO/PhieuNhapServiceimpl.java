/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableDAO;

import com.test.hibernate.entities.PhieuNhap;
import com.test.testmaven.manage.ManagePhieuNhap;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuNhapServiceimpl {

    private ManagePhieuNhap managePhieuNhap = null;

    public PhieuNhapServiceimpl() {
        this.managePhieuNhap = new ManagePhieuNhap();
    }

    public List<PhieuNhap> getList() {
        return managePhieuNhap.getList();
    }

    public boolean create(PhieuNhap phieuNhap) {
        if (managePhieuNhap.addPhieuNhap(phieuNhap.getSoPn(), phieuNhap.getSoDh(), phieuNhap.getNgayNhap())) {
            return true;
        }
        return false;
    }

    public boolean update(PhieuNhap phieuNhap) {
        if (managePhieuNhap.updatePhieuNhap(phieuNhap.getSoPn(), phieuNhap.getNgayNhap(), phieuNhap.getSoDh())) {
            return true;
        }
        return false;
    }

    public boolean deleteStatus(PhieuNhap phieuNhap) {
        if (managePhieuNhap.deletePhieuNhap(phieuNhap.getSoPn())) {
            return true;
        }
        return false;
    }
}
