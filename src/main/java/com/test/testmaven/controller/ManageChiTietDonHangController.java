package com.test.testmaven.controller;

import TableDAO.ChiTietDonHangServiceimpl;
import com.test.hibernate.entities.ChiTietDonHang;
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
public class ManageChiTietDonHangController {
    private JButton btnSubmit;
    private JComboBox jcbSoDh;
    private JComboBox jcbMaVTu;
    private JTextField jtfSlDat;
    private JLabel jlbMessage;
    
    private ChiTietDonHangServiceimpl chiTietDonHangService = null;
    
    private ChiTietDonHang chiTietDonHang = null;

    public ManageChiTietDonHangController(JButton btnSubmit, JComboBox jcbSoDh, JComboBox jcbMaVTu, JTextField jtfSlDat, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jcbSoDh = jcbSoDh;
        this.jcbMaVTu = jcbMaVTu;
        this.jtfSlDat = jtfSlDat;
        this.jlbMessage = jlbMessage;
        this.chiTietDonHangService = new ChiTietDonHangServiceimpl();
    }
    
    public void setView(ChiTietDonHang chiTietDonHangIn){
        this.chiTietDonHang = chiTietDonHangIn;
        jcbMaVTu.setSelectedItem(chiTietDonHang.getMaVTu());
        jcbSoDh.setSelectedItem(chiTietDonHang.getSoDh());
        jtfSlDat.setText(chiTietDonHang.getSlDat()+"");
        jcbMaVTu.setEditable(false);
        jcbSoDh.setEditable(false);
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jcbMaVTu.getSelectedItem() == null || jcbSoDh.getSelectedItem() == null){
                    jlbMessage.setText("Vui lòng chọn dữ liệu bắt buộc");
                } else{
                    chiTietDonHang.setMaVTu(jcbMaVTu.getItemAt(jcbMaVTu.getSelectedIndex()).toString());
                    chiTietDonHang.setSoDh(jcbSoDh.getItemAt(jcbSoDh.getSelectedIndex()).toString());
                    chiTietDonHang.setSlDat(Integer.parseInt(jtfSlDat.getText()));
                    if(chiTietDonHangService.create(chiTietDonHang)){
                        jlbMessage.setText("Thêm mới thành công");
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
