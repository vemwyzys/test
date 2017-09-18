package com.how2java.test;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate21 {
	public static void main(String[] args) {
		// 获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		// 通过SessionFactory获取一个Session
		Session s = sf.openSession();
		// 在session的基础上开启一个事务
		s.beginTransaction();

		String name = "iphone";
		Query q = s.createQuery("select count(*) from Product p where p.name like ?");
		q.setString(0, "%" + name + "%");
		long total = (Long) q.uniqueResult();
		System.out.println(total);

		// 提交事务
		s.getTransaction().commit();
		// 关闭Session
		s.close();
		// 关闭SessionFactory
		sf.close();
	}

}
