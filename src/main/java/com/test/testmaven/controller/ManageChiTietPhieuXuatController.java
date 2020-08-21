/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.ChiTietPhieuXuatServiceimpl;
import com.test.hibernate.entities.ChiTietPhieuXuat;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class ManageChiTietPhieuXuatController {
    private JButton btnSubmit;
    private JComboBox jcbSoPx;
    private JComboBox jcbMaVTu;
    private JTextField jtfSoLuongXuat;
    private JTextField jtfDonGiaXuat;
    private JLabel jlbMessage;
    
    private ChiTietPhieuXuatServiceimpl chiTietPhieuXuatService = null;
    
    private ChiTietPhieuXuat chiTietPhieuXuat = null;

    public ManageChiTietPhieuXuatController(JButton btnSubmit, JComboBox jcbSoPx, JComboBox jcbMaVTu, JTextField jtfSoLuongXuat, JTextField jtfDonGiaXuat, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jcbSoPx = jcbSoPx;
        this.jcbMaVTu = jcbMaVTu;
        this.jtfSoLuongXuat = jtfSoLuongXuat;
        this.jtfDonGiaXuat = jtfDonGiaXuat;
        this.jlbMessage = jlbMessage;
        this.chiTietPhieuXuatService = new ChiTietPhieuXuatServiceimpl();
    }

    
    
    
    public void setView(ChiTietPhieuXuat chiTietPhieuXuat){
        this.chiTietPhieuXuat = chiTietPhieuXuat;
        jcbSoPx.setSelectedItem(chiTietPhieuXuat.getSoPx());
        jcbMaVTu.setSelectedItem(chiTietPhieuXuat.getMaVTu());
        jtfSoLuongXuat.setText(chiTietPhieuXuat.getSlXuat()+ "");
        jtfDonGiaXuat.setText(chiTietPhieuXuat.getSlXuat()+"");
        jcbMaVTu.setEditable(false);
        jcbSoPx.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jcbSoPx.getSelectedItem() == null || jcbMaVTu.getSelectedItem() == null){
                    jlbMessage.setText("Vui lòng chọn dữ liệu bắt buộc");
                } else{
                    chiTietPhieuXuat.setSoPx(jcbSoPx.getItemAt(jcbSoPx.getSelectedIndex()).toString());
                    chiTietPhieuXuat.setMaVTu(jcbMaVTu.getItemAt(jcbMaVTu.getSelectedIndex()).toString());
                    chiTietPhieuXuat.setSlXuat(Integer.parseInt(jtfSoLuongXuat.getText()));
                    chiTietPhieuXuat.setDgXuat(Float.parseFloat(jtfDonGiaXuat.getText()));
                    if(chiTietPhieuXuatService.create(chiTietPhieuXuat)){
                        jlbMessage.setText("Thêm mới thành công!");
                    }else{
                        jlbMessage.setText("Dữ liệu đã tồn tại!");
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100, 221, 23));
            }
        });
    }
}
