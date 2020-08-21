package com.test.testmaven.controller;

import TableDAO.NhaCungCapServiceimpl;
import com.test.hibernate.entities.NhaCungCap;
import com.test.testmaven.manage.ManageNhaCungCap;
import com.test.testmaven.utility.ClassTableModelNhaCungCap;
import com.test.testmaven.views.NhaCungCapJFrame;
import com.test.testmaven.views.NhaCungCapJFrameUpdate;
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
public class NhaCungCapController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnRefresh;
    private JTextField jtfSearch;
    private NhaCungCapServiceimpl nhaCungCapService = null;

    private String[] listColumn = {"STT","MaNhaCc","TenNhaCc","DiaChi","DienThoai","",""};
    private TableRowSorter<TableModel> rowSorter = null;
    
    public NhaCungCapController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.nhaCungCapService = new NhaCungCapServiceimpl();
    }
    
    public void setDateToTable(){
        List<NhaCungCap> listItem = nhaCungCapService.getList();
        DefaultTableModel tableModel = new ClassTableModelNhaCungCap().setTableNCC(listItem, listColumn);
        
        JTable tableNhaCungCap = new JTable(tableModel);
        
        rowSorter = new TableRowSorter<>(tableNhaCungCap.getModel());
        
        tableNhaCungCap.setRowSorter(rowSorter);
        
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
        
        tableNhaCungCap.getColumnModel().getColumn(0).setMinWidth(40);
        tableNhaCungCap.getColumnModel().getColumn(0).setMaxWidth(40);
        tableNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(40);
        
        tableNhaCungCap.getColumnModel().getColumn(1).setMinWidth(80);
        tableNhaCungCap.getColumnModel().getColumn(1).setMaxWidth(80);
        tableNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        tableNhaCungCap.getColumnModel().getColumn(5).setMinWidth(80);
        tableNhaCungCap.getColumnModel().getColumn(5).setMaxWidth(80);
        tableNhaCungCap.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        tableNhaCungCap.getColumnModel().getColumn(6).setMinWidth(80);
        tableNhaCungCap.getColumnModel().getColumn(6).setMaxWidth(80);
        tableNhaCungCap.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        tableNhaCungCap.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableNhaCungCap.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableNhaCungCap.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableNhaCungCap.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));
        
        tableNhaCungCap.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 1 && tableNhaCungCap.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) tableNhaCungCap.getModel();
                    int selectedRowIndex = tableNhaCungCap.getSelectedRow();
                    selectedRowIndex = tableNhaCungCap.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    NhaCungCap nhaCungCap = new NhaCungCap();
                    nhaCungCap.setMaNhaCc(model.getValueAt(selectedRowIndex, 1).toString());
                    nhaCungCap = new ManageNhaCungCap().getNhaCungCap(model.getValueAt(selectedRowIndex, 1).toString());
                    
                    if(tableNhaCungCap.getSelectedColumn()==5){
                        NhaCungCapJFrameUpdate nhaCungCapJFrame = new NhaCungCapJFrameUpdate(nhaCungCap);
                        nhaCungCapJFrame.setTitle("Thông tin nhà cung cấp");
                        nhaCungCapJFrame.setResizable(false);
                        nhaCungCapJFrame.setLocationRelativeTo(null);
                        nhaCungCapJFrame.setVisible(true);
                    }
                    
                    if(tableNhaCungCap.getSelectedColumn()==6){
                        if(nhaCungCapService.deleteStatus(nhaCungCap)){
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công");
                        } else{
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa nhà cung cấp này");
                        }
                    }
                }
            }
        });
        
        tableNhaCungCap.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableNhaCungCap.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableNhaCungCap.setRowHeight(50);
        tableNhaCungCap.validate();
        tableNhaCungCap.repaint();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableNhaCungCap);
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
                NhaCungCapJFrame nhaCungCapJFrame = new NhaCungCapJFrame(new NhaCungCap());
                nhaCungCapJFrame.setTitle("Thông tin vật tư");
                nhaCungCapJFrame.setLocationRelativeTo(null);
                nhaCungCapJFrame.setResizable(false);
                nhaCungCapJFrame.setVisible(true);
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
