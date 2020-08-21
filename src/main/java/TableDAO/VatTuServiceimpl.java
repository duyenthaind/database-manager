package TableDAO;

import com.test.hibernate.entities.VatTu;
import com.test.testmaven.manage.ManageVatTu;
import java.util.List;

/**
 *
 * @author Admin
 */
public class VatTuServiceimpl {
    private ManageVatTu manageVatTu = null;
    
    public VatTuServiceimpl(){
        manageVatTu = new ManageVatTu();
    }
    
    public List<VatTu> getList(){
        return manageVatTu.getList();
    }
    
    public List<String> getListMaVTu(){
        return manageVatTu.getListMaVTu();
    }
    
    public int create(VatTu vatTu) {
        int status = 0;
        ManageVatTu manageVatTu = new ManageVatTu();
        try{
            manageVatTu.addVatTu(vatTu.getMaVTu(), vatTu.getTenVTu(), vatTu.getDvTinh(), vatTu.getPhanTram());
            status = 1;
        } catch(Exception e){
            e.printStackTrace();
            status = 0;
        }
        status = 1;
        return status;
    }
    
    public int update(VatTu vatTu){
        int status = 0;
        ManageVatTu manageVatTu = new ManageVatTu();
        try{
            manageVatTu.updateVatTu(vatTu.getMaVTu(), vatTu.getTenVTu(), vatTu.getDvTinh(), vatTu.getPhanTram());
            status = 1;
        } catch(Exception e){
            e.printStackTrace();
            status = 0;
        }
        return status;
    }
    
    public int deleteStatus(VatTu vatTu){
        int status = 0;
        ManageVatTu manageVatTu = new ManageVatTu();
        if(!manageVatTu.isExists(vatTu)){
            return 0;
        }
        if(manageVatTu.deleteVatTu(vatTu.getMaVTu())==1){
            status=1;
        };
        return status;
    }
}
