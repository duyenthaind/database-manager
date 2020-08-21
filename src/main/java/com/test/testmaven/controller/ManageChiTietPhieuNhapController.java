/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.ChiTietPhieuNhapServiceimpl;
import com.test.hibernate.entities.ChiTietPhieuNhap;
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
public class ManageChiTietPhieuNhapController {
    private JButton btnSubmit;
    private JTextField jtfSoLuongNhap;
    private JTextField jtfDonGiaNhap;
    private JLabel jlbMessage;
    private JComboBox jcbMaVTu;
    private JComboBox jcbSoPn;
    
    private ChiTietPhieuNhapServiceimpl chiTietPhieuNhapService = null;
    
    private ChiTietPhieuNhap chiTietPhieuNhap = null;

    public ManageChiTietPhieuNhapController(JButton btnSubmit, JComboBox jcbSoPn, JComboBox jcbMaVTu, JTextField jtfSoLuongNhap, JTextField jtfDonGiaNhap, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jcbSoPn = jcbSoPn;
        this.jcbMaVTu = jcbMaVTu;
        this.jtfSoLuongNhap = jtfSoLuongNhap;
        this.jtfDonGiaNhap = jtfDonGiaNhap;
        this.jlbMessage = jlbMessage;
        this.chiTietPhieuNhapService = new ChiTietPhieuNhapServiceimpl();
    }
    
    
    public void setView(ChiTietPhieuNhap chiTietPhieuNhap){
        this.chiTietPhieuNhap = chiTietPhieuNhap;
        jcbMaVTu.setSelectedItem(chiTietPhieuNhap.getMaVTu());
        jcbSoPn.setSelectedItem(chiTietPhieuNhap.getSoPn());
        jtfSoLuongNhap.setText(chiTietPhieuNhap.getSlNhap() + "");
        jtfDonGiaNhap.setText(chiTietPhieuNhap.getDgNhap()+"");
        jcbMaVTu.setEditable(false);
        jcbSoPn.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jcbMaVTu.getSelectedItem() == null || jcbSoPn.getSelectedItem() == null){
                    jlbMessage.setText("Vui lòng chọn dữ liệu bắt buộc");
                } else{
                    chiTietPhieuNhap.setSoPn(jcbSoPn.getItemAt(jcbSoPn.getSelectedIndex()).toString());
                    chiTietPhieuNhap.setMaVTu(jcbMaVTu.getItemAt(jcbMaVTu.getSelectedIndex()).toString());
                    chiTietPhieuNhap.setSlNhap(Integer.parseInt(jtfSoLuongNhap.getText()));
                    chiTietPhieuNhap.setDgNhap(Float.parseFloat(jtfDonGiaNhap.getText()));
                    if(chiTietPhieuNhapService.create(chiTietPhieuNhap)){
                        jlbMessage.setText("Thêm mới thành công!");
                    } else{
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
