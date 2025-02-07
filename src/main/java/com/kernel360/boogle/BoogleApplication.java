package com.kernel360.boogle;


import com.kernel360.boogle.global.annotation.BoogleAnnotation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@BoogleAnnotation
@SpringBootApplication
public class BoogleApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(BoogleApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
