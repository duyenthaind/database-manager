/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.PhieuNhapServiceimpl;
import com.test.hibernate.entities.PhieuNhap;
import com.toedter.calendar.JDateChooser;
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
public class ManagePhieuNhapControlllerUpdate {
    private JButton btnSubmit;
    private JComboBox jcbSoDh;
    private JTextField jtfSoPn;
    private JDateChooser jdcNgayNhap;
    private JLabel jlbMessage;
    
    private PhieuNhapServiceimpl phieuNhapService = null;
    
    private PhieuNhap phieuNhap = null;

    public ManagePhieuNhapControlllerUpdate(JButton btnSubmit, JComboBox jcbSoDh, JTextField jtfSoPn, JDateChooser jdcNgayNhap, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jcbSoDh = jcbSoDh;
        this.jtfSoPn = jtfSoPn;
        this.jdcNgayNhap = jdcNgayNhap;
        this.jlbMessage = jlbMessage;
        this.phieuNhapService = new PhieuNhapServiceimpl();
    }
    
    public void setView(PhieuNhap phieuNhap){
        this.phieuNhap = phieuNhap;
        jtfSoPn.setText(phieuNhap.getSoPn());
        jdcNgayNhap.setDate(phieuNhap.getNgayNhap());
        jcbSoDh.setSelectedItem(phieuNhap.getSoDh());
        jtfSoPn.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jcbSoDh.getSelectedItem() == null){
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else{
                    phieuNhap.setSoPn(jtfSoPn.getText());
                    phieuNhap.setSoDh(jcbSoDh.getItemAt(jcbSoDh.getSelectedIndex()).toString());
                    try{
                        phieuNhap.setNgayNhap(jdcNgayNhap.getDate());
                    } catch(Exception ex){
                        ex.printStackTrace();
                        phieuNhap.setNgayNhap(null);
                    }
                    if(phieuNhapService.update(phieuNhap)){
                        jlbMessage.setText("Cập nhật thành công!");
                    } else{
                        jlbMessage.setText("Cập nhật thất bại!");
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
