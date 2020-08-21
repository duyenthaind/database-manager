package com.test.testmaven.utility;

import com.test.hibernate.entities.VatTu;
import java.util.List;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ClassTableModelVatTu {
    public DefaultTableModel setTableVatTu(List<VatTu> listItem, String[] listColumn){
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
                VatTu vatTu = listItem.get(i);
                object = new Object[columns];
                object[0] = (i+1);
                object[1] = vatTu.getMaVTu();
                object[2] = vatTu.getTenVTu();
                object[3] = vatTu.getDvTinh();
                object[4] = vatTu.getPhanTram();
                object[5] = "Sửa";
                object[6] = "Xóa";
                defaulttablemodel.addRow(object);
            }
        }
        return defaulttablemodel;
    }
}
