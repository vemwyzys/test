package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate14 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		Product p=(Product)s.load(Product.class, 1);
		System.out.println("log1");
		System.out.println(p.getName());
		System.out.println("log2");
		
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
