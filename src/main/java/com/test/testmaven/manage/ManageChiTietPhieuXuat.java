package com.test.testmaven.manage;

import com.test.hibernate.entities.ChiTietPhieuXuat;
import com.test.testmaven.hibernate.HibernateUtil;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class ManageChiTietPhieuXuat {
    
    /*Method to create a ChiTietPhieuXuat*/
    public boolean addChiTietPhieuXuat(String soPxIn, String maVTuIn, int slXuatIn, float dgXuatIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            ChiTietPhieuXuat chitietphieuxuat = new ChiTietPhieuXuat(soPxIn, maVTuIn, slXuatIn, dgXuatIn);
            session.save(chitietphieuxuat);
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
    
    /*Method to read all ChiTietPhieuXuat*/
    public void listChiTietPhieuXuat(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String sql = " select * from ctpxuat";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(ChiTietPhieuXuat.class);
            List chiTietPhieuXuats = query.list();
            System.out.printf("%-12s%-12s%-12s%s", "SoPx", "MaVTu", "SlXuat", "DgXuat");
		System.out.println();
            for(Iterator iterator = chiTietPhieuXuats.iterator(); iterator.hasNext();){
                ChiTietPhieuXuat chitietphieuxuat = (ChiTietPhieuXuat) iterator.next();
                System.out.printf("%-12s%-12s%-12s%s", chitietphieuxuat.getSoPx(), chitietphieuxuat.getMaVTu(), chitietphieuxuat.getSlXuat(), chitietphieuxuat.getDgXuat());
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
    
    /*Method to update a ChiTietPhieuXuat*/
    public boolean updateChiTietPhieuXuat(String soPxIn, String maVTuIn, int slXuatIn, float dgXuatIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ChiTietPhieuXuat chiTietPhieuXuat = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietPhieuXuat where soPx = :soPx and maVTu = :maVTu";
            Query query = session.createQuery(hql);
            query.setParameter("soPx", soPxIn);
            query.setParameter("maVTu", maVTuIn);
            List<ChiTietPhieuXuat> listResult = query.list();
            if(listResult.isEmpty()){
                chiTietPhieuXuat = null;
            } else{
                chiTietPhieuXuat = listResult.get(0);
            }
            chiTietPhieuXuat.setSoPx(soPxIn);
            chiTietPhieuXuat.setMaVTu(maVTuIn);
            chiTietPhieuXuat.setDgXuat(dgXuatIn);
            chiTietPhieuXuat.setSlXuat(slXuatIn);
            session.update(chiTietPhieuXuat);
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
   
    /*Method to delete a ChiTietPhieuXuat*/
    public boolean deleteChiTietPhieuXuat(String soPxIn, String maVTuIn){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ChiTietPhieuXuat chiTietPhieuXuat = null;
        try{
            tx = session.beginTransaction();
            String hql = "from ChiTietPhieuXuat where soPx = :soPx and maVTu = :maVTu";
            Query query = session.createQuery(hql);
            query.setParameter("soPx", soPxIn);
            query.setParameter("maVTu", maVTuIn);
            List<ChiTietPhieuXuat> listResult = query.list();
            if(listResult.isEmpty()){
                chiTietPhieuXuat = null;
            } else{
                chiTietPhieuXuat = listResult.get(0);
            }
            session.delete(chiTietPhieuXuat);
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
    
    /*Method to get List ChiTietPhieuXuat*/
    public List<ChiTietPhieuXuat> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ChiTietPhieuXuat> listResults = null;
        try{
            String sql = "from ChiTietPhieuXuat";
            Query query = session.createQuery(sql);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
    
    /*Method to check if a ChiTietPhieuXuat were available or not*/
    public boolean isExists(ChiTietPhieuXuat chiTietPhieuXuat){
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = true;
        String hql = "from ChiTietPhieuXuat where soPx = :soPx and maVTu = :maVTu";
        Query query = session.createQuery(hql);
        query.setParameter("soPx", chiTietPhieuXuat.getSoPx());
        query.setParameter("maVTu", chiTietPhieuXuat.getMaVTu());
        List<ChiTietPhieuXuat> listResult = query.list();
        if(listResult.isEmpty()){
            return false;
        }
        session.close();
        return result;
    }

    /*Method to get a ChiTietPhieuXuat*/
    public ChiTietPhieuXuat getChiTietPhieuXuat(String soPxIn, String maVTuIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        ChiTietPhieuXuat chiTietPhieuXuat = null;
        try{
            String hql = "from ChiTietPhieuXuat where soPx = :soPx and maVTu = :maVTu";
            Query query = session.createQuery(hql);
            query.setParameter("soPx", soPxIn);
            query.setParameter("maVTu", maVTuIn);
            List<ChiTietPhieuXuat> listResult = query.list();
            if(listResult.isEmpty()){
                chiTietPhieuXuat = null;
            } else{
                chiTietPhieuXuat = listResult.get(0);
            }
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return chiTietPhieuXuat;
    }
    
    /*Method to get a List ChiTietPhieuXuat with SoPx is a parameter*/
    public List<ChiTietPhieuXuat> getListWithConstraint(String soPxIn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ChiTietPhieuXuat> listResults = null;
        try{
            String hql = "from ChiTietPhieuXuat where soPx = :soPxIn";
            Query query = session.createQuery(hql);
            query.setParameter("soPxIn", soPxIn);
            listResults = query.list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
}
