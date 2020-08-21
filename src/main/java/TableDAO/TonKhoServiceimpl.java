package TableDAO;

import com.test.hibernate.entities.TonKho;
import com.test.testmaven.manage.ManageTonKho;
import com.test.testmaven.manage.ManageVatTu;
import java.util.List;

/**
 *
 * @author Admin
 */
public class TonKhoServiceimpl {

    private ManageTonKho manageTonKho = null;

    public TonKhoServiceimpl() {
        manageTonKho = new ManageTonKho();
    }

    public List<TonKho> getList() {
        return manageTonKho.getListHql();
    }
    
    public List<String> getListMaVTu(){
        return new ManageVatTu().getListMaVTu();
    }

    public boolean update(TonKho tonKho) {
        if (manageTonKho.updateVatTu(tonKho.getNamThang(), tonKho.getMaVTu(), tonKho.getSlDau(), tonKho.getTongSLN(), tonKho.getTongSLX(), tonKho.getSLCuoi()) == 1) {
            return true;
        }
        return false;
    }

    public boolean create(TonKho tonKho) {
        if (manageTonKho.addTonKho(tonKho.getNamThang(), tonKho.getMaVTu(), tonKho.getSlDau(), tonKho.getTongSLN(), tonKho.getTongSLX(), tonKho.getSLCuoi()) == 1) {
            return true;
        }
        return false;
    }

    public int deleteStatus(TonKho tonKho) {
        int status = 0;
        ManageTonKho manageTonKho = new ManageTonKho();
        if (manageTonKho.deleteVatTu(tonKho.getMaVTu(), tonKho.getNamThang()) == 1) {
            status = 1;
        }
        return status;
    }
}
