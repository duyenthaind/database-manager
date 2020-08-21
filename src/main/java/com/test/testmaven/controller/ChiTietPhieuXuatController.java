/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.ChiTietPhieuXuatServiceimpl;
import com.test.hibernate.entities.ChiTietPhieuXuat;
import com.test.testmaven.manage.ManageChiTietPhieuXuat;
import com.test.testmaven.utility.ClassTableModelChiTietPhieuXuat;
import com.test.testmaven.views.ChiTietPhieuXuatJFrame;
import com.test.testmaven.views.ChiTietPhieuXuatJFrameUpdate;
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
public class ChiTietPhieuXuatController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private ChiTietPhieuXuatServiceimpl chiTietPhieuXuatService = null;

    private String[] listColumn = {"STT","SoPx","MaVTu","SLXuat","DgXuat","",""};
    private TableRowSorter<TableModel> rowSorter = null;

    public ChiTietPhieuXuatController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.chiTietPhieuXuatService = new ChiTietPhieuXuatServiceimpl();
    }

    
    public void setDateToTable(){
        List<ChiTietPhieuXuat> listItem = chiTietPhieuXuatService.getList();
        DefaultTableModel tableModel = new ClassTableModelChiTietPhieuXuat().setTableChiTietPhieuXuat(listItem, listColumn);
        
        JTable tableChiTietPhieuXuat = new JTable(tableModel);
        
        rowSorter = new TableRowSorter<>(tableChiTietPhieuXuat.getModel());
        
        tableChiTietPhieuXuat.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfSearch.getText();
                if(text.trim().length() == 0){
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        tableChiTietPhieuXuat.getColumnModel().getColumn(0).setMinWidth(40);
        tableChiTietPhieuXuat.getColumnModel().getColumn(0).setMaxWidth(40);
        tableChiTietPhieuXuat.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        tableChiTietPhieuXuat.getColumnModel().getColumn(1).setMinWidth(80);
        tableChiTietPhieuXuat.getColumnModel().getColumn(1).setMaxWidth(80);
        tableChiTietPhieuXuat.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        tableChiTietPhieuXuat.getColumnModel().getColumn(5).setMinWidth(80);
        tableChiTietPhieuXuat.getColumnModel().getColumn(5).setMaxWidth(80);
        tableChiTietPhieuXuat.getColumnModel().getColumn(5).setPreferredWidth(80);

        tableChiTietPhieuXuat.getColumnModel().getColumn(6).setMinWidth(80);
        tableChiTietPhieuXuat.getColumnModel().getColumn(6).setMaxWidth(80);
        tableChiTietPhieuXuat.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        tableChiTietPhieuXuat.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableChiTietPhieuXuat.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableChiTietPhieuXuat.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableChiTietPhieuXuat.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        tableChiTietPhieuXuat.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1 && tableChiTietPhieuXuat.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) tableChiTietPhieuXuat.getModel();
                    int selectedRowIndex = tableChiTietPhieuXuat.getSelectedRow();
                    selectedRowIndex = tableChiTietPhieuXuat.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    ChiTietPhieuXuat chiTietPhieuXuat = new ChiTietPhieuXuat();
                    chiTietPhieuXuat.setSoPx(model.getValueAt(selectedRowIndex, 1).toString());
                    chiTietPhieuXuat = new ManageChiTietPhieuXuat().getChiTietPhieuXuat(model.getValueAt(selectedRowIndex, 1).toString(),model.getValueAt(selectedRowIndex, 2).toString());

                    if (tableChiTietPhieuXuat.getSelectedColumn() == 5) {
                        ChiTietPhieuXuatJFrameUpdate chiTietPhieuXuatJFrame = new ChiTietPhieuXuatJFrameUpdate(chiTietPhieuXuat);
                        chiTietPhieuXuatJFrame.setTitle("Thông tin chi tiết phiếu nhập");
                        chiTietPhieuXuatJFrame.setResizable(false);
                        chiTietPhieuXuatJFrame.setLocationRelativeTo(null);
                        chiTietPhieuXuatJFrame.setVisible(true);
                    }
                    if (tableChiTietPhieuXuat.getSelectedColumn() == 6) {
                        if (chiTietPhieuXuatService.deleteStatus(chiTietPhieuXuat)) {
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa chi tiêt chi tiết phiếu xuất này");
                        }
                    }

                }
            }
        });
        
        tableChiTietPhieuXuat.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableChiTietPhieuXuat.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableChiTietPhieuXuat.setRowHeight(50);
        tableChiTietPhieuXuat.validate();
        tableChiTietPhieuXuat.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableChiTietPhieuXuat);
        scrollPane.setPreferredSize(new Dimension(1300, 400));
        
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scrollPane);
        jpnView.validate();
        jpnView.repaint();
    }
    
    public void setEvent(){
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChiTietPhieuXuatJFrame chiTietPhieuXuatJFrame = new ChiTietPhieuXuatJFrame(new ChiTietPhieuXuat());
                chiTietPhieuXuatJFrame.setTitle("Thông tin chi tiết phiếu xuất");
                chiTietPhieuXuatJFrame.setLocationRelativeTo(null);
                chiTietPhieuXuatJFrame.setResizable(false);
                chiTietPhieuXuatJFrame.setVisible(true);
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
