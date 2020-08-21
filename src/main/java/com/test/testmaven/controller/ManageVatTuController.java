package com.test.testmaven.controller;

import TableDAO.VatTuServiceimpl;
import com.test.hibernate.entities.VatTu;
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
public class ManageVatTuController {

    private JButton btnSubmit;
    private JTextField jtfMaVTu;
    private JTextField jtfTenVTu;
    private JTextField jtfDVTinh;
    private JTextField jtfPhanTram;
    private JLabel jlbMessage;

    private VatTuServiceimpl vatTuService = null;

    private VatTu vatTu = null;

    public ManageVatTuController(JButton btnSubmit, JTextField jtfMaVTu, JTextField jtfTenVTu, JTextField jtfDVTinh, JTextField jtfPhanTram, JLabel jlbMessage) {
        this.btnSubmit = btnSubmit;
        this.jtfMaVTu = jtfMaVTu;
        this.jtfTenVTu = jtfTenVTu;
        this.jtfDVTinh = jtfDVTinh;
        this.jtfPhanTram = jtfPhanTram;
        this.jlbMessage = jlbMessage;
        this.vatTuService = new VatTuServiceimpl();
    }

    public void setView(VatTu vatTu) {
        this.vatTu = vatTu;
        jtfMaVTu.setText(vatTu.getMaVTu());
        jtfTenVTu.setText(vatTu.getTenVTu());
        jtfDVTinh.setText(vatTu.getDvTinh());
        jtfPhanTram.setText(vatTu.getPhanTram() + "");
    }

    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (jtfMaVTu.getText().length() == 0 || jtfMaVTu.getText().equalsIgnoreCase("null")) {
                    jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                } else {
                    if (jtfMaVTu.getText().length() == 0 || jtfMaVTu.getText().equalsIgnoreCase("null")) {
                        jlbMessage.setText("Vui lòng nhập dữ liệu bắt buộc");
                    } else {
                        if (jtfMaVTu.getText().length() > 4) {
                            JOptionPane.showMessageDialog(new JFrame(), "Mã vật tư chứa nhiều nhất 4 kí tự");
                            return;
                        }
                        vatTu.setMaVTu(jtfMaVTu.getText());
                        vatTu.setTenVTu(jtfTenVTu.getText());
                        vatTu.setDvTinh(jtfDVTinh.getText());
                        vatTu.setPhanTram(Double.valueOf(jtfPhanTram.getText()));
                        int status = vatTuService.create(vatTu);
                        if (status == 1) {
                            jlbMessage.setText("Thêm thành công!");
                        } else {
                            jlbMessage.setText("Dữ liệu đã tồn tại!");
                        }
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
        }
        );
    }
}
