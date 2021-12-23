package com.neo4j.Neo4j.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "song_statistics")
public class SongStatistics implements Serializable {
    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public int getNbLikedPerson() {
        return nbLikedPerson;
    }

    public void setNbLikedPerson(int nbLikedPerson) {
        this.nbLikedPerson = nbLikedPerson;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Id
    @Column(name="song_name")
    private String song_name;
    @Column(name="nbLikedPerson")
    private int nbLikedPerson ;
    @Column(name="artistName")
    private String artistName;

    public SongStatistics(String song_name, int nbLikedPerson, String artistName) {
        this.song_name = song_name;
        this.nbLikedPerson = nbLikedPerson;
        this.artistName = artistName;
    }

    public SongStatistics() {

    }

    @Override
    public String toString() {
        return "SongStatistics{" +
                "song_name='" + song_name + '\'' +
                ", nbLikedPerson=" + nbLikedPerson +
                ", artistName='" + artistName + '\'' +
                '}';
    }
}
