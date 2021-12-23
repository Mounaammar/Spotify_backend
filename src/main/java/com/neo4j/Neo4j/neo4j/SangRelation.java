package com.neo4j.Neo4j.neo4j;

public class SangRelation {
    private String artistName;
    private String songName;

    public SangRelation(String artistName, String songName) {
        this.artistName = artistName;
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "subscriptionRelation{" +

                ", The song ='" + songName + '\'' +
                "sang by ='" + artistName + '\'' +
                '}';
    }
}
