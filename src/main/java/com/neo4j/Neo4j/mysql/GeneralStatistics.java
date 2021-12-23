package com.neo4j.Neo4j.mysql;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "general_statistics")

public class GeneralStatistics  implements Serializable {
    @Id

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTotalArtists() {
        return totalArtists;
    }

    public void setTotalArtists(int totalArtists) {
        this.totalArtists = totalArtists;
    }

    public int getTotal_Songs() {
        return total_Songs;
    }

    public void setTotal_Songs(int total_Songs) {
        this.total_Songs = total_Songs;
    }

    public int getTotal_Users() {
        return total_Users;
    }

    public void setTotal_Users(int total_Users) {
        this.total_Users = total_Users;
    }

    public String getTopArtists() {
        return topArtists;
    }

    public void setTopArtists(String topArtists) {
        this.topArtists = topArtists;
    }

    public String getTopSong() {
        return topSong;
    }

    public void setTopSong(String topSong) {
        this.topSong = topSong;
    }

    @Column(name="totalArtists")
    private int totalArtists;
    @Column(name="total_Songs")
    private int total_Songs;
    @Column(name="total_Users")
    private int total_Users;
    @Column(name="topArtists")
    private String topArtists;

    @Column(name="topSong")
    private String topSong;

    public GeneralStatistics(Integer id, int totalArtists, int totalSongs, int totalUsers, String topArtist, String topSong) {
        this.id = id;
        this.totalArtists = totalArtists;
        this.total_Songs = totalSongs;
        this.total_Users = totalUsers;
        this.topArtists = topArtist;
        this.topSong = topSong;
    }

    public GeneralStatistics() {

    }

    @Override
    public String toString() {
        return "GeneralStatistics{" +
                "id=" + id +
                ", totalArtists=" + totalArtists +
                ", totalSongs=" + total_Songs +
                ", totalUsers=" + total_Users +
                ", topArtist='" + topArtists + '\'' +
                ", topSong='" + topSong + '\'' +
                '}';
    }
}
