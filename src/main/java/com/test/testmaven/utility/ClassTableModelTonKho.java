package com.test.testmaven.utility;

import com.test.hibernate.entities.TonKho;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ClassTableModelTonKho {
    public DefaultTableModel setTableTonKho(List<TonKho> listItem, String[] listColumn){
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
                TonKho tonKho = listItem.get(i);
                object = new Object[columns];
                object[0] = (i+1);
                object[1] = tonKho.getMaVTu();
                object[2] = tonKho.getNamThang();
                object[3] = tonKho.getSlDau();
                object[4] = tonKho.getSLCuoi();
                object[5] = tonKho.getTongSLN();
                object[6] = tonKho.getTongSLX();
                object[7] = "Sửa";
                object[8] = "Xóa";
                defaulttablemodel.addRow(object);
            }
        }
        return defaulttablemodel;
    }
}
