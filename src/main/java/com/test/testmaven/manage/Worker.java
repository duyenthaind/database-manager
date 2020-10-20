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
public interface Worker {
    public void pubJob(JobImpl job, Object obj);
    
    public int status();
}
