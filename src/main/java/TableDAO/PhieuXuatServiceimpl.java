/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableDAO;

import com.test.hibernate.entities.PhieuXuat;
import com.test.testmaven.manage.ManagePhieuXuat;
import java.util.List;

/**
 *
 * @author Admin
 */
public class PhieuXuatServiceimpl {

    private ManagePhieuXuat managePhieuXuat = null;

    public PhieuXuatServiceimpl() {
        this.managePhieuXuat = new ManagePhieuXuat();
    }

    public List<PhieuXuat> getList() {
        return managePhieuXuat.getList();
    }

    public boolean create(PhieuXuat phieuXuat) {
        if (managePhieuXuat.addPhieuXuat(phieuXuat.getSoPx(), phieuXuat.getNgayXuat(), phieuXuat.getTenKh())) {
            return true;
        }
        return false;
    }

    public boolean update(PhieuXuat phieuXuat) {
        if (managePhieuXuat.updatePhieuXuat(phieuXuat.getSoPx(), phieuXuat.getTenKh(), phieuXuat.getNgayXuat())) {
            return true;
        }
        return false;
    }

    public boolean deleteStatus(PhieuXuat phieuXuat) {
        if (managePhieuXuat.deletePhieuXuat(phieuXuat.getSoPx())) {
            return true;
        }
        return false;
    }
}
