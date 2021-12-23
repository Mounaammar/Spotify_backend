package com.neo4j.Neo4j.neo4j;

public class SubscriptionRelation {
    private String artistName;
    private String userName;

    public SubscriptionRelation(String artistName, String userName) {
        this.artistName = artistName;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "subscriptionRelation{" +

                ", The user ='" + userName + '\'' +
                "is subscribed to ='" + artistName + '\'' +
                '}';
    }
}
