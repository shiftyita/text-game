package it.shifty.textgame;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdventureGameApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdventureGameApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
