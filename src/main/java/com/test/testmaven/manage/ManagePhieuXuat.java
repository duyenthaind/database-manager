package com.test.testmaven.manage;

import com.test.hibernate.entities.PhieuXuat;
import com.test.testmaven.hibernate.HibernateUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ManagePhieuXuat {
    
    /*Method to add a PhieuXuat*/
    public boolean addPhieuXuat(String soPxIn, Date ngayXuatIn, String tenKhIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            PhieuXuat phieuxuat;
            phieuxuat = new PhieuXuat(soPxIn, ngayXuatIn, tenKhIn);
            session.save(phieuxuat);
            tx.commit();
            result = true;
        }catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return result;
    }
    
    /*Method to read all PhieuXuat*/
    public void listPhieuXuat(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from PhieuXuat";
            Query query = session.createQuery(hql);
            List phieuXuats = query.list();
            System.out.printf("%-12s%-30s%s", "SoPx","TenKh","NgayXuat");
		System.out.println();
            for(Iterator iterator = phieuXuats.iterator(); iterator.hasNext();){
            PhieuXuat phieuxuat = (PhieuXuat)iterator.next();
                System.out.printf("%-12s%-30s%s", phieuxuat.getSoPx(), phieuxuat.getTenKh(), phieuxuat.getNgayXuat());
                System.out.println();
            }
            tx.commit();
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
    }
    
    /*Method to update a PhieuXuat*/
    public boolean updatePhieuXuat(String soPxIn, String tenKhIn, Date ngayXuatIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            PhieuXuat phieuxuat =(PhieuXuat) session.get(PhieuXuat.class, soPxIn);
            phieuxuat.setSoPx(soPxIn);
            phieuxuat.setTenKh(tenKhIn);
            phieuxuat.setNgayXuat(ngayXuatIn);
            session.update(phieuxuat);
            tx.commit();
            result = true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return result;
    }
   
    /*Method to delete a PhieuXuat*/
    public boolean deletePhieuXuat(String soPxIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            PhieuXuat phieuxuat = (PhieuXuat) session.get(PhieuXuat.class, soPxIn);
            session.delete(phieuxuat);
            tx.commit();
            result = true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return result;
    }
    
    /*Method to get List PhieuNhap*/
    public List<PhieuXuat> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PhieuXuat> listResults = null;
        try{
            String hql = "from PhieuXuat";
            Query query = session.createQuery(hql);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
    
    /*Method to check if a TonKho were available or not*/
    public boolean isExists(PhieuXuat phieuXuat){
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = true;
        String hql = "from PhieuXuat where soPx = :soPx";
        Query query = session.createQuery(hql);
        query.setParameter("soPx", phieuXuat.getSoPx());
        List<PhieuXuat> listResult = query.list();
        if(listResult.isEmpty()){
            return false;
        }
        session.close();
        return result;
    }

    /*Method to get a TonKho*/
    public PhieuXuat getPhieuXuat(String soPxIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        PhieuXuat phieuXuat = null;
        try{
            String hql = "from PhieuXuat where soPx = :soPx";
        Query query = session.createQuery(hql);
        query.setParameter("soPx", soPxIn);
        List<PhieuXuat> listResult = query.list();
            if(listResult.isEmpty()){
                phieuXuat = null;
            } else{
                phieuXuat = listResult.get(0);
            }
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return phieuXuat;
    }
    
    /*Method to get List SoPx*/
    public List<String> getListSoPx(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> listResults = null;
        String hql = "select distinct soPx from PhieuXuat";
        listResults = session.createQuery(hql).list();
        return listResults;
    }
}
