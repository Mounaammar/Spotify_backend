package com.neo4j.Neo4j.neo4j;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Song {
    @Id
    private final String title;
    private final String duration;
    private final String  type;
    private final String rank ;
    @Relationship(type = "SANG", direction = Relationship.Direction.INCOMING)
    private final List<Artist> artists;
    public Song(String title, String duration, String type, String rank, List<Artist> artists) {
        this.title = title;
        this.duration = duration;
        this.type = type;
        this.rank = rank;
        this.artists = artists;

    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public String getRank() {
        return rank;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration='" + duration + '\'' +
                ", type='" + type + '\'' +
                ", rank='" + rank + '\'' +
                ", artists=" + artists +
                '}';
    }
}
