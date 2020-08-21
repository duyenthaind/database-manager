package com.test.testmaven.controller;

import TableDAO.DonDhServiceimpl;
import com.test.hibernate.entities.DonDh;
import com.test.testmaven.manage.ManageDonDh;
import com.test.testmaven.utility.ClassTableModelDonDh;
import com.test.testmaven.views.DonDhJFrame;
import com.test.testmaven.views.DonDhJFrameUpdate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Admin
 */
public class DonDhController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private DonDhServiceimpl donDhService = null;

    private String[] listColumn = {"STT","SoDh","MaNCC","NgayDh","",""};
    private TableRowSorter<TableModel> rowSorter = null;

    public DonDhController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.donDhService = new DonDhServiceimpl();
    }
    
    
    
    public void setDateToTable(){
        List<DonDh> listItem = donDhService.getList();
        DefaultTableModel tableModel = new ClassTableModelDonDh().setTableDonDh(listItem, listColumn);
        
        JTable tableDonDh = new JTable(tableModel);
        
        rowSorter = new TableRowSorter<>(tableDonDh.getModel());
        
        tableDonDh.setRowSorter(rowSorter);
        
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
        
        tableDonDh.getColumnModel().getColumn(0).setMinWidth(40);
        tableDonDh.getColumnModel().getColumn(0).setMaxWidth(40);
        tableDonDh.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        tableDonDh.getColumnModel().getColumn(1).setMinWidth(80);
        tableDonDh.getColumnModel().getColumn(1).setMaxWidth(80);
        tableDonDh.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        tableDonDh.getColumnModel().getColumn(4).setMinWidth(80);
        tableDonDh.getColumnModel().getColumn(4).setMaxWidth(80);
        tableDonDh.getColumnModel().getColumn(4).setPreferredWidth(80);

        tableDonDh.getColumnModel().getColumn(5).setMinWidth(80);
        tableDonDh.getColumnModel().getColumn(5).setMaxWidth(80);
        tableDonDh.getColumnModel().getColumn(5).setPreferredWidth(80);

        tableDonDh.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tableDonDh.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableDonDh.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableDonDh.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        tableDonDh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 1 && tableDonDh.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) tableDonDh.getModel();
                    int selectedRowIndex = tableDonDh.getSelectedRow();
                    selectedRowIndex = tableDonDh.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    
                    DonDh donDh = new DonDh();
                    donDh.setSoDh(model.getValueAt(selectedRowIndex, 1).toString());
                    donDh = new ManageDonDh().getChiTietDonHang(model.getValueAt(selectedRowIndex, 1).toString());
                    if(tableDonDh.getSelectedColumn() == 4){
                        DonDhJFrameUpdate donDhJFrame = new DonDhJFrameUpdate(donDh);
                        donDhJFrame.setTitle("Thông tin đơn hàng");
                        donDhJFrame.setResizable(false);
                        donDhJFrame.setLocationRelativeTo(null);
                        donDhJFrame.setVisible(true);
                    }
                    if(tableDonDh.getSelectedColumn() == 5){
                        if(donDhService.deleteStatus(donDh)){
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công!");
                        } else{
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa đơn đặt hàng này!");
                        }
                    }
                    
                    
                }
            }
        });
        
        tableDonDh.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableDonDh.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableDonDh.setRowHeight(50);
        tableDonDh.validate();
        tableDonDh.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableDonDh);
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
                DonDhJFrame donDhJFrame = new DonDhJFrame(new DonDh());
                donDhJFrame.setTitle("Thông tin vật tư");
                donDhJFrame.setLocationRelativeTo(null);
                donDhJFrame.setResizable(false);
                donDhJFrame.setVisible(true);
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
