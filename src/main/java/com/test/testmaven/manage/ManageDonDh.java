package com.test.testmaven.manage;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.hibernate.entities.DonDh;
import com.test.testmaven.hibernate.HibernateUtil;
import org.hibernate.Query;

public class ManageDonDh {

    //Method to create a dondh in the database
    public boolean addDonDh(String soDhIn, String maNccIn, Date ngayDhIn) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            DonDh dondh = new DonDh(soDhIn, maNccIn, ngayDhIn);
            session.save(dondh);
            tx.commit();
            result = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    //Method to read all the dondh
    public void listDonDh() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sql = "SELECT *FROM dondh";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(DonDh.class);
            List results = query.list();
            System.out.printf("%-12s%-30s%s", "SoDh", "NgayDh", "MaNhaCc");
            System.out.println();
            for (Iterator iterator = results.iterator(); iterator.hasNext();) {
                DonDh dondh = (DonDh) iterator.next();
                System.out.printf("%-12s%-30s%s", dondh.getSoDh(), dondh.getNgayDh(), dondh.getMaNhaCc());
                System.out.println();
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
    }

    //Method to update a dondh
    public boolean updateDonDh(String soDhIn, Date ngayDhIn, String maNhaCcIn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            DonDh dondh = (DonDh) session.get(DonDh.class, soDhIn);
            dondh.setSoDh(soDhIn);
            dondh.setMaNhaCc(maNhaCcIn);
            dondh.setNgayDh(ngayDhIn);
            session.update(dondh);
            tx.commit();
            result = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    //Method to delete a dondh
    public boolean deleteDonDh(String soDhIn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean result = false;
        try {
            tx = session.beginTransaction();
            DonDh dondh = (DonDh) session.get(DonDh.class, soDhIn);
            session.delete(dondh);
            tx.commit();
            result = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    /*Method to check if ChiTietDonHang were available or not*/
    public boolean isExists(DonDh donDatHang) {
        boolean result = true;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "from DonDh where soDh = :soDh";
        Query query = session.createQuery(hql);
        query.setParameter("soDh", donDatHang.getSoDh());
        List<DonDh> listResult = query.list();
        if (listResult.isEmpty()) {
            result = false;
        } else {
            result = true;
        }
        session.close();
        return result;
    }

    /*Method to get list ChiTietDonHang*/
    public List<DonDh> getList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<DonDh> listResults = null;
        try {
            String hql = "from DonDh";
            Query query = session.createQuery(hql);
            listResults = query.list();
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return listResults;
    }

    /*Method to get a ChiTietDonHang*/
    public DonDh getChiTietDonHang(String soDhIn) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DonDh donDatHang = null;
        try {
            String hql = "from DonDh where soDh = :soDh";
            Query query = session.createQuery(hql);
            query.setParameter("soDh", soDhIn);
            List<DonDh> result = query.list();
            if (result.isEmpty()) {
                donDatHang = null;
            } else {
                donDatHang = result.get(0);
            }

        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return donDatHang;
    }

    /*Method to get list SoDh*/
    public List<String> getListSoDh() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<String> listResults = null;
        String hql = "select distinct soDh from DonDh";
        listResults = session.createQuery(hql).list();
        return listResults;
    }
}
