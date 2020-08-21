package com.test.testmaven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.hibernate.entities.DonDh;
import com.test.testmaven.hibernate.HibernateUtil;

public class ManageDonDh {

//	public static void main(String[] args) {
//		//add data to the database
//		ManageDonDh ME = new ManageDonDh();
////		try {
////			Integer dondhId1 = ME.addDonDh("D006", "C06", (Date) format.parse("2020-05-09"));
////		} catch (ParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		try {
////			Integer dondhId2 = ME.addDonDh("D007", "C03", (Date) format.parse("2020-05-12"));
////		} catch (ParseException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		ME.showList();
//				
//	}

	/*Method to create a DonDh*/
	public void addDonDh(String soDhIn, String maNccIn, Date ngayDhIn ){
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      Integer dondhId = null;
	      
	      try {
	         tx = session.beginTransaction();
	         DonDh dondh = new DonDh(soDhIn,maNccIn,ngayDhIn);
	         session.save(dondh); 
	         tx.commit();
	      } catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
        }
	

	//method show the current table
	public void showList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String sql = "SELECT *FROM dondh";
			SQLQuery query = session.createSQLQuery(sql);
			query.addEntity(DonDh.class);
			List results = query.list();
			System.out.printf("%-12s%-30s%s", "SoDh","NgayDh","MaNhaCc");
			System.out.println();
			for(Iterator iterator = results.iterator(); iterator.hasNext(); ) {
				DonDh dondh = (DonDh) iterator.next();
				System.out.printf("%-12s%-30s%s", dondh.getSoDh(), dondh.getNgayDh(), dondh.getMaNhaCc());
				System.out.println();
			}
			tx.commit();
		}catch(HibernateException ex) {
			if(tx!=null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//method update table dondh
	public void updateDonDh(String soDhIn, String ngayDhIn, String maNhaCcIn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		try {
			tx = session.beginTransaction();
			DonDh dondh = (DonDh) session.get(DonDh.class, soDhIn);
			dondh.setSoDh(soDhIn);
			dondh.setMaNhaCc(maNhaCcIn);
			try {
				dondh.setNgayDh((Date) format.parse(ngayDhIn));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			session.update(dondh);
			tx.commit();
		} catch(HibernateException ex) {
			if(tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	//method delete element from table dondh
	public void deleteDonDh(String soDhIn) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			DonDh dondh = (DonDh) session.get(DonDh.class, soDhIn);
			session.delete(dondh);
			tx.commit();
		} catch(HibernateException ex) {
			if(tx != null) tx.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}
}
