package com.neo4j.Neo4j.data;

import com.neo4j.Neo4j.mysql.ArtistStatistics;
import com.neo4j.Neo4j.neo4j.Artist;

public class FullArtist {
    private Artist artist ;
    private ArtistStatistics artistStatistics;

    public FullArtist(Artist artist, ArtistStatistics artistStatistics) {
        this.artist = artist;
        this.artistStatistics = artistStatistics;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public ArtistStatistics getArtistStatistics() {
        return artistStatistics;
    }

    public void setArtistStatistics(ArtistStatistics artistStatistics) {
        this.artistStatistics = artistStatistics;
    }
}
