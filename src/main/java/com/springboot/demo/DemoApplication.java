package com.springboot.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoApplication {

	/**
	 * @link{https://start.spring.io/}
	 *
	 * <br> start your spring boot project </br>
	 * @return
	 */
	@RequestMapping("/")
	String index() {
		return "hello spring boot";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		// 关闭bannner
//		SpringApplication app = new SpringApplication(DemoApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);
	}
}
