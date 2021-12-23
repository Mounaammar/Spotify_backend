package com.neo4j.Neo4j.mysql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="artist_statistics")
public class ArtistStatistics implements Serializable {
    @Id
    @Column( name="artist_name" )
    private String artist_name ;

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public int getNbSubscibedPersons() {
        return nbSubscibedPersons;
    }

    public void setNbSubscibedPersons(int nbSubscibedPersons) {
        this.nbSubscibedPersons = nbSubscibedPersons;
    }

    public int getNbSongs() {
        return nbSongs;
    }

    public void setNbSongs(int nbSongs) {
        this.nbSongs = nbSongs;
    }

    public String getTopFan() {
        return topFan;
    }

    public void setTopFan(String topFan) {
        this.topFan = topFan;
    }

    @Column( name="nbSubscibedPersons" )
    private int nbSubscibedPersons ;

    @Column( name="nbSongs" )
    private int nbSongs;

    @Column( name="topFan" )
    private String topFan;

    public ArtistStatistics(String artistName, int nbSubscribedPersons, int nbSongs, String topFan) {
        this.artist_name = artistName;
        this.nbSubscibedPersons = nbSubscribedPersons;
        this.nbSongs = nbSongs;
        this.topFan = topFan;
    }

    public ArtistStatistics() {

    }

    @Override
    public String toString() {
        return "ArtistStatistics{" +
                "artistName='" + artist_name + '\'' +
                ", nbSubscribedPersons=" + nbSubscibedPersons +
                ", nbSongs=" + nbSongs +
                ", topFan='" + topFan + '\'' +
                '}';
    }
}
