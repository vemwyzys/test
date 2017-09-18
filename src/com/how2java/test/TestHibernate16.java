package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Category;
import com.how2java.pojo.Product;

public class TestHibernate16 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		System.out.println("log");
		Category c= (Category) s.get(Category.class, 1);
		System.out.println("log");
		Category c2= (Category) s.get(Category.class, 1);
		System.out.println("log");
		
		Product p1 = new Product();
		p1.setName("101");
		Product p2 = new Product();
		p2.setName("102");
		Product p3 = new Product();
		p3.setName("103");
		Product p4 = new Product();
		p4.setName("104");
		
		c.getProducts().add(p1);
		c.getProducts().add(p2);
		c.getProducts().add(p3);
		c.getProducts().add(p4);
		
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
