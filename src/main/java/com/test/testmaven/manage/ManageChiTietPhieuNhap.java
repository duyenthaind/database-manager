package com.test.testmaven.manage;

import com.test.hibernate.entities.ChiTietPhieuNhap;
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
public class ManageChiTietPhieuNhap {
    
     /*Method to create a ChiTietPhieuNhap*/
    public boolean addChiTietPhieuNhap(String soPnIn, String maVTuIn, int slNhapIn, float dgNhapIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ChiTietPhieuNhap chitietphieunhap = new ChiTietPhieuNhap(soPnIn, maVTuIn, slNhapIn, dgNhapIn);
            session.save(chitietphieunhap);
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
    
    /*Method to read all ChiTietPhieuNhap*/
    public void listChiTietPhieuNhap(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietPhieuNhap";
            Query query = session.createQuery(hql);
            List chiTietPhieuNhaps = query.list();
            System.out.printf("%-12s%-12s%-12s%s", "SoPn", "MaVTu", "SlNhap", "DgNhap");
		System.out.println();
            for(Iterator iterator = chiTietPhieuNhaps.iterator(); iterator.hasNext();){
                ChiTietPhieuNhap chitietphieunhap = (ChiTietPhieuNhap) iterator.next();
                System.out.printf("%-12s%-12s%-12s%s", chitietphieunhap.getSoPn(), chitietphieunhap.getMaVTu(), chitietphieunhap.getSlNhap(), chitietphieunhap.getDgNhap());
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
    
    /*Method to update a ChiTietPhieuNhap*/
    public boolean updateChiTietPhieuNhap(String soPnIn, String maVTuIn, int slNhapIn, float dgNhapIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ChiTietPhieuNhap chiTietPhieuNhap = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietPhieuNhap where soPn = :soPn and maVTu = :maVTu";
            Query query = session.createQuery(hql);
            query.setParameter("soPn", soPnIn);
            query.setParameter("maVTu", maVTuIn);
            List<ChiTietPhieuNhap> listResults = query.list();
            if(listResults.isEmpty()){
                chiTietPhieuNhap = null;
            } else{
                chiTietPhieuNhap = listResults.get(0);
            }
            chiTietPhieuNhap.setSoPn(soPnIn);
            chiTietPhieuNhap.setMaVTu(maVTuIn);
            chiTietPhieuNhap.setSlNhap(slNhapIn);
            chiTietPhieuNhap.setDgNhap(dgNhapIn);
            session.update(chiTietPhieuNhap);
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
   
    /*Method to delete a ChiTietPhieuNhap*/
    public boolean deleteChiTietPhieuNhap(String soPnIn, String maVTuIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ChiTietPhieuNhap chiTietPhieuNhap = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietPhieuNhap where soPn = :soPn and maVTu = :maVTu";
            Query query = session.createQuery(hql);
            query.setParameter("soPn", soPnIn);
            query.setParameter("maVTu", maVTuIn);
            List<ChiTietPhieuNhap> listResults = query.list();
            if(listResults.isEmpty()){
                chiTietPhieuNhap = null;
            } else{
                chiTietPhieuNhap = listResults.get(0);
            }
            session.delete(chiTietPhieuNhap);
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
    
    /*Method to get List ChiTietPhieuNhap*/
    public List<ChiTietPhieuNhap> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ChiTietPhieuNhap> listResults = null;
        try{
            String hql = "from ChiTietPhieuNhap";
            Query query = session.createQuery(hql);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
    
    /*Method to check if a ChiTietPhieuNhap were available or not*/
    public boolean isExists(ChiTietPhieuNhap chiTietPhieuNhap){
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = true;
        String hql = "from ChiTietPhieuNhap where soPn = :soPn and maVTu = :maVTu";
        Query query = session.createQuery(hql);
        query.setParameter("soPn", chiTietPhieuNhap.getSoPn());
        query.setParameter("maVTu", chiTietPhieuNhap.getMaVTu());
        List<ChiTietPhieuNhap> listResult = query.list();
        if(listResult.isEmpty()){
            return false;
        }
        session.close();
        return result;
    }

    /*Method to get a TonKho*/
    public ChiTietPhieuNhap getChiTietPhieuNhap(String soPnIn, String maVTuIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        ChiTietPhieuNhap chiTietPhieuNhap = null;
        try{
            String hql = "from ChiTietPhieuNhap where soPn = :soPn and maVTu = :maVTu";
            Query query = session.createQuery(hql);
            query.setParameter("soPn", soPnIn);
            query.setParameter("maVTu", maVTuIn);
            List<ChiTietPhieuNhap> listResult = query.list();
            if(listResult.isEmpty()){
                chiTietPhieuNhap = null;
            } else{
                chiTietPhieuNhap = listResult.get(0);
            }
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return chiTietPhieuNhap;
    }
    
    /*Method to get a list ChiTietPhieuNhap with soPn is a parameter*/
    public List<ChiTietPhieuNhap> getListWithConstraint(String soPnIn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ChiTietPhieuNhap> listResults = null;
        try{
            String hql = "from ChiTietPhieuNhap where soPn = :soPnIn";
            Query query = session.createQuery(hql);
            query.setParameter("soPnIn", soPnIn);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
}
