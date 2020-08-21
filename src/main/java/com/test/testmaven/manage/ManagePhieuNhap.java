package com.test.testmaven.manage;

import com.test.hibernate.entities.PhieuNhap;
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
public class ManagePhieuNhap {
    
    /*Method to add a PhieuNhap*/
    public boolean addPhieuNhap(String soPnIn, String soDhIn, Date ngayNhapIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            PhieuNhap phieunhap;
            phieunhap = new PhieuNhap(soPnIn, ngayNhapIn, soDhIn);
            session.save(phieunhap);
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
    
    /*Method to read all PhieuNhap*/
    public void listPhieuNhap(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from PhieuNhap";
            Query query = session.createQuery(hql);
            List phieuNhaps = query.list();
            System.out.printf("%-30s%-12s%s", "SoPn","NgayNhap","SoDh");
		System.out.println();
            for(Iterator iterator = phieuNhaps.iterator(); iterator.hasNext();){
                PhieuNhap phieunhap = (PhieuNhap)iterator.next();
                System.out.printf("%-30s%-12s%s", phieunhap.getSoPn(),phieunhap.getNgayNhap(), phieunhap.getSoDh());
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
    
    /*Method to update a PhieuNhap*/
    public boolean updatePhieuNhap(String soPnIn, Date ngayNhapIn, String soDhIn){
        boolean result  = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            PhieuNhap phieuxuat =(PhieuNhap) session.get(PhieuNhap.class, soPnIn);
            phieuxuat.setSoPn(soPnIn);
            phieuxuat.setSoDh(soDhIn);
            phieuxuat.setNgayNhap(ngayNhapIn);
            tx.commit();
            session.update(phieuxuat);
            result = true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return result;
    }
   
    /*Method to delete a PhieuNhap*/
    public boolean deletePhieuNhap(String soPnIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            PhieuNhap phieuxuat = (PhieuNhap) session.get(PhieuNhap.class, soPnIn);
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
    public List<PhieuNhap> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<PhieuNhap> listResults = null;
        try{
            String hql = "from PhieuNhap";
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
    public boolean isExists(PhieuNhap phieuNhap){
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = true;
        String hql = "from PhieuNhap where soPn = :soPn";
        Query query = session.createQuery(hql);
        query.setParameter("soPn", phieuNhap.getSoPn());
        List<PhieuNhap> listResult = query.list();
        if(listResult.isEmpty()){
            return false;
        }
        session.close();
        return result;
    }

    /*Method to get a PhieuNhap*/
    public PhieuNhap getPhieuNhap(String soPnIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        PhieuNhap phieuNhap = null;
        try{
            String hql = "from PhieuNhap where soPn = :soPn";
            Query query = session.createQuery(hql);
            query.setParameter("soPn", soPnIn);
            List<PhieuNhap> listResult = query.list();
            if(listResult.isEmpty()){
                phieuNhap = null;
            } else{
                phieuNhap = listResult.get(0);
            }
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return phieuNhap;
    }
    
    /*Method to get List SoPn*/
    public List<String> getListSoPn(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> listResults = null;
        String hql = "select distinct soPn from PhieuNhap";
        listResults = session.createQuery(hql).list();
        return listResults;
    }
}
