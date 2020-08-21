package com.test.testmaven.controller;

import TableDAO.DonDhServiceimpl;
import com.test.hibernate.entities.DonDh;
import com.toedter.calendar.JDateChooser;
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
public class ManageDonDhController {
    private JButton btnSubmit;
    private JTextField jtfSoDh;
    private JDateChooser jdcNgayDatHang;
    private JComboBox jcbMaNhaCungCap;
    private JLabel jlbMessage;
    
    private DonDhServiceimpl donDhService = null;
    
    private DonDh donDh = null;

    public ManageDonDhController(JButton btnSubmit, JTextField jtfSoDh, JDateChooser jdcNgayDatHang, JComboBox jcbMaNhaCungCap, JLabel jlbMessage) {
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
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfSoDh.getText().length() == 0){
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else{
                    if(jtfSoDh.getText().length()>4){
                        JOptionPane.showMessageDialog(new JFrame(), "Số đơn hàng chứa tối đa 4 kí tự");
                        return;
                    }
                    donDh.setSoDh(jtfSoDh.getText());
                    donDh.setMaNhaCc(jcbMaNhaCungCap.getItemAt(jcbMaNhaCungCap.getSelectedIndex()).toString());
                    try{
                        donDh.setNgayDh(jdcNgayDatHang.getDate());
                    } catch(Exception ex){
                        ex.printStackTrace();
                        donDh.setNgayDh(null);
                    }
                    if(donDhService.create(donDh)){
                        jlbMessage.setText("Thêm mới thành công!");
                    } else{
                        jlbMessage.setText("Dữ liệu đã tồn tại");
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
