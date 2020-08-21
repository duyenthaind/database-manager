package com.test.testmaven.controller;

import TableDAO.DonDhServiceimpl;
import com.test.hibernate.entities.DonDh;
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
public class ManageDonDhControllerUpdate {
    private JButton btnSubmit;
    private JTextField jtfSoDh;
    private JDateChooser jdcNgayDatHang;
    private JComboBox jcbMaNhaCungCap;
    private JLabel jlbMessage;
    
    private DonDhServiceimpl donDhService = null;
    
    private DonDh donDh = null;

    public ManageDonDhControllerUpdate(JButton btnSubmit, JTextField jtfSoDh, JDateChooser jdcNgayDatHang, JComboBox jcbMaNhaCungCap, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jtfSoDh = jtfSoDh;
        this.jdcNgayDatHang = jdcNgayDatHang;
        this.jcbMaNhaCungCap = jcbMaNhaCungCap;
        this.jlbMessage = jlbMessage;
        this.donDhService = new DonDhServiceimpl();
    }
    
    public void setView(DonDh donDh){
        this.donDh = donDh;
        jtfSoDh.setText(donDh.getSoDh());
        jdcNgayDatHang.setDate(donDh.getNgayDh());
        jcbMaNhaCungCap.setSelectedItem(donDh.getMaNhaCc());
        jtfSoDh.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfSoDh.getText().length() == 0){
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else{
                    donDh.setSoDh(jtfSoDh.getText());
                    donDh.setMaNhaCc(jcbMaNhaCungCap.getItemAt(jcbMaNhaCungCap.getSelectedIndex()).toString());
                    System.out.println(jdcNgayDatHang.getDate());
                    try{
                        donDh.setNgayDh(jdcNgayDatHang.getDate());
                    } catch(Exception ex){
                        ex.printStackTrace();
                        donDh.setNgayDh(null);
                    }
                    if(donDhService.update(donDh)){
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
