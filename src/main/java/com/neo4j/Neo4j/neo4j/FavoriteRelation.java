package com.neo4j.Neo4j.neo4j;

public class FavoriteRelation {
    private String userName;
    private String songName;

    public FavoriteRelation(String userName, String songName) {
        this.userName = userName;
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "subscriptionRelation{" +

                ", The song ='" + songName + '\'' +
                "sang by ='" + userName + '\'' +
                '}';
    }
}
