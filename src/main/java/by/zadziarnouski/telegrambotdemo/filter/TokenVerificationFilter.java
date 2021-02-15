package by.zadziarnouski.telegrambotdemo.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


@Component
public class TokenVerificationFilter implements Filter {

    private final TelegramLongPollingBot telegramBotApi;

    @Autowired
    public TokenVerificationFilter(TelegramLongPollingBot telegramBotApi) {
        this.telegramBotApi = telegramBotApi;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (tokenVerification(req.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            res.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid token");
        }
    }

    private boolean tokenVerification(String requestURI) {
        String[] split = requestURI.split("/");
        return telegramBotApi.getBotToken().equals(split[1]);
    }

}
