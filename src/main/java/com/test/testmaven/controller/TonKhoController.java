/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.TonKhoServiceimpl;
import com.test.hibernate.entities.TonKho;
import com.test.testmaven.manage.ManageTonKho;
import com.test.testmaven.utility.ClassTableModelTonKho;
import com.test.testmaven.views.KhoJFrame;
import com.test.testmaven.views.KhoJFrameUpdate;
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
public class TonKhoController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private TonKhoServiceimpl khoService = null;

    private String[] listColumn = {"STT","MaVTu","NamThang","SLDau","SLCuoi","TongSLN","TongSLX","",""};
    private TableRowSorter<TableModel> rowSorter = null;
    
    public TonKhoController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefesh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefesh;
        this.khoService = new TonKhoServiceimpl();
    }
    
    public void setDateToTable(){
        List<TonKho> listItem = khoService.getList();
        DefaultTableModel tableModel = new ClassTableModelTonKho().setTableTonKho(listItem, listColumn);
        
        JTable tableTonKho = new JTable(tableModel);
        
        rowSorter = new TableRowSorter<>(tableTonKho.getModel());
        
        tableTonKho.setRowSorter(rowSorter);
        
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
        
        tableTonKho.getColumnModel().getColumn(0).setMinWidth(40);
        tableTonKho.getColumnModel().getColumn(0).setMaxWidth(40);
        tableTonKho.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        tableTonKho.getColumnModel().getColumn(1).setMinWidth(80);
        tableTonKho.getColumnModel().getColumn(1).setMaxWidth(80);
        tableTonKho.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        tableTonKho.getColumnModel().getColumn(7).setMinWidth(80);
        tableTonKho.getColumnModel().getColumn(7).setMaxWidth(80);
        tableTonKho.getColumnModel().getColumn(7).setPreferredWidth(80);
        
        tableTonKho.getColumnModel().getColumn(8).setMinWidth(80);
        tableTonKho.getColumnModel().getColumn(8).setMaxWidth(80);
        tableTonKho.getColumnModel().getColumn(8).setPreferredWidth(80);
        
        tableTonKho.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        tableTonKho.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableTonKho.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        tableTonKho.getColumnModel().getColumn(8).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        tableTonKho.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 1 && tableTonKho.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) tableTonKho.getModel();
                    int selectedRowIndex = tableTonKho.getSelectedRow();
                    selectedRowIndex = tableTonKho.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    
                    TonKho tonKho = new TonKho();
                    tonKho.setMaVTu(model.getValueAt(selectedRowIndex, 1).toString());
                    tonKho.setNamThang(model.getValueAt(selectedRowIndex, 2).toString());
                    tonKho = new ManageTonKho().getVatTu(model.getValueAt(selectedRowIndex, 1).toString(), model.getValueAt(selectedRowIndex, 2).toString());
                    if(tableTonKho.getSelectedColumn()==7){
                        KhoJFrameUpdate tonKhoJrame = new KhoJFrameUpdate(tonKho);
                        tonKhoJrame.setTitle("Thông tin vật tư");
                        tonKhoJrame.setResizable(false);
                        tonKhoJrame.setLocationRelativeTo(null);
                        tonKhoJrame.setVisible(true);
                    }
                    if(tableTonKho.getSelectedColumn()==8){
                        if(khoService.deleteStatus(tonKho)==1){
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công");
                        } else{
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa chi tiêt tồn kho này");
                        }
                    }
                                        
                }
            }
        });
        
        tableTonKho.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableTonKho.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableTonKho.setRowHeight(50);
        tableTonKho.validate();
        tableTonKho.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableTonKho);
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
                KhoJFrame khofJFrame = new KhoJFrame(new TonKho());
                khofJFrame.setTitle("Thông tin tồn kho");
                khofJFrame.setLocationRelativeTo(null);
                khofJFrame.setResizable(false);
                khofJFrame.setVisible(true);
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
}

//class ButtonRenderer extends JButton implements TableCellRenderer {
//
//    public ButtonRenderer() {
//        setOpaque(true);
//    }
//
//    public Component getTableCellRendererComponent(JTable table, Object value,
//            boolean isSelected, boolean hasFocus, int row, int column) {
//        if (isSelected) {
//            setForeground(table.getSelectionForeground());
//            setBackground(table.getSelectionBackground());
//        } else {
//            setForeground(table.getForeground());
//            setBackground(UIManager.getColor("Button.background"));
//        }
//        setText((value == null) ? "" : value.toString());
//        return this;
//    }
//}

///**
// * @version 1.0 11/09/98
// */
//class ButtonEditor extends DefaultCellEditor {
//
//    protected JButton button;
//
//    private String label;
//
//    private boolean isPushed;
//
//    public ButtonEditor(JCheckBox checkBox) {
//        super(checkBox);
//        button = new JButton();
//        button.setOpaque(true);
//        button.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("xx ");
//                fireEditingStopped();
//            }
//        });
//    }
//
//    public Component getTableCellEditorComponent(JTable table, Object value,
//            boolean isSelected, int row, int column) {
//        if (isSelected) {
//            button.setForeground(table.getSelectionForeground());
//            button.setBackground(table.getSelectionBackground());
//        } else {
//            button.setForeground(table.getForeground());
//            button.setBackground(table.getBackground());
//        }
//        label = (value == null) ? "" : value.toString();
//        button.setText(label);
//        isPushed = true;
//        return button;
//    }
//
//    public Object getCellEditorValue() {
//        if (isPushed) {
//            // 
//            // 
//            JOptionPane.showMessageDialog(button, label + ": Ouch!");
//            // System.out.println(label + ": Ouch!");
//        }
//        isPushed = false;
//        return new String(label);
//    }
//
//    @Override
//    public boolean stopCellEditing() {
//        isPushed = false;
//        return super.stopCellEditing();
//    }
//
//    @Override
//    protected void fireEditingStopped() {
//        super.fireEditingStopped();
//    }
//}
