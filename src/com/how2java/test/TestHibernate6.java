package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate6 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		Product p = (Product)s.get(Product.class, 6);
		System.out.println(p.getName());
		p.setName("iphone3GS");
		s.update(p);
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
