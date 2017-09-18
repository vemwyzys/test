package com.how2java.test;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.how2java.pojo.Product;

public class TestHibernate19 {
	static Session s1;
	static Session s2;

	public static void main(String[] args) throws InterruptedException {

		// 获取SessionFactory
		final SessionFactory sf = new Configuration().configure().buildSessionFactory();

		Thread t1 = new Thread() {
			public void run() {
				Session s1 = sf.getCurrentSession();
			}
		};
		t1.start();

		Thread t2 = new Thread() {
			public void run() {
				Session s2 = sf.getCurrentSession();
			}
		};
		t2.start();
		t1.join();
		t2.join();
		System.out.println(s1 == s2);
	}

}
