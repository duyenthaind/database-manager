package com.test.testmaven.controller;

import TableDAO.PhieuXuatServiceimpl;
import com.test.hibernate.entities.PhieuXuat;
import com.toedter.calendar.JDateChooser;
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
public class ManagePhieuXuatController {
    private JButton btnSubmit;
    private JTextField jtfSoPx;
    private JTextField jtfTenKh;
    private JDateChooser jdcNgayXuat;
    private JLabel jlbMessage;
    
    private PhieuXuatServiceimpl phieuXuatService = null;
    
    private PhieuXuat phieuXuat = null;

    public ManagePhieuXuatController(JButton btnSubmit, JTextField jtfSoPx, JTextField jtfTenKh, JDateChooser jdcNgayXuat, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jtfSoPx = jtfSoPx;
        this.jtfTenKh = jtfTenKh;
        this.jdcNgayXuat = jdcNgayXuat;
        this.jlbMessage = jlbMessage;
        this.phieuXuatService = new PhieuXuatServiceimpl();
    }
    
    
    
    public void setView(PhieuXuat phieuXuat){
        this.phieuXuat = phieuXuat;
        jtfSoPx.setText(phieuXuat.getSoPx());
        jdcNgayXuat.setDate(phieuXuat.getNgayXuat());
        jtfTenKh.setText(phieuXuat.getTenKh());
    }
    
    public void setEvent(){
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfSoPx.getText().length() == 0 || jtfSoPx.getText().equalsIgnoreCase("#null")){
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else{
                    if(jtfSoPx.getText().length() > 4){
                        JOptionPane.showMessageDialog(new JFrame(), "Số phiếu xuất chứa tối đa 4 kí tự");
                        return;
                    }
                    phieuXuat.setSoPx(jtfSoPx.getText());
                    phieuXuat.setTenKh(jtfTenKh.getText());
                    try{
                        phieuXuat.setNgayXuat(jdcNgayXuat.getDate());
                    } catch(Exception ex){
                        ex.printStackTrace();
                        phieuXuat.setNgayXuat(null);
                    }
                    if(phieuXuatService.create(phieuXuat)){
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
