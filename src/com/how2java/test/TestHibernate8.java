package com.how2java.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;

import com.how2java.pojo.Product;

public class TestHibernate8 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		String name = "iphone";
		//通过session创建一个Criteria
		Criteria c = s.createCriteria(Product.class);
		//增加约束
		c.add(Restrictions.like("name","%"+name+"%"));
		//调用list()方法查询结果的合集
		List<Product> ps = c.list();
		for(Product p:ps) {
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
