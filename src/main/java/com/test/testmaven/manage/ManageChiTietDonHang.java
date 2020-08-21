package com.test.testmaven.manage;

import com.test.hibernate.entities.ChiTietDonHang;
import com.test.testmaven.hibernate.HibernateUtil;
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
public class ManageChiTietDonHang {
    
    /*Method to create a ChiTietDonHang */
    public boolean addChiTietDonHang(String soDhIn, String maVTuIn, int slDatIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ChiTietDonHang chitietdonhang = new ChiTietDonHang(maVTuIn, slDatIn, soDhIn);
            session.save(chitietdonhang);
            tx.commit();
            result = true;
        }catch(HibernateException ex){
            if(tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
    /*Method to read all ChiTietDonHang*/
    public void listChiTietDonHang(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietDonHang";
            Query query = session.createQuery(hql);
            List chiTietDonHangs = query.list();
            System.out.printf("%-12s%-12s%s", "SoDh", "MaVTu", "SlDat");
		System.out.println();
            for(Iterator iterator = chiTietDonHangs.iterator(); iterator.hasNext();){
                ChiTietDonHang chitietdonhang = (ChiTietDonHang)iterator.next();
                System.out.printf("%-12s%-12s%s",chitietdonhang.getSoDh(), chitietdonhang.getMaVTu(), chitietdonhang.getSlDat());
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
    
    /*Method to update a ChiTietDonHang*/
    public boolean updateChiTietDonHang(String soDhIn, String maVTuIn, int slDatIn){
        boolean resultBool = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ChiTietDonHang chiTietDonHang = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietDonHang where maVTu = :maVatTu and soDh = :soDh";
            Query query = session.createQuery(hql);
            query.setParameter("maVatTu", maVTuIn);
            query.setParameter("soDh", soDhIn);
            List<ChiTietDonHang> result = query.list();
            if(result.isEmpty()){
                chiTietDonHang = null;
            } else{
                chiTietDonHang = result.get(0);
            }
            chiTietDonHang.setSoDh(soDhIn);
            chiTietDonHang.setMaVTu(maVTuIn);
            chiTietDonHang.setSlDat(slDatIn);
            session.update(chiTietDonHang);
            tx.commit();
            resultBool = true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return resultBool;
    }
   
    /*Method to delete a ChiTietDonHang*/
    public boolean deleteChiTietPhieuNhap(String soDhIn, String maVTuIn){
        boolean finalResult = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ChiTietDonHang chiTietDonHang = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietDonHang where maVTu = :maVatTu and soDh = :soDh";
            Query query = session.createQuery(hql);
            query.setParameter("maVatTu", maVTuIn);
            query.setParameter("soDh", soDhIn);
            List<ChiTietDonHang> result = query.list();
            if(result.isEmpty()){
                chiTietDonHang = null;
            } else{
                chiTietDonHang = result.get(0);
            }
            session.delete(chiTietDonHang);
            tx.commit();
            finalResult = true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return finalResult;
    }
    
    /*Method to check if ChiTietDonHang were available or not*/
    public boolean isExists(ChiTietDonHang chiTietDonHang){
        boolean result = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from ChiTietDonHang where maVTu = :maVatTu and soDh = :soDh";
        Query query = session.createQuery(hql);
        query.setParameter("maVatTu", chiTietDonHang.getMaVTu());
        query.setParameter("soDh", chiTietDonHang.getSoDh());
        List<ChiTietDonHang> listResult = query.list();
        if(listResult.isEmpty()){
            result = false;
        }
        else{
            result = true;
        }
        session.close();
        return result;
    }
    
    /*Method to get list ChiTietDonHang*/
    public List<ChiTietDonHang> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ChiTietDonHang> listResults = null;
        try{
            String hql = "from ChiTietDonHang";
            Query query = session.createQuery(hql);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
    
    /*Method to get a ChiTietDonHang*/
    public ChiTietDonHang getChiTietDonHang(String soDhIn, String maVTuIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        ChiTietDonHang chiTietDonHang = null;
            try{
                String hql = "from ChiTietDonHang where maVTu = :maVatTu and soDh = :soDh";
                Query query = session.createQuery(hql);
                query.setParameter("maVatTu", maVTuIn);
                query.setParameter("soDh", soDhIn);
                List<ChiTietDonHang> result = query.list();
                if(result.isEmpty()){
                    chiTietDonHang = null;
                } else{
                    chiTietDonHang = result.get(0);
                }
                
            } catch(HibernateException ex){
                ex.printStackTrace();
            } finally{
                session.close();
            }
            return chiTietDonHang;
    }
    
    /*Sublit in DonDh*/
    public List<ChiTietDonHang> getListWithConstraint(String soDhIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ChiTietDonHang> listResults = null;
        try{
            String hql = "from ChiTietDonHang where soDh = :soDhIn";
            Query query = session.createQuery(hql);
            query.setParameter("soDhIn", soDhIn);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
}
