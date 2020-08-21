/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.testmaven;

import com.test.hibernate.entities.DonDh;
import com.test.testmaven.hibernate.HibernateUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class Main {
//    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            Query query = session.createSQLQuery("show tables");
//            List e = query.list();
//            for (Object object : e) {
//                System.out.println(" e" + object.toString());
//            }
//        } catch (Exception e) {
//        }
//        try {
//        	Query query = session.createSQLQuery("SELECT MaNhaCc FROM dondh");
//        	List results = query.list();
//        	for(Object object: results) {
//        		System.out.println(" MaNhaCc " + object.toString());
//        	}
//        }catch(Exception ex) {
//        	
//        }
//        
//        System.out.println("Input the sql command: ");
//        Scanner scanner = new Scanner(System.in);
//        String sql = scanner.nextLine();
//        Transaction tx = null;
//        try {
//        	tx = session.beginTransaction();
//        	SQLQuery query = session.createSQLQuery(sql);
//            query.addEntity(DonDh.class);
//            List results = query.list();
//    		System.out.printf("%-12s%-30s%s", "SoDh","NgayDh","MaNhaCc");
//    		System.out.println();
//    		for(Iterator iterator = results.iterator(); iterator.hasNext(); ) {
//    			DonDh dondh = (DonDh) iterator.next();
//    			System.out.printf("%-12s%-30s%s", dondh.getSoDh(), dondh.getNgayDh(), dondh.getMaNhaCc());
//    			System.out.println();
//    		}
//        }catch(HibernateException ex) {
//        	if(tx!=null) tx.rollback();
//        	ex.printStackTrace();
//        } finally {
//        	session.close();
//        }
//        
//    }
}
