package com.test.testmaven.manage;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.hibernate.entities.VatTu;
import com.test.testmaven.hibernate.HibernateUtil;
import org.hibernate.Query;

public class ManageVatTu {
	
	/*Method to create a vattu in the database*/
	public void addVatTu(String maVTuIn, String tenVTuIn, String dvTinhIn, double phanTramIn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			VatTu vattu = new VatTu(maVTuIn, tenVTuIn, dvTinhIn, phanTramIn);
			session.save(vattu);
			tx.commit();
		} catch(HibernateException ex){
			if(tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/*Method to read all the vattu*/
	public void listVatTu() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "select * from vattu";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(VatTu.class);
			List results = query.list();
			
			System.out.printf("%-12s%-12s%-12s%s", "MaVTu","TenVTu","DvTinh", "PhanTram");
			System.out.println();
			for(Iterator iterator = results.iterator(); iterator.hasNext(); ) {
				VatTu vattu = (VatTu) iterator.next();
				System.out.printf("%-12s%-12s%-12s%s", vattu.getMaVTu(), vattu.getTenVTu(), vattu.getDvTinh(), vattu.getPhanTram());
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
	
	/*Method to update a vattu */
	public void updateVatTu(String maVTuIn, String tenVTuIn, String dvTinhIn, double phanTramIn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			VatTu vattu = (VatTu) session.get(VatTu.class, maVTuIn);
			vattu.setMaVTu(maVTuIn);
			vattu.setTenVTu(tenVTuIn);
			vattu.setDvTinh(dvTinhIn);
			vattu.setPhanTram(phanTramIn);
			session.update(vattu);
			tx.commit();
		} catch(HibernateException ex) {
			if(tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	/*Method to delete a vattu*/
	public int deleteVatTu(String maVTuIn) {
                int status = 0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			VatTu vattu = (VatTu) session.get(VatTu.class, maVTuIn);
			session.delete(vattu);
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
        public List<VatTu> getList(){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            List<VatTu> listResults = null;
            try{
                tx = session.beginTransaction();
                String hql = "from VatTu";
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
        
        public VatTu getVatTu(String MaVTuIn){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            VatTu vatTu = null;
            try{
                tx = session.beginTransaction();
                vatTu = (VatTu) session.get(VatTu.class, MaVTuIn);
                tx.commit();
                
            } catch(HibernateException ex){
                if(tx!=null) tx.rollback();
                ex.printStackTrace();
            } finally{
                session.close();
            }
            return vatTu;
        }
        
        public boolean isExists(VatTu vatTu){
            Session session = HibernateUtil.getSessionFactory().openSession();
            boolean result = true;
            if(session.get(VatTu.class, vatTu.getMaVTu())==null){
                result=false;
            }
            session.close();
            return result;
        }
        
        /*Method to get list MaVTu*/
        public List<String> getListMaVTu(){
            List<String> listResults = null;
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "select distinct maVTu from VatTu";
            listResults = session.createQuery(hql).list();
            return listResults;
        }
}
