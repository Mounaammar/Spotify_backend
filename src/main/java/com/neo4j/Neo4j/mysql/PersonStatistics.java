package com.neo4j.Neo4j.mysql;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_statistics")
public class PersonStatistics implements Serializable {
    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getNbLikedSong() {
        return nbLikedSong;
    }

    public void setNbLikedSong(int nbLikedSong) {
        this.nbLikedSong = nbLikedSong;
    }

    public int getNbSubscription() {
        return nbSubscription;
    }

    public void setNbSubscription(int nbSubscription) {
        this.nbSubscription = nbSubscription;
    }

    public String getTopArtist() {
        return topArtist;
    }

    public void setTopArtist(String topArtist) {
        this.topArtist = topArtist;
    }

    @Id
    @Column(name="user_name")
    private String user_name;
    @Column(name="nbLikedSong")
    private int nbLikedSong;
    @Column(name="nbSubscription")
    private int nbSubscription;
    @Column(name="topArtist")
    private String topArtist;

    public PersonStatistics (String user_name, int nbLikedSong, int nbSubscription, String topArtist) {
        this.user_name = user_name;
        this.nbLikedSong = nbLikedSong;
        this.nbSubscription = nbSubscription;
        this.topArtist = topArtist;
    }

    public PersonStatistics() {

    }

    @Override
    public String toString() {
        return "PersonStatistics{" +
                "user_name='" + user_name + '\'' +
                ", nbLikedSong=" + nbLikedSong +
                ", nbSubscription=" + nbSubscription +
                ", topArtist='" + topArtist + '\'' +
                '}';
    }
}
