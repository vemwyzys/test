package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		Product p = new Product();
		p.setName("iphone X");
		p.setPrice(9999);
		//通过调用Session的save方法把对象保存到数据库
		s.save(p);
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
