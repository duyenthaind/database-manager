package com.test.testmaven.utility;

import com.test.hibernate.entities.NhaCungCap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ClassTableModelNhaCungCap {
    public DefaultTableModel setTableNCC(List<NhaCungCap> listItem, String[] listColumn){
        DefaultTableModel defaulttablemodel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        defaulttablemodel.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object []object = null;
        int rows = listItem.size();
        if(rows>0){
            for(int i = 0; i<rows; i++){
                NhaCungCap nhaCungCap = listItem.get(i);
                object = new Object[columns];
                object[0] = (i+1);
                object[1] = nhaCungCap.getMaNhaCc();
                object[2] = nhaCungCap.getTenNhaCc();
                object[3] = nhaCungCap.getDiaChi();
                object[4] = nhaCungCap.getDienThoai();
                object[5] = "Sửa";
                object[6] = "Xóa";
                defaulttablemodel.addRow(object);
            }
        }
        return defaulttablemodel;
    }
}
