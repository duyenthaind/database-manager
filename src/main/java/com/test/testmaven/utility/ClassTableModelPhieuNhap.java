package com.test.testmaven.utility;

import com.test.hibernate.entities.PhieuNhap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ClassTableModelPhieuNhap {
    public DefaultTableModel setTablePhieuNhap(List<PhieuNhap> listItem, String[] listColumn){
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
                PhieuNhap phieuNhap = listItem.get(i);
                object = new Object[columns];
                object[0] = (i+1);
                object[1] = phieuNhap.getSoPn();
                object[2] = phieuNhap.getSoDh();
                object[3] = phieuNhap.getNgayNhap();
                object[4] = "Sửa";
                object[5] = "Xóa";
                defaulttablemodel.addRow(object);
            }
        }
        return defaulttablemodel;
    }
}
