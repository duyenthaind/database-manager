/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import TableDAO.VatTuServiceimpl;
import com.test.hibernate.entities.VatTu;
import com.test.testmaven.manage.ManageVatTu;
import com.test.testmaven.utility.ClassTableModelVatTu;
import com.test.testmaven.views.VatTuJFrame;
import com.test.testmaven.views.VatTuJFrameUpdate;
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
public class VatTuController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private VatTuServiceimpl vatTuService = null;

    private String[] listColumn = {"STT", "MaVTu", "TenVTu", "DVTinh", "PhanTram", "", ""};
    private TableRowSorter<TableModel> rowSorter = null;

    public VatTuController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.vatTuService = new VatTuServiceimpl();
    }

    public void setDateToTable() {
        List<VatTu> listItem = vatTuService.getList();
        DefaultTableModel tableModel = new ClassTableModelVatTu().setTableVatTu(listItem, listColumn);

        JTable tableVatTu = new JTable(tableModel);

        rowSorter = new TableRowSorter<>(tableVatTu.getModel());

        tableVatTu.setRowSorter(rowSorter);

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
        tableVatTu.getColumnModel().getColumn(0).setMinWidth(40);
        tableVatTu.getColumnModel().getColumn(0).setMaxWidth(40);
        tableVatTu.getColumnModel().getColumn(0).setPreferredWidth(40);

        tableVatTu.getColumnModel().getColumn(1).setMinWidth(80);
        tableVatTu.getColumnModel().getColumn(1).setMaxWidth(80);
        tableVatTu.getColumnModel().getColumn(1).setPreferredWidth(80);

        tableVatTu.getColumnModel().getColumn(5).setMinWidth(80);
        tableVatTu.getColumnModel().getColumn(5).setMaxWidth(80);
        tableVatTu.getColumnModel().getColumn(5).setPreferredWidth(80);

        tableVatTu.getColumnModel().getColumn(6).setMinWidth(80);
        tableVatTu.getColumnModel().getColumn(6).setMaxWidth(80);
        tableVatTu.getColumnModel().getColumn(6).setPreferredWidth(80);
        
        tableVatTu.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableVatTu.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableVatTu.getColumnModel().getColumn(6).setCellRenderer(new ButtonRenderer());
        tableVatTu.getColumnModel().getColumn(6).setCellEditor(new ButtonEditor(new JCheckBox()));

        tableVatTu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1 && tableVatTu.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) tableVatTu.getModel();
                    int selectedRowIndex = tableVatTu.getSelectedRow();
                    selectedRowIndex = tableVatTu.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);

                    if (tableVatTu.getSelectedColumn() == 5) {
                        VatTu vatTu = new VatTu();
                        vatTu.setMaVTu(model.getValueAt(selectedRowIndex, 1).toString());
                        vatTu = new ManageVatTu().getVatTu(model.getValueAt(selectedRowIndex, 1).toString());
                        VatTuJFrameUpdate vatTuJFrame = new VatTuJFrameUpdate(vatTu);
                        vatTuJFrame.setTitle("Thông tin vật tư");
                        vatTuJFrame.setResizable(false);
                        vatTuJFrame.setLocationRelativeTo(null);
                        vatTuJFrame.setVisible(true);
                    }

                    if (tableVatTu.getSelectedColumn() == 6) {
                        VatTu vatTu = new VatTu();
                        vatTu.setMaVTu(model.getValueAt(selectedRowIndex, 1).toString());
                        vatTu = new ManageVatTu().getVatTu(model.getValueAt(selectedRowIndex, 1).toString());
                        if (vatTuService.deleteStatus(vatTu) == 1) {
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa vật tư này");
                        }
                    }

                }
            }
        });

        tableVatTu.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableVatTu.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableVatTu.setRowHeight(50);
        tableVatTu.validate();
        tableVatTu.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableVatTu);
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
                VatTuJFrame vattuJFrame = new VatTuJFrame(new VatTu());
                vattuJFrame.setTitle("Thông tin vật tư");
                vattuJFrame.setLocationRelativeTo(null);
                vattuJFrame.setResizable(false);
                vattuJFrame.setVisible(true);
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
