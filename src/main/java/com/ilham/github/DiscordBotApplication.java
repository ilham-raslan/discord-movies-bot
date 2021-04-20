package com.ilham.github;

import com.ilham.github.listener.MovieMessageListener;
import com.ilham.github.properties.credential.CredentialProperties;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.util.Properties;

public class DiscordBotApplication {

  public static void main(String[] args) throws LoginException {
    Properties credentialProperties = new CredentialProperties();
    JDA jda = JDABuilder.createDefault(credentialProperties.getProperty("discord-bot-token")).build();
    jda.addEventListener(new MovieMessageListener());
  }
}
