/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.TonKhoServiceimpl;
import com.test.hibernate.entities.TonKho;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class ManageKhoControllerUpdate {
    private JButton btnSubmit;
    private JTextField jtfNamThang;
    private JTextField jtfTongSLNhap;
    private JTextField jtfTongSLXuat;
    private JTextField jtfSoLuongDau;
    private JTextField jtfSoLuongCuoi;
    private JLabel jlbMessage;
    private JComboBox jcbMaVTu;
    
    private TonKhoServiceimpl khoService = null;
    
    private TonKho tonKho = null;

    public ManageKhoControllerUpdate(JButton btnSubmit, JTextField jtfNamThang, JTextField jtfTongSLNhap, JTextField jtfTongSLXuat, JTextField jtfSoLuongDau, JTextField jtfSoLuongCuoi, JLabel jlbMessage, JComboBox jcbMaVTu) {
        this.btnSubmit = btnSubmit;
        this.jtfNamThang = jtfNamThang;
        this.jtfTongSLNhap = jtfTongSLNhap;
        this.jtfTongSLXuat = jtfTongSLXuat;
        this.jtfSoLuongDau = jtfSoLuongDau;
        this.jtfSoLuongCuoi = jtfSoLuongCuoi;
        this.jlbMessage = jlbMessage;
        this.jcbMaVTu = jcbMaVTu;
        this.khoService  = new TonKhoServiceimpl();
    }
    
    
    
    public void setView(TonKho tonKho){
        this.tonKho = tonKho;
        jtfNamThang.setText(tonKho.getNamThang());
        jtfSoLuongDau.setText(tonKho.getSlDau() + "");  //String.valueOf(tonKho.getSlDau())
        jtfSoLuongCuoi.setText(tonKho.getSLCuoi() + "");
        jtfTongSLNhap.setText(tonKho.getTongSLN() + "");
        jtfTongSLXuat.setText(tonKho.getTongSLX() + "");
        jcbMaVTu.setSelectedItem(tonKho.getMaVTu());
        jcbMaVTu.setEditable(false);
        jcbMaVTu.setEnabled(false);
        jtfNamThang.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfNamThang.getText().length() == 0){
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else{
                    if(jtfNamThang.getText().matches("^[12]([0-9]){3}[0][123456789]$")||jtfNamThang.getText().matches("^[12]([0-9]){3}[1][02]$")){
                        tonKho.setNamThang(jtfNamThang.getText());
                    } else{
                        JOptionPane.showMessageDialog(new JFrame(), "Năm tháng gồm 6 chữ số (VD: 202006)");
                        return;
                    }
                    tonKho.setSlDau(Integer.parseInt(jtfSoLuongDau.getText()));
                    tonKho.setSLCuoi(Integer.parseInt(jtfSoLuongCuoi.getText()));
                    tonKho.setTongSLN(Integer.parseInt(jtfTongSLNhap.getText()));
                    tonKho.setTongSLX(Integer.parseInt(jtfTongSLXuat.getText()));
                    if(khoService.update(tonKho)){
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
