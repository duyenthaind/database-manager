/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableDAO;

import com.test.hibernate.entities.DonDh;
import com.test.testmaven.manage.ManageDonDh;
import java.util.List;

/**
 *
 * @author Admin
 */
public class DonDhServiceimpl {

    private ManageDonDh manageDonDh = null;

    public DonDhServiceimpl() {
        this.manageDonDh = new ManageDonDh();
    }

    public List<DonDh> getList() {
        return manageDonDh.getList();
    }

    public List<String> getListSoDh() {
        return manageDonDh.getListSoDh();
    }

    public boolean create(DonDh donDh) {
        if (manageDonDh.addDonDh(donDh.getSoDh(), donDh.getMaNhaCc(), donDh.getNgayDh())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(DonDh donDh) {
        if (manageDonDh.updateDonDh(donDh.getSoDh(), donDh.getNgayDh(), donDh.getMaNhaCc())) {
            return true;
        }
        return false;
    }

    public boolean deleteStatus(DonDh donDh) {
        if (manageDonDh.deleteDonDh(donDh.getSoDh())) {
            return true;
        }
        return false;
    }
}
