package com.how2java.test;

import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate20 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		String name = "iphone";
		Query query = s.createQuery("from Product p where p.name like ?");
		query.setString(0, "%"+name+"%");
		Iterator<Product> it = query.iterate();
		while(it.hasNext()) {
			Product p = it.next();
			System.out.println(p.getName());
		}
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
