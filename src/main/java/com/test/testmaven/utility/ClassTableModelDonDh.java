/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.utility;

import com.test.hibernate.entities.DonDh;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ClassTableModelDonDh {
    public DefaultTableModel setTableDonDh(List<DonDh> listItem, String[] listColumn){
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
                DonDh donDatHang = listItem.get(i);
                object = new Object[columns];
                object[0] = (i+1);
                object[1] = donDatHang.getSoDh();
                object[2] = donDatHang.getMaNhaCc();
                object[3] = donDatHang.getNgayDh();
                object[4] = "Sửa";
                object[5] = "Xóa";
                defaulttablemodel.addRow(object);
            }
        }
        return defaulttablemodel;
    }
}
