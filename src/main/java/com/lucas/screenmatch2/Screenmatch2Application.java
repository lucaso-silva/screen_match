package com.lucas.screenmatch2;

import com.lucas.screenmatch2.main.Main;
import com.lucas.screenmatch2.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Screenmatch2Application implements CommandLineRunner {
    @Autowired
    private SeriesRepository seriesRepository;

	public static void main(String[] args) {
		SpringApplication.run(Screenmatch2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main(seriesRepository);
		main.displayMenu();

	}
}
