package TableDAO;

import com.test.hibernate.entities.ChiTietDonHang;
import com.test.testmaven.manage.ManageChiTietDonHang;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietDonHangServiceimpl {
    
    private ManageChiTietDonHang manageChiTietDonHang = null;

    public ChiTietDonHangServiceimpl() {
        this.manageChiTietDonHang = new ManageChiTietDonHang();
    }

    
    public List<ChiTietDonHang> getList(){
        return manageChiTietDonHang.getList();
    }
    
    public List<ChiTietDonHang> getListWithInDonDh(String soDhIn){
        return manageChiTietDonHang.getListWithConstraint(soDhIn);
    }
    
    public boolean create(ChiTietDonHang chiTietDonHang){
        if(manageChiTietDonHang.addChiTietDonHang(chiTietDonHang.getSoDh(), chiTietDonHang.getMaVTu(), chiTietDonHang.getSlDat())){
            return true;
        }
        return false;
    }
    
    public boolean update(ChiTietDonHang chiTietDonHang){
        if(manageChiTietDonHang.updateChiTietDonHang(chiTietDonHang.getSoDh(), chiTietDonHang.getMaVTu(), chiTietDonHang.getSlDat())){
            return true;
        }
        return false;
    }
    
    public boolean deleteStatus(ChiTietDonHang chiTietDonHang){
        if(manageChiTietDonHang.deleteChiTietPhieuNhap(chiTietDonHang.getSoDh(), chiTietDonHang.getMaVTu())){
            return true;
        }
        return false;
    }
}
