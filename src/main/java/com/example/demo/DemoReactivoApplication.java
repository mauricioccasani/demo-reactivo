package com.example.demo;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.bean.ProductoBean;

import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;

@SpringBootApplication
@Log4j2
public class DemoReactivoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoReactivoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.faltMap_();
		
	}
	
	private void faltMap_() {
		List<ProductoBean>lstProducto=
				Arrays.asList(ProductoBean.builder().id(1).nombre("Mauricio").fecha(new Date()).build(),
						ProductoBean.builder().id(2).nombre("Juana").fecha(new Date()).build(),
						ProductoBean.builder().id(3).nombre("Mauricio").fecha(new Date()).build(),
						ProductoBean.builder().id(4).nombre("Carla").fecha(new Date()).build());
		Flux.fromIterable(lstProducto)
		.flatMap(p->{
			p.setNombre(p.getNombre()+" Olivares");
			return Flux.just(p);
		})
		.filter(p->p.getNombre().equals("Mauricio Olivares"))
		.subscribe(l->log.info("Productos: {}",l));
	}

}
