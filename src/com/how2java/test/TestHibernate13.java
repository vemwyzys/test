package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate13 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		Product p=(Product)s.get(Product.class, 1);
		s.delete(p);
		
		Product p2=(Product)s.get(Product.class, 1);
		p2.setName("长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称长度超过30的字符串作为产品名称");
		s.update(p2);
		
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
