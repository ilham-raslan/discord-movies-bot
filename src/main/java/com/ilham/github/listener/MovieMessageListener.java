package com.ilham.github.listener;

import com.ilham.github.service.MovieService;
import com.ilham.github.service.impl.MovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

@Slf4j
public class MovieMessageListener extends ListenerAdapter {
  MovieService movieService;

  public MovieMessageListener() {
    this.movieService = new MovieServiceImpl();
  }

  @Override
  public void onMessageReceived(MessageReceivedEvent event) {
    String message = event.getMessage().getContentRaw();
    String returnMessage = "";

    if (message.startsWith("!add-movie")) {
      String[] arguments = message.split(" ", 2);
      String movie = arguments[1];

      movieService.addMovie(movie);

      returnMessage = "Added movie: " + movie;
    } else if (message.startsWith("!recommend-movie-from")) {
      String[] arguments = message.split(" ", 2);
      String movie = arguments[1];

      List<String> movies = this.movieService.recommendByMovieName(movie);

      returnMessage = String.join("\n", movies);
    } else if (message.equals("!list-movies")) {
      List<String> movies = movieService.findAllMovies();

      returnMessage = String.join("\n", movies);
    }

    MessageChannel channel = event.getChannel();
    channel.sendMessage(returnMessage).queue();
  }
}
