/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.ChiTietPhieuNhapServiceimpl;
import com.test.hibernate.entities.ChiTietPhieuNhap;
import com.test.testmaven.manage.ManageChiTietPhieuNhap;
import com.test.testmaven.utility.ClassTableModelChiTietPhieuNhap;
import com.test.testmaven.views.ChiTietPhieuNhapJFrame;
import com.test.testmaven.views.ChiTietPhieuNhapJFrameUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhapController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private ChiTietPhieuNhapServiceimpl chiTietphieuNhapService = null;

    private String[] listColumn = {"STT", "SoPn", "MaVTu", "SLNhap", "DgNhap", "", ""};
    private TableRowSorter<TableModel> rowSorter = null;

    public ChiTietPhieuNhapController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.chiTietphieuNhapService = new ChiTietPhieuNhapServiceimpl();
    }

    public void setDateToTable() {
        List<ChiTietPhieuNhap> listItem = chiTietphieuNhapService.getList();
        DefaultTableModel tableModel = new ClassTableModelChiTietPhieuNhap().setTableChiTietPhieuNhap(listItem, listColumn);

        JTable tableChiTietPhieuNhap = new JTable(tableModel);

        rowSorter = new TableRowSorter<>(tableChiTietPhieuNhap.getModel());

        tableChiTietPhieuNhap.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        tableChiTietPhieuNhap.getColumnModel().getColumn(0).setMinWidth(40);
        tableChiTietPhieuNhap.getColumnModel().getColumn(0).setMaxWidth(40);
        tableChiTietPhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(40);

        tableChiTietPhieuNhap.getColumnModel().getColumn(1).setMinWidth(80);
        tableChiTietPhieuNhap.getColumnModel().getColumn(1).setMaxWidth(80);
        tableChiTietPhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(80);

        tableChiTietPhieuNhap.getColumnModel().getColumn(5).setMinWidth(80);
        tableChiTietPhieuNhap.getColumnModel().getColumn(5).setMaxWidth(80);
        tableChiTietPhieuNhap.getColumnModel().getColumn(5).setPreferredWidth(80);

        tableChiTietPhieuNhap.getColumnModel().getColumn(6).setMinWidth(80);
        tableChiTietPhieuNhap.getColumnModel().getColumn(6).setMaxWidth(80);
        tableChiTietPhieuNhap.getColumnModel().getColumn(6).setPreferredWidth(80);

        tableChiTietPhieuNhap.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableChiTietPhieuNhap.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableChiTietPhieuNhap.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableChiTietPhieuNhap.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));

        tableChiTietPhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1 && tableChiTietPhieuNhap.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) tableChiTietPhieuNhap.getModel();
                    int selectedRowIndex = tableChiTietPhieuNhap.getSelectedRow();
                    selectedRowIndex = tableChiTietPhieuNhap.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap();
                    chiTietPhieuNhap.setSoPn(model.getValueAt(selectedRowIndex, 1).toString());
                    chiTietPhieuNhap = new ManageChiTietPhieuNhap().getChiTietPhieuNhap(model.getValueAt(selectedRowIndex, 1).toString(), model.getValueAt(selectedRowIndex, 2).toString());

                    if (tableChiTietPhieuNhap.getSelectedColumn() == 5) {
                        ChiTietPhieuNhapJFrameUpdate chiTietPhieuNhapJFrame = new ChiTietPhieuNhapJFrameUpdate(chiTietPhieuNhap);
                        chiTietPhieuNhapJFrame.setTitle("Thông tin chi tiết phiếu nhập");
                        chiTietPhieuNhapJFrame.setResizable(false);
                        chiTietPhieuNhapJFrame.setLocationRelativeTo(null);
                        chiTietPhieuNhapJFrame.setVisible(true);
                    }
                    if (tableChiTietPhieuNhap.getSelectedColumn() == 6) {
                        if (chiTietphieuNhapService.deleteStatus(chiTietPhieuNhap)) {
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa chi tiêt chi tiết phiếu nhập này");
                        }
                    }

                }
            }
        });

        tableChiTietPhieuNhap.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableChiTietPhieuNhap.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableChiTietPhieuNhap.setRowHeight(50);
        tableChiTietPhieuNhap.validate();
        tableChiTietPhieuNhap.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableChiTietPhieuNhap);
        scrollPane.setPreferredSize(new Dimension(1300, 400));

        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }

    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChiTietPhieuNhapJFrame chiTietPhieuNhapJFrame = new ChiTietPhieuNhapJFrame(new ChiTietPhieuNhap());
                chiTietPhieuNhapJFrame.setTitle("Thông tin chi tiết phiếu nhập");
                chiTietPhieuNhapJFrame.setLocationRelativeTo(null);
                chiTietPhieuNhapJFrame.setResizable(false);
                chiTietPhieuNhapJFrame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
            }
        });
        
        btnRefresh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setDateToTable();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                btnRefresh.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnRefresh.setBackground(new Color(100, 221, 23));
            }
        });

    }

    /*To render button*/
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    /**
     * @version 1.0 11/09/98
     */
    class ButtonEditor extends DefaultCellEditor {

        protected JButton button;

        private String label;

        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("xx ");
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // 
                // 
                JOptionPane.showMessageDialog(button, label + ": Ouch!");
                // System.out.println(label + ": Ouch!");
            }
            isPushed = false;
            return new String(label);
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
