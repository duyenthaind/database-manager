/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.NhaCungCapServiceimpl;
import com.test.hibernate.entities.NhaCungCap;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class ManageNhaCungCapControllerUpdate {
    private JButton btnSubmit;
    private JTextField jtfMaNCC;
    private JTextField jtfTenNCC;
    private JTextField jtfDiaChi;
    private JTextField jtfDienThoai;
    private JLabel jlbMessage;
    
    private NhaCungCapServiceimpl nhaCungCapService = null;
    
    private NhaCungCap nhaCungCap = null;

    public ManageNhaCungCapControllerUpdate(JButton btnSubmit, JTextField jtfMaNCC, JTextField jtfTenNCC, JTextField jtfDiaChi, JTextField jtfDienThoai, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jtfMaNCC = jtfMaNCC;
        this.jtfTenNCC = jtfTenNCC;
        this.jtfDiaChi = jtfDiaChi;
        this.jtfDienThoai = jtfDienThoai;
        this.jlbMessage = jlbMessage;
        this.nhaCungCapService = new NhaCungCapServiceimpl();
    }
    
    
    
    public void setView(NhaCungCap nhaCungCapIn){
        this.nhaCungCap = nhaCungCapIn;
        jtfMaNCC.setText(nhaCungCap.getMaNhaCc());
        jtfTenNCC.setText(nhaCungCap.getTenNhaCc());
        jtfDiaChi.setText(nhaCungCap.getDiaChi());
        jtfDienThoai.setText(nhaCungCap.getDienThoai());
        jtfMaNCC.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfMaNCC.getText().length() == 0 || jtfMaNCC.getText().equalsIgnoreCase("#null")){
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else{
                    if(jtfMaNCC.getText().length()>3){
                        JOptionPane.showMessageDialog(new JFrame(), "Mã nhà cung cấp chứa nhiều nhất 3 kí tự");
                        return;
                    } 
                    nhaCungCap.setMaNhaCc(jtfMaNCC.getText());
                    nhaCungCap.setTenNhaCc(jtfTenNCC.getText());
                    nhaCungCap.setDiaChi(jtfDiaChi.getText());
                    if(jtfDienThoai.getText().matches("^[0]([0-9]){9}$")){
                        nhaCungCap.setDienThoai(jtfDienThoai.getText());
                    } else{
                        JOptionPane.showMessageDialog(new JFrame(), "Số điện thoại phải có 10 chữ số");
                        return;
                    }
                    if(nhaCungCapService.update(nhaCungCap)){
                        jlbMessage.setText("Cập nhật thành công!");
                    } else{
                        jlbMessage.setText("Cập nhật thất bại");
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
