/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.utility;

import com.test.hibernate.entities.ChiTietPhieuXuat;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ClassTableModelChiTietPhieuXuat {
    public DefaultTableModel setTableChiTietPhieuXuat(List<ChiTietPhieuXuat> listItem, String[] listColumn){
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
                ChiTietPhieuXuat chiTietPhieuXuat = listItem.get(i);
                object = new Object[columns];
                object[0] = (i+1);
                object[1] = chiTietPhieuXuat.getSoPx();
                object[2] = chiTietPhieuXuat.getMaVTu();
                object[3] = chiTietPhieuXuat.getSlXuat();
                object[4] = chiTietPhieuXuat.getDgXuat();
                object[5] = "Sửa";
                object[6] = "Xóa";
                defaulttablemodel.addRow(object);
            }
        }
        return defaulttablemodel;
    }
}
