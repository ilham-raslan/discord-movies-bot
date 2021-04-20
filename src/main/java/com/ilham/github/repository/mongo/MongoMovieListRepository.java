package com.ilham.github.repository.mongo;

import com.ilham.github.properties.application.ApplicationProperties;
import com.ilham.github.properties.credential.CredentialProperties;
import com.ilham.github.repository.MovieListRepository;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MongoMovieListRepository implements MovieListRepository {
    Properties credentialProperties;
    Properties applicationProperties;

    public MongoMovieListRepository() {
        this.credentialProperties = new CredentialProperties();
        this.applicationProperties = new ApplicationProperties();
    }

    @Override
    public void addMovie(String movieName) {
        MongoClientURI uri =
                new MongoClientURI(this.credentialProperties.getProperty("mongodb-connection-string"));

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase mongoDatabase =
                mongoClient.getDatabase(this.applicationProperties.getProperty("mongo-db-name"));

        MongoCollection<Document> collection = mongoDatabase.getCollection("movie");

        Bson filter = Filters.eq("name", movieName);

        Bson update = new Document("$set", new Document().append("name", movieName));
        UpdateOptions options = new UpdateOptions().upsert(true);

        collection.updateOne(filter, update, options);

        mongoClient.close();
    }

    @Override
    public List<String> findAllMovies() {
        List<String> movies = new ArrayList<>();

        MongoClientURI uri =
                new MongoClientURI(this.credentialProperties.getProperty("mongodb-connection-string"));

        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase mongoDatabase =
                mongoClient.getDatabase(this.applicationProperties.getProperty("mongo-db-name"));

        MongoCollection<Document> collection = mongoDatabase.getCollection("movie");
        FindIterable<Document> iterable = collection.find();

        try (MongoCursor<Document> iterator = iterable.iterator()) {
            while (iterator.hasNext()) {
                movies.add(iterator.next().get("name").toString());
            }
        }

        mongoClient.close();
        return movies;
    }
}
