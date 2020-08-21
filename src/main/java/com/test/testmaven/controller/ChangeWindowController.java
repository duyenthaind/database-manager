
package com.test.testmaven.controller;

import com.test.testmaven.bean.MenuListBean;
import com.test.testmaven.views.MenuDonDhJPanel;
import com.test.testmaven.views.KhoJPanel;
import com.test.testmaven.views.MainMenuJPanel;
import com.test.testmaven.views.NhaCungCapJPanel;
import com.test.testmaven.views.MenuNhapXuatJPanel;
import com.test.testmaven.views.VatTuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class ChangeWindowController {
    private JPanel root;
    private String kindSelected = "";
    List<MenuListBean> listItem = null;

    public ChangeWindowController(JPanel jpnRoot) {
        this.root = jpnRoot;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "MainMenu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 100, 191));
        
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new MainMenuJPanel());
        root.validate();
        root.repaint();
    }
    
    public void setEven(List<MenuListBean> menuListBeans){
        this.listItem = menuListBeans;
        for(MenuListBean item : menuListBeans){
            item.getJlabel().addMouseListener(new LabelEvent(item.getKind(), item.getJpanel(), item.getJlabel()));
        }
    }
    
    class LabelEvent implements MouseListener{
        private JPanel node;
        
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) {
            switch(kind){
                case "MainMenu":
                    node = new MainMenuJPanel();
                    break;  
                case "DonDh":
                    node  = new MenuDonDhJPanel();
                    break;
                case "VatTu":
                    node = new VatTuJPanel();
                    break;
                case "NhapXuat":
                    node = new MenuNhapXuatJPanel();
                    break;
                case "Kho":
                    node = new KhoJPanel();
                    break;
                case "NhaCungCap":
                    node = new NhaCungCapJPanel();
                    break;
                default:
                    node = new MainMenuJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(76, 175, 80));
                jlbItem.setBackground(new Color(76, 175, 80));
            }
        }
        
    }
    
    private void setChangeBackground(String kind){
        for(MenuListBean item : listItem){
            if(item.getKind().equalsIgnoreCase(kind)){
                item.getJpanel().setBackground(new Color(96, 100, 191));
                item.getJlabel().setBackground(new Color(96, 100, 191));
            } else{
                item.getJpanel().setBackground(new Color(76, 175, 80));
                item.getJlabel().setBackground(new Color(76, 175, 80));
            }
        }
    }
}
