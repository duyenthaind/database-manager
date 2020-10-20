/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven.manage;

import org.hibernate.Session;

/**
 *
 * @author HL94NVT
 */
public class WorkerImpl implements Worker {

    private final int ERROR = -1;
    private final int SAVED = 1;
    private final int UPDATED = 2;
    private final int DELETED = 3;
    private final Session session;
    private int status = 0;
    CheckInstance check = new CheckInstance();

    public WorkerImpl(Session session) {
        this.session = session;
    }

    private void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void pubJob(JobImpl job, Object obj) {
        int service = job.getJob();
        check.setObj(obj);
        int type = check.returnType();
        int stt = 0;
        try {
            PerformService performService = new PerformService(session);
            performService.setObj(obj);
            performService.setService(service);
            performService.setType(type);
            int sttt = performService.process();
            if (sttt == 1) {
                stt = SAVED;
            } else if (status == 2) {
                stt = UPDATED;
            } else if (status == 3) {
                stt = DELETED;
            } else {
                stt = ERROR;
            }
        } catch (Exception ex) {
            stt = ERROR;
        } finally {
            setStatus(stt);
        }
    }

    @Override
    public int status() {
        return status;
    }

}
