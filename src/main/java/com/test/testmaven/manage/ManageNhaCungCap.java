package com.test.testmaven.manage;

import com.test.hibernate.entities.NhaCungCap;
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
public class ManageNhaCungCap {
    
    /*Method to create a NhaCungCap*/
    public boolean addNhaCungCap(String maNhaCcIn, String tenNhaCcIn, String diaChiIn, String dienThoaiIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            NhaCungCap nhacungcap = new NhaCungCap(maNhaCcIn,tenNhaCcIn,diaChiIn, dienThoaiIn);
            session.save(nhacungcap);
            tx.commit();
            return true;
        }catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return false;
    }
    
    /*Method to read all NhaCungCap*/
    public void listNhaCungCap(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            String hql = "from NhaCungCap";
            Query query = session.createQuery(hql);
            List nhaCungCaps = query.list();
            System.out.printf("%-50s%-50s%-12s%s", "MaNhaCC","TenNhaCc","DiaChi","DienThoai");
		System.out.println();
            for(Iterator iterator = nhaCungCaps.iterator(); iterator.hasNext();){
            NhaCungCap nhacungcap = (NhaCungCap)iterator.next();
                System.out.printf("%-50s%-50s%-12s%s", nhacungcap.getMaNhaCc(), nhacungcap.getTenNhaCc(), nhacungcap.getDiaChi(), nhacungcap.getDienThoai());
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
    
    /*Method to update a NhaCungCap*/
    public boolean updateNhaCungCap(String maNhaCcIn, String tenNhaCcIn, String diaChiIn, String dienThoaiIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            NhaCungCap nhacungcap = (NhaCungCap) session.get(NhaCungCap.class, maNhaCcIn);
            nhacungcap.setMaNhaCc(maNhaCcIn);
            nhacungcap.setTenNhaCc(tenNhaCcIn);
            nhacungcap.setDiaChi(diaChiIn); 
            nhacungcap.setDienThoai(dienThoaiIn);
            tx.commit();
            return true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return false;
    }
   
    /*Method to delete a NhaCungCap*/
    public boolean deleteNhaCungCap(String maNhaCCIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            NhaCungCap nhacungcap = (NhaCungCap) session.get(NhaCungCap.class, maNhaCCIn);
            session.delete(nhacungcap);
            tx.commit();
            return true;
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return false;
    }
    
    public List<NhaCungCap> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<NhaCungCap> listResults = null;
        try{
            tx = session.beginTransaction();
            String hql = "from NhaCungCap";
            Query query = session.createQuery(hql);
            listResults = query.list();
            tx.commit();
        } catch(HibernateException ex){
            if(tx!=null) tx.rollback();
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
    
    public NhaCungCap getNhaCungCap(String maNhaCcIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        NhaCungCap nhaCungCap = null;
        try{
            nhaCungCap =(NhaCungCap) session.get(NhaCungCap.class, maNhaCcIn);
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return nhaCungCap;
    }
    
    public boolean isExists(NhaCungCap nhaCungCapIn){
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean result = true;
        if(session.get(NhaCungCap.class, nhaCungCapIn.getMaNhaCc())==null){
            result = false;
        }
        return result;
    }
    
    /*Method to get List MaNhaCungCap*/
    public List<String> getListMaNhaCungCap(){
        List<String> listResults = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try{
            String hql = "select maNhaCc from NhaCungCap";
            listResults = session.createQuery(hql).list();
        } catch(HibernateException ex){
            ex.printStackTrace();
        } finally{
            session.close();
        }
        return listResults;
    }
}
