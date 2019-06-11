package cn.moling.spacet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication(scanBasePackages={"cn.moling.spacet"})
@MapperScan({"cn.moling.spacet.*.dao"})
@ServletComponentScan
public class SpacetApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpacetApplication.class, args);
	}

}
