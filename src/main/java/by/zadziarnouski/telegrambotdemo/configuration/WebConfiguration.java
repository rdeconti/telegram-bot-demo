package by.zadziarnouski.telegrambotdemo.configuration;


import by.zadziarnouski.telegrambotdemo.Bot;
import by.zadziarnouski.telegrambotdemo.service.CityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


@Configuration
public class WebConfiguration {

    @Bean
    public TelegramLongPollingBot bot(CityService cityService) {
        return new Bot(cityService);
    }
}
