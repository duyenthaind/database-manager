/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.PhieuNhapServiceimpl;
import com.test.hibernate.entities.PhieuNhap;
import com.test.testmaven.manage.ManagePhieuNhap;
import com.test.testmaven.utility.ClassTableModelPhieuNhap;
import com.test.testmaven.views.PhieuNhapJFrame;
import com.test.testmaven.views.PhieuNhapJFrameUpdate;
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
public class PhieuNhapController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private PhieuNhapServiceimpl phieuNhapService = null;

    private String[] listColumn = {"STT", "SoPn", "SoDh", "NgayNhap", "", ""};
    private TableRowSorter<TableModel> rowSorter = null;

    public PhieuNhapController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.phieuNhapService = new PhieuNhapServiceimpl();
    }

    public void setDateToTable() {
        List<PhieuNhap> listItem = phieuNhapService.getList();
        DefaultTableModel tableModel = new ClassTableModelPhieuNhap().setTablePhieuNhap(listItem, listColumn);

        JTable tablePhieuNhap = new JTable(tableModel);

        rowSorter = new TableRowSorter<>(tablePhieuNhap.getModel());

        tablePhieuNhap.setRowSorter(rowSorter);

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

        tablePhieuNhap.getColumnModel().getColumn(0).setMinWidth(40);
        tablePhieuNhap.getColumnModel().getColumn(0).setMaxWidth(40);
        tablePhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(40);

        tablePhieuNhap.getColumnModel().getColumn(1).setMinWidth(80);
        tablePhieuNhap.getColumnModel().getColumn(1).setMaxWidth(80);
        tablePhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(80);

        tablePhieuNhap.getColumnModel().getColumn(4).setMinWidth(80);
        tablePhieuNhap.getColumnModel().getColumn(4).setMaxWidth(80);
        tablePhieuNhap.getColumnModel().getColumn(4).setPreferredWidth(80);

        tablePhieuNhap.getColumnModel().getColumn(5).setMinWidth(80);
        tablePhieuNhap.getColumnModel().getColumn(5).setMaxWidth(80);
        tablePhieuNhap.getColumnModel().getColumn(5).setPreferredWidth(80);

        tablePhieuNhap.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tablePhieuNhap.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        tablePhieuNhap.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tablePhieuNhap.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

        tablePhieuNhap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1 && tablePhieuNhap.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) tablePhieuNhap.getModel();
                    int selectedRowIndex = tablePhieuNhap.getSelectedRow();
                    selectedRowIndex = tablePhieuNhap.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    PhieuNhap phieuNhap = new PhieuNhap();
                    phieuNhap.setSoPn(model.getValueAt(selectedRowIndex, 1).toString());
                    phieuNhap = new ManagePhieuNhap().getPhieuNhap(model.getValueAt(selectedRowIndex, 1).toString());

                    if (tablePhieuNhap.getSelectedColumn() == 4) {
                        PhieuNhapJFrameUpdate phieuNhapJFrame = new PhieuNhapJFrameUpdate(phieuNhap);
                        phieuNhapJFrame.setTitle("Thông tin phiếu nhập");
                        phieuNhapJFrame.setResizable(false);
                        phieuNhapJFrame.setLocationRelativeTo(null);
                        phieuNhapJFrame.setVisible(true);
                    }
                    if (tablePhieuNhap.getSelectedColumn() == 5) {
                        if (phieuNhapService.deleteStatus(phieuNhap)) {
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa phiếu xuất này");
                        }
                    }

                }
            }
        });

        tablePhieuNhap.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tablePhieuNhap.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tablePhieuNhap.setRowHeight(50);
        tablePhieuNhap.validate();
        tablePhieuNhap.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tablePhieuNhap);
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
                PhieuNhapJFrame phieuNhapJFrame = new PhieuNhapJFrame(new PhieuNhap());
                phieuNhapJFrame.setTitle("Thông tin phiếu nhập");
                phieuNhapJFrame.setLocationRelativeTo(null);
                phieuNhapJFrame.setResizable(false);
                phieuNhapJFrame.setVisible(true);
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
