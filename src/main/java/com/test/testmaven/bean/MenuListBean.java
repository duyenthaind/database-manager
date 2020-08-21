package com.test.testmaven.bean;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class MenuListBean {
    private String kind;
    private JPanel jpanel;
    private JLabel jlabel;

    public MenuListBean() {
    }

    public MenuListBean(String kind, JPanel jpanel, JLabel jlabel) {
        this.kind = kind;
        this.jpanel = jpanel;
        this.jlabel = jlabel;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpanel() {
        return jpanel;
    }

    public void setJpanel(JPanel jpanel) {
        this.jpanel = jpanel;
    }

    public JLabel getJlabel() {
        return jlabel;
    }

    public void setJlabel(JLabel jlabel) {
        this.jlabel = jlabel;
    }
    
    
}
