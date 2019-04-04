package com.pancou.ad.listener;

import java.io.IOException;

import org.apache.log4j.Logger;

public class Test {
	Logger log = Logger.getLogger(getClass());

	void print() {
		log.info("dddddddd");
	}

	public static void main(String[] args) throws IOException {
		System.out.println("1");
		Test t = new Test();
		t.print();
	}
}
