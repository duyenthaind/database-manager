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
public class JobImpl implements Job {

    private int type;

    public JobImpl(int type) {
        this.type = type;
    }

    public int getJob(){
        return this.type;
    }
    
    @Override
    public void setJob(int type) {
        switch (type) {
            case 1:
                this.type = SAVE;
                break;
            case 2:
                this.type = UPDATE;
                break;
            case -1:
                this.type = DELETE;
                break;
            default:
                this.type = 0;
        }
    }
}
