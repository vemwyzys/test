package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate22 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s1 = sf.openSession();
		Session s2 = sf.openSession();
		//在session的基础上开启一个事务
		s1.beginTransaction();
		s2.beginTransaction();
		
		Product p1 = (Product) s1.get(Product.class, 1);
		System.out.println("产品1的原始价格是："+p1.getPrice());
		p1.setPrice(p1.getPrice()+1000);
		System.out.println("产品1的目前价格是："+p1.getPrice());
		
		Product p2 = (Product) s2.get(Product.class, 1);
		p2.setPrice(p2.getPrice()+1);
		
		s1.update(p1);
		s2.update(p2);
		
		//提交事务
		s1.getTransaction().commit();
		s2.getTransaction().commit();
		
		Product p = (Product) s2.get(Product.class, 1);
		System.out.println("现在的价格是"+p.getPrice());
		
		//关闭Session
		s1.close();
		s2.close();
		//关闭SessionFactory
		sf.close();
	}

}
