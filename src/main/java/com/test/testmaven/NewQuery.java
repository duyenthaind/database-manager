package com.test.testmaven;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.test.hibernate.entities.DonDh;
import com.test.hibernate.entities.VatTu;
import com.test.testmaven.hibernate.HibernateUtil;

public class NewQuery {
//	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		
//		//simple example to take the data from the database
//		try {
//			String sql = "SELECT * FROM dondh WHERE SoDh = 'D001'";
//			Query query = session.createSQLQuery(sql);
//			List<DonDh> e = query.list();
//			for(DonDh obj : e) {
//				System.out.println(obj.toString());
//			}
//		}catch(Exception ex) {
//			
//		}
//		try {
//			session.beginTransaction();
//			String hql = "FROM dondh where SoDh = 'D001'";
//			Query query = session.createQuery(hql);
//			query.setCacheable(true);
//			List<DonDh> e = query.list();
//			for(DonDh obj: e) {
//				System.out.println(obj);
//			}
//			session.getTransaction();
//		}catch(Exception e){
//			
//		}
//		
//		//print the list with constraint
//		try {
//			String sql = "SELECT vattu.MaVTu, vattu.TenVTu, vattu.DVTinh, vattu.PhanTram FROM ctpnhap INNER JOIN vattu ON ctpnhap.MaVTu = vattu.MaVTu ";
//			SQLQuery query = session.createSQLQuery(sql);
//			query.addEntity(VatTu.class);
//			List results = query.list();
//			System.out.printf("%-12s%-12s%-12s%s", "MaVTu","TenVTu","DVTinh","PhanTram");
//			System.out.println();
//			for(Iterator iterator = results.iterator(); iterator.hasNext(); ) {
//				VatTu vattu = (VatTu) iterator.next();
//				System.out.printf("%-12s%-12s%-12s%s", vattu.getMaVTu(),vattu.getTenVTu(),vattu.getDvTinh(),vattu.getPhanTram());
//				System.out.println();
//			}
//		} catch(Exception e){
//			
//		}
//		
//	}
	
	public int countMaNCc() {
		int countTemp=0;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(DonDh.class);
			cr.add(Restrictions.eq("maNhaCc", "C03"));
			List dondhs = cr.list();
			
			for(Iterator iterator = dondhs.iterator(); iterator.hasNext(); iterator.next()) {
				countTemp++;
			};
			return countTemp;
 		} catch(HibernateException ex) {
 			if(tx!=null) tx.rollback();
 			ex.printStackTrace();
 		} finally {
 			session.close();
 		}
		return 0;
	}
}
