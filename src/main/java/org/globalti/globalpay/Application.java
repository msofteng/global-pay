package org.globalti.globalpay;

import java.util.List;

import org.globalti.globalpay.entity.TaxaEntity;
import org.globalti.globalpay.repository.TaxaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner run(TaxaRepository taxaRepository) {
		return args -> {
			taxaRepository.saveAll(
				List.of(
					new TaxaEntity(0,  0,  3.00, 2.5),
    			new TaxaEntity(1,  10, 12.00, 0.0),
    			new TaxaEntity(11, 20, 0.00, 8.2),
    			new TaxaEntity(21, 30, 0.00, 6.9),
    			new TaxaEntity(31, 40, 0.00, 4.7),
    			new TaxaEntity(41, 50, 0.00, 1.7)
				)
			);

			System.out.println("As taxas foram carregadas com sucesso!");
		};
	}
}