package com.how2java.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate9 {
	public static void main(String[] args) {
		//获取SessionFactory
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		//通过SessionFactory获取一个Session
		Session s = sf.openSession();
		//在session的基础上开启一个事务
		s.beginTransaction();
		
		String name = "iphone";
		
		String sql = "select * from product_ p where p.name like '%"+name+"%'";
		             //"select * from product_ p where p.name like '%"+name+"%'";
		Query q = s.createSQLQuery(sql);
		List<Object[]> list = q.list();
		for(Object[] os:list) {
			for(Object filed:os) {
				System.out.println(filed+"\t");
			}
			System.out.println(os);
		}
		//提交事务
		s.getTransaction().commit();
		//关闭Session
		s.close();
		//关闭SessionFactory
		sf.close();
	}

}
