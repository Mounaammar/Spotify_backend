package com.neo4j.Neo4j.neo4j;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Person {
    @Id
    private final String name;
    private final String email;


    @Relationship(type = "ADDED_TO_FAVORITE_BY", direction = Relationship.Direction.INCOMING)
    private final List<Song> songs;
    public Person(String name, String email, List<Song> songs) {
        this.name = name;
        this.email = email;

        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Song> getSongs() {
        return songs;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", songs=" + songs +
                '}';
    }
}

