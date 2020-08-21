package com.test.testmaven.manage;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.hibernate.entities.TonKho;
import com.test.testmaven.hibernate.HibernateUtil;
import org.hibernate.Query;

public class ManageTonKho {
	/*Method to create a tonkho*/
	public int addTonKho(String namThangIn, String maVTuIn, int slDauIn, int tongSLNIn, int tongSLXIn, int sLCuoiIn) {
                int status = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TonKho tonkho = new TonKho(namThangIn, maVTuIn, slDauIn, tongSLNIn, tongSLXIn, sLCuoiIn);
			session.save(tonkho);
			tx.commit();
                        status = 1;
		} catch(HibernateException ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
                return status;
	}
	
	/*Method to read all the tonkho*/
	public void listTonKho() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "select * from tonkho";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(TonKho.class);
			List results = query.list();
			
			System.out.printf("%-12s%-12s%-12s%-12s%-12s%s", "MaVTu","NamThang","SlDau", "TongSLN","TongSLX","SLCuoi");
			System.out.println();
			for(Iterator iterator = results.iterator(); iterator.hasNext(); ) {
				TonKho tonkho = (TonKho) iterator.next();
				System.out.printf("%-12s%-12s%-12s%-12s%-12s%s", tonkho.getMaVTu(), tonkho.getNamThang(), tonkho.getSlDau(), tonkho.getTongSLN(), tonkho.getTongSLX(), tonkho.getSLCuoi());
				System.out.println();
			}
			tx.commit();
		} catch(HibernateException ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/*Method to update a tonkho */
	public int updateVatTu(String namThangIn, String maVTuIn, int slDauIn, int tongSLNIn, int tongSLXIn, int sLCuoiIn) {
                int status = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
                        TonKho tonKho = null;
                        String hql = "from TonKho where maVTu = :maVatTu and namThang = :namThang";
                        Query query = session.createQuery(hql);
                        query.setParameter("maVatTu", maVTuIn);
                        query.setParameter("namThang", namThangIn);
                        List<TonKho> result = query.list();
                        if(result.isEmpty()){
                            tonKho = null;
                        } else{
                            tonKho = result.get(0);
                        }
			tonKho.setMaVTu(maVTuIn);
			tonKho.setSLCuoi(sLCuoiIn);
			tonKho.setNamThang(namThangIn);
			tonKho.setSlDau(slDauIn);
			tonKho.setTongSLN(tongSLNIn);
			tonKho.setTongSLX(tongSLXIn);
			session.update(tonKho);
			tx.commit();
                        status = 1;
		} catch(HibernateException ex) {
			if(tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
                return status;
	}
	
	/*Method to delete a tonkho*/
	public int deleteVatTu(String maVTuIn, String namThangIn) {
                int status = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
                        TonKho tonKho = null;
                        String hql = "from TonKho where maVTu = :maVatTu and namThang = :namThang";
                        Query query = session.createQuery(hql);
                        query.setParameter("maVatTu", maVTuIn);
                        query.setParameter("namThang", namThangIn);
                        List<TonKho> result = query.list();
                        if(result.isEmpty()){
                            tonKho = null;
                        } else{
                            tonKho = result.get(0);
                        }
			session.delete(tonKho);
			tx.commit();
                        status = 1;
		} catch(HibernateException ex) {
			if(tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
                return status;
	}
        
        /*Method to getlist*/
        public List getList(){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            List listResults = null;
            try{
                tx = session.beginTransaction();
                String sql = "select * from tonkho";
                SQLQuery query = session.createSQLQuery(sql);
                query.addEntity(TonKho.class);
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
        
        /*Method to get list TonKho*/
        public List<TonKho> getListHql(){
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<TonKho> listResults = null;
            try{
                String hql = "from TonKho";
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
        public boolean isExists(TonKho tonKho){
            Session session = HibernateUtil.getSessionFactory().openSession();
            boolean result = true;
            String hql = "from TonKho where maVTu = :maVatTu and namThang = :namThang";
            Query query = session.createQuery(hql);
            query.setParameter("maVatTu", tonKho.getMaVTu());
            query.setParameter("namThang", tonKho.getNamThang());
            List<TonKho> listResult = query.list();
            if(listResult.isEmpty()){
                return false;
            }
            session.close();
            return result;
        }
        
        /*Method to get a TonKho*/
        public TonKho getVatTu(String maVTuIn, String namThangIn){
            Session session = HibernateUtil.getSessionFactory().openSession();
            TonKho tonKho = null;
            try{
                String hql = "from TonKho where maVTu = :maVatTu and namThang = :namThang";
                Query query = session.createQuery(hql);
                query.setParameter("maVatTu", maVTuIn);
                query.setParameter("namThang", namThangIn);
                List<TonKho> result = query.list();
                if(result.isEmpty()){
                    tonKho = null;
                } else{
                    tonKho = result.get(0);
                }
                
            } catch(HibernateException ex){
                ex.printStackTrace();
            } finally{
                session.close();
            }
            return tonKho;
        }
}
