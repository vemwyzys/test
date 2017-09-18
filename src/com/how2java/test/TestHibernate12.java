package com.how2java.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;
import com.how2java.pojo.User;

public class TestHibernate12 {
	public static void main(String[] args) {
		// 获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		// 通过SessionFactory获取一个Session
		Session s = sf.openSession();
		// 在session的基础上开启一个事务
		s.beginTransaction();

		// 增加3个用户
		Set<User> users = new HashSet();
		for (int i = 0; i < 3; i++) {
			User u = new User();
			u.setName("user"+i);
			users.add(u);
			s.save(u);
		}
		
		//产品1被用户1，2，3购买
		Product p1=(Product)s.get(Product.class, 1);
		
		p1.setUsers(users);//users是3个用户的一个hashset
		s.save(p1);
		//会生成一张user_product的中间表
		
		// 提交事务
		s.getTransaction().commit();
		// 关闭Session
		s.close();
		// 关闭SessionFactory
		sf.close();
	}

}
