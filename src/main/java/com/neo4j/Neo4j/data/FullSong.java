package com.neo4j.Neo4j.data;

import com.neo4j.Neo4j.mysql.SongStatistics;
import com.neo4j.Neo4j.neo4j.Song;

public class FullSong {
    private Song song;
    private SongStatistics songStatistics;

    public FullSong(Song song, SongStatistics songStatistics) {
        this.song = song;
        this.songStatistics = songStatistics;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public SongStatistics getSongStatistics() {
        return songStatistics;
    }

    public void setSongStatistics(SongStatistics songStatistics) {
        this.songStatistics = songStatistics;
    }
}
