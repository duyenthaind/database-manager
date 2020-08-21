package com.test.testmaven.controller;

import TableDAO.ChiTietDonHangServiceimpl;
import com.test.hibernate.entities.ChiTietDonHang;
import com.test.testmaven.manage.ManageChiTietDonHang;
import com.test.testmaven.utility.ClassTableModelChiTietDonHang;
import com.test.testmaven.views.ChiTietDonHangJFrame;
import com.test.testmaven.views.ChiTietDonHangJFrameUpdate;
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
public class ChiTietDonHangController {

    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnRefresh;
    private ChiTietDonHangServiceimpl chiTietDonHangService = null;

    private String[] listColumn = {"STT", "SoDh", "MaVTu", "SlDat", "", ""};
    private TableRowSorter<TableModel> rowSorter = null;

    public ChiTietDonHangController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnRefresh) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnRefresh = btnRefresh;
        this.chiTietDonHangService = new ChiTietDonHangServiceimpl();
    }

    public void setDateToTable() {
        List<ChiTietDonHang> listItem = chiTietDonHangService.getList();
        DefaultTableModel tableModel = new ClassTableModelChiTietDonHang().setTableChiTietDonHang(listItem, listColumn);

        JTable tableChiTietDonHang = new JTable(tableModel);

        rowSorter = new TableRowSorter<>(tableChiTietDonHang.getModel());

        tableChiTietDonHang.setRowSorter(rowSorter);

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
        tableChiTietDonHang.getColumnModel().getColumn(0).setMinWidth(40);
        tableChiTietDonHang.getColumnModel().getColumn(0).setMaxWidth(40);
        tableChiTietDonHang.getColumnModel().getColumn(0).setPreferredWidth(40);

        tableChiTietDonHang.getColumnModel().getColumn(1).setMinWidth(80);
        tableChiTietDonHang.getColumnModel().getColumn(1).setMaxWidth(80);
        tableChiTietDonHang.getColumnModel().getColumn(1).setPreferredWidth(80);

        tableChiTietDonHang.getColumnModel().getColumn(4).setMinWidth(80);
        tableChiTietDonHang.getColumnModel().getColumn(4).setMaxWidth(80);
        tableChiTietDonHang.getColumnModel().getColumn(4).setPreferredWidth(80);

        tableChiTietDonHang.getColumnModel().getColumn(5).setMinWidth(80);
        tableChiTietDonHang.getColumnModel().getColumn(5).setMaxWidth(80);
        tableChiTietDonHang.getColumnModel().getColumn(5).setPreferredWidth(80);

        tableChiTietDonHang.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tableChiTietDonHang.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        tableChiTietDonHang.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());
        tableChiTietDonHang.getColumnModel().getColumn(5).setCellEditor(new ButtonEditor(new JCheckBox()));

        tableChiTietDonHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 1 && tableChiTietDonHang.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) tableChiTietDonHang.getModel();
                    int selectedRowIndex = tableChiTietDonHang.getSelectedRow();
                    selectedRowIndex = tableChiTietDonHang.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);

                    ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
                    chiTietDonHang.setMaVTu(model.getValueAt(selectedRowIndex, 2).toString());
                    chiTietDonHang.setSoDh(model.getValueAt(selectedRowIndex, 1).toString());
                    chiTietDonHang = new ManageChiTietDonHang().getChiTietDonHang(chiTietDonHang.getSoDh(), chiTietDonHang.getMaVTu());

                    if (tableChiTietDonHang.getSelectedColumn() == 4) {
                        ChiTietDonHangJFrameUpdate chiTietDonHangJFrame = new ChiTietDonHangJFrameUpdate(chiTietDonHang);
                        chiTietDonHangJFrame.setTitle("Thông tin chi tiết đơn hàng");
                        chiTietDonHangJFrame.setResizable(false);
                        chiTietDonHangJFrame.setLocationRelativeTo(null);
                        chiTietDonHangJFrame.setVisible(true);
                    }
                    if (tableChiTietDonHang.getSelectedColumn() == 5) {
                        if (chiTietDonHangService.deleteStatus(chiTietDonHang)) {
                            JOptionPane.showMessageDialog(new JFrame(), "Xóa thành công!");
                        } else {
                            JOptionPane.showMessageDialog(new JFrame(), "Không thể xóa chi tiết đơn hàng này!");
                        }
                    }
                }
            }
        });

        tableChiTietDonHang.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
        tableChiTietDonHang.getTableHeader().setPreferredSize(new Dimension(100, 50));
        tableChiTietDonHang.setRowHeight(50);
        tableChiTietDonHang.validate();
        tableChiTietDonHang.repaint();

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tableChiTietDonHang);
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
                ChiTietDonHangJFrame chiTietDonHangJFrame = new ChiTietDonHangJFrame(new ChiTietDonHang());
                chiTietDonHangJFrame.setTitle("Thông tin chi tiết đơn hàng");
                chiTietDonHangJFrame.setLocationRelativeTo(null);
                chiTietDonHangJFrame.setResizable(false);
                chiTietDonHangJFrame.setVisible(true);
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