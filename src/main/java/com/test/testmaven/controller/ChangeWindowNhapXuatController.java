/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.controller;

import com.test.testmaven.bean.MenuListBean;
import com.test.testmaven.views.ChiTietPhieuNhapJPanel;
import com.test.testmaven.views.ChiTietPhieuXuatJPanel;
import com.test.testmaven.views.MenuNhapXuatJPanel;
import com.test.testmaven.views.PhieuNhapJPanel;
import com.test.testmaven.views.PhieuXuatJPanel;
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
public class ChangeWindowNhapXuatController {
    private JPanel root;
    private String kindSelected = "";
    List<MenuListBean> listItem = null;

    public ChangeWindowNhapXuatController(JPanel root) {
        this.root = root;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem){
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new MenuNhapXuatJPanel());
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
                case "MainMenuNhapXuat":
                    node = new MenuNhapXuatJPanel();
                    break;  
                case "PhieuNhap":
                    node  = new PhieuNhapJPanel();
                    break;
                case "ChiTietPhieuNhap":
                    node = new ChiTietPhieuNhapJPanel();
                    break;
                case "PhieuXuat":
                    node = new PhieuXuatJPanel();
                    break;
                case "ChiTietPhieuXuat":
                    node = new ChiTietPhieuXuatJPanel();
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
