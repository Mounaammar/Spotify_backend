package com.neo4j.Neo4j.neo4j;

public class SubscribedArtistforPerson {
    private String nameArtist;
    private int numberOfSongsforArtist;
 //   private Person person;
//    private Song song;
 //   private Artist artist;

    public SubscribedArtistforPerson(String nameArtist, int numberOfSongsforArtist) {
        this.nameArtist = nameArtist;
        this.numberOfSongsforArtist = numberOfSongsforArtist;

    }



    public String getNameArtist() {
        return nameArtist;
    }

    public void setNameArtist(String nameArtist) {
        this.nameArtist = nameArtist;
    }

    public int getNumberOfSongsforArtist() {
        return numberOfSongsforArtist;
    }

    public void setNumberOfSongsforArtist(int numberOfSongsforArtist) {
        this.numberOfSongsforArtist = numberOfSongsforArtist;
    }

    @Override
    public String toString() {
        return "subscribedArtistforPerson{" +
                "nameArtist='" + nameArtist + '\'' +
                ", numberOfSongsforArtist=" + numberOfSongsforArtist +
                '}';
    }
}
