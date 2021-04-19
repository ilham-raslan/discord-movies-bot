package com.ilham.github;

import com.ilham.github.properties.application.ApplicationProperties;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class DiscordBotApplication {

    public static void main(String[] args) throws LoginException {
//        JDA jda = JDABuilder.createDefault("token").build();
        ApplicationProperties applicationProperties = new ApplicationProperties();
        System.out.println(applicationProperties.getPropertyByName("connection-uri"));
    }
}
