package TableDAO;

import com.test.hibernate.entities.NhaCungCap;
import com.test.testmaven.manage.ManageNhaCungCap;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhaCungCapServiceimpl {
    private ManageNhaCungCap manageNhaCungCap = null;
    
    public NhaCungCapServiceimpl(){
        manageNhaCungCap = new ManageNhaCungCap();
    }
    
    public List<NhaCungCap> getList(){
        return manageNhaCungCap.getList();
    }
    
    public List<String> getListMaNhaCungCap(){
        return manageNhaCungCap.getListMaNhaCungCap();
    }
    
    public boolean create(NhaCungCap nhaCungCapIn){
        if(manageNhaCungCap.addNhaCungCap(nhaCungCapIn.getMaNhaCc(), nhaCungCapIn.getTenNhaCc(), nhaCungCapIn.getDiaChi(), nhaCungCapIn.getDienThoai())){
            return true;
        }
        return false;
    }
    
    public boolean update(NhaCungCap nhaCungCapIn){
        if(manageNhaCungCap.updateNhaCungCap(nhaCungCapIn.getMaNhaCc(), nhaCungCapIn.getTenNhaCc(), nhaCungCapIn.getDiaChi(), nhaCungCapIn.getDienThoai())){
            return true;
        }
        return false;
    }
    
    public boolean deleteStatus(NhaCungCap nhaCungCapIn){
        if(manageNhaCungCap.deleteNhaCungCap(nhaCungCapIn.getMaNhaCc())){
            return true;
        }
        return false;
    }
    
}
