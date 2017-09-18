package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate5 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		Product p = (Product) s.get(Product.class, 5);
		System.out.println("id=5产品名称是："+p.getName());
		s.delete(p);
		//Product p1 = (Product) s.get(Product.class, 5);
		//System.out.println("id=5产品名称是："+p.getName());
		s.getTransaction().commit();
		s.close();
		sf.close();
	}

}
