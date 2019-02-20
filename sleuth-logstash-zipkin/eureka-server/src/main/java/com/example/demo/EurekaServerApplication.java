package com.example.demo;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class EurekaServerApplication {

	public static void main(String[] args) {
		LinkedList<String> s = new LinkedList<>();
		s.peek();
		s.poll();
		s.remove("1");
		try {
			Thread.sleep(10);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (args){
			try {
				args.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ReentrantLock r = new ReentrantLock();


		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();



	}
}
