/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.manage;

/**
 *
 * @author HL94NVT
 */
public interface Job {
    public static final int SAVE = 1;
    public static final int UPDATE = 2;
    public static final int DELETE = -1;
    
    public void setJob(int type);
}
