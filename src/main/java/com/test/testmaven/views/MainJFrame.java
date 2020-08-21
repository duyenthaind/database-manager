/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.views;

import com.test.testmaven.bean.MenuListBean;
import com.test.testmaven.controller.ChangeWindowController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MainJFrame extends javax.swing.JFrame {
    public MainJFrame() {
        initComponents();
        setTitle("QlBanHang");
        
        ChangeWindowController controller = new ChangeWindowController(jpnView);
        controller.setView(jpnMainMenu, jlbMainMenu);
        
        List<MenuListBean> listItem = new ArrayList<>();
        listItem.add(new MenuListBean("MainMenu", jpnMainMenu, jlbMainMenu));
        listItem.add(new MenuListBean("DonDh", jpnDonDh, jlbDonDh));
        listItem.add(new MenuListBean("VatTu", jpnVatTu, jlbVatTu));
        listItem.add(new MenuListBean("NhapXuat", jpnNhapXuat, jlbNhapXuat));
        listItem.add(new MenuListBean("Kho", jpnKho, jlbKho));
        listItem.add(new MenuListBean("NhaCungCap", jpnNhaCungCap, jlbNhaCungCap));
        controller.setEven(listItem);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpnRoot = new javax.swing.JPanel();
        jpnMenu = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jpnMainMenu = new javax.swing.JPanel();
        jlbMainMenu = new javax.swing.JLabel();
        jpnVatTu = new javax.swing.JPanel();
        jlbVatTu = new javax.swing.JLabel();
        jpnDonDh = new javax.swing.JPanel();
        jlbDonDh = new javax.swing.JLabel();
        jpnNhapXuat = new javax.swing.JPanel();
        jlbNhapXuat = new javax.swing.JLabel();
        jpnKho = new javax.swing.JPanel();
        jlbKho = new javax.swing.JLabel();
        jpnNhaCungCap = new javax.swing.JPanel();
        jlbNhaCungCap = new javax.swing.JLabel();
        jpnView = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpnMenu.setBackground(new java.awt.Color(204, 255, 204));

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_euro_black_18dp.png"))); // NOI18N
        jLabel1.setText("QlBanHang");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addGap(113, 113, 113))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );

        jpnMainMenu.setBackground(new java.awt.Color(153, 153, 255));
        jpnMainMenu.setForeground(new java.awt.Color(0, 0, 0));
        jpnMainMenu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jlbMainMenu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jlbMainMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_home_black_18dp.png"))); // NOI18N
        jlbMainMenu.setText("Màn hình chính");

        javax.swing.GroupLayout jpnMainMenuLayout = new javax.swing.GroupLayout(jpnMainMenu);
        jpnMainMenu.setLayout(jpnMainMenuLayout);
        jpnMainMenuLayout.setHorizontalGroup(
            jpnMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnMainMenuLayout.setVerticalGroup(
            jpnMainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMainMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnVatTu.setBackground(new java.awt.Color(153, 153, 255));
        jpnVatTu.setForeground(new java.awt.Color(0, 0, 0));
        jpnVatTu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jlbVatTu.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jlbVatTu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_storage_black_18dp.png"))); // NOI18N
        jlbVatTu.setText("Quản lý vật tư");

        javax.swing.GroupLayout jpnVatTuLayout = new javax.swing.GroupLayout(jpnVatTu);
        jpnVatTu.setLayout(jpnVatTuLayout);
        jpnVatTuLayout.setHorizontalGroup(
            jpnVatTuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnVatTuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnVatTuLayout.setVerticalGroup(
            jpnVatTuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnVatTuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnDonDh.setBackground(new java.awt.Color(153, 153, 255));
        jpnDonDh.setForeground(new java.awt.Color(0, 0, 0));
        jpnDonDh.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jlbDonDh.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jlbDonDh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_article_black_18dp.png"))); // NOI18N
        jlbDonDh.setText("Quản lý đơn hàng");

        javax.swing.GroupLayout jpnDonDhLayout = new javax.swing.GroupLayout(jpnDonDh);
        jpnDonDh.setLayout(jpnDonDhLayout);
        jpnDonDhLayout.setHorizontalGroup(
            jpnDonDhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDonDhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDonDh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnDonDhLayout.setVerticalGroup(
            jpnDonDhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnDonDhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDonDh, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnNhapXuat.setBackground(new java.awt.Color(153, 153, 255));
        jpnNhapXuat.setForeground(new java.awt.Color(0, 0, 0));
        jpnNhapXuat.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jlbNhapXuat.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jlbNhapXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_sync_black_18dp.png"))); // NOI18N
        jlbNhapXuat.setText("Quản lý nhập xuất");

        javax.swing.GroupLayout jpnNhapXuatLayout = new javax.swing.GroupLayout(jpnNhapXuat);
        jpnNhapXuat.setLayout(jpnNhapXuatLayout);
        jpnNhapXuatLayout.setHorizontalGroup(
            jpnNhapXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNhapXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbNhapXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnNhapXuatLayout.setVerticalGroup(
            jpnNhapXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNhapXuatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbNhapXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnKho.setBackground(new java.awt.Color(153, 153, 255));
        jpnKho.setForeground(new java.awt.Color(0, 0, 0));
        jpnKho.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jlbKho.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jlbKho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_add_business_black_18dp.png"))); // NOI18N
        jlbKho.setText("Quản lý kho");

        javax.swing.GroupLayout jpnKhoLayout = new javax.swing.GroupLayout(jpnKho);
        jpnKho.setLayout(jpnKhoLayout);
        jpnKhoLayout.setHorizontalGroup(
            jpnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnKhoLayout.setVerticalGroup(
            jpnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnKhoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbKho, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        jpnNhaCungCap.setBackground(new java.awt.Color(153, 153, 255));
        jpnNhaCungCap.setForeground(new java.awt.Color(0, 0, 0));
        jpnNhaCungCap.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N

        jlbNhaCungCap.setFont(new java.awt.Font("Calibri", 1, 16)); // NOI18N
        jlbNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/baseline_emoji_people_black_18dp.png"))); // NOI18N
        jlbNhaCungCap.setText("Các nhà cung cấp");

        javax.swing.GroupLayout jpnNhaCungCapLayout = new javax.swing.GroupLayout(jpnNhaCungCap);
        jpnNhaCungCap.setLayout(jpnNhaCungCapLayout);
        jpnNhaCungCapLayout.setHorizontalGroup(
            jpnNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jpnNhaCungCapLayout.setVerticalGroup(
            jpnNhaCungCapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNhaCungCapLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jpnMenuLayout = new javax.swing.GroupLayout(jpnMenu);
        jpnMenu.setLayout(jpnMenuLayout);
        jpnMenuLayout.setHorizontalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jpnVatTu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnDonDh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnNhapXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnKho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnMenuLayout.setVerticalGroup(
            jpnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnMenuLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnMainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnVatTu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnDonDh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnNhapXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnNhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpnView.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jpnViewLayout = new javax.swing.GroupLayout(jpnView);
        jpnView.setLayout(jpnViewLayout);
        jpnViewLayout.setHorizontalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
        );
        jpnViewLayout.setVerticalGroup(
            jpnViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpnRootLayout = new javax.swing.GroupLayout(jpnRoot);
        jpnRoot.setLayout(jpnRootLayout);
        jpnRootLayout.setHorizontalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnRootLayout.createSequentialGroup()
                .addComponent(jpnMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpnRootLayout.setVerticalGroup(
            jpnRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpnView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpnRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel jlbDonDh;
    private javax.swing.JLabel jlbKho;
    private javax.swing.JLabel jlbMainMenu;
    private javax.swing.JLabel jlbNhaCungCap;
    private javax.swing.JLabel jlbNhapXuat;
    private javax.swing.JLabel jlbVatTu;
    private javax.swing.JPanel jpnDonDh;
    private javax.swing.JPanel jpnKho;
    private javax.swing.JPanel jpnMainMenu;
    private javax.swing.JPanel jpnMenu;
    private javax.swing.JPanel jpnNhaCungCap;
    private javax.swing.JPanel jpnNhapXuat;
    private javax.swing.JPanel jpnRoot;
    private javax.swing.JPanel jpnVatTu;
    private javax.swing.JPanel jpnView;
    // End of variables declaration//GEN-END:variables
}
