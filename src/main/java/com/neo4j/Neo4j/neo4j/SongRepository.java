package com.neo4j.Neo4j.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends Neo4jRepository<Song, String> {
    @Query("MATCH (s:Song) RETURN s")
    List<Song> getAllSongs();

    @Query("MATCH (s:Song) ,(p:Person) where s.title=$songName AND p.name=$personName CREATE (s)-[stu:ADDED_TO_FAVORITE_BY] -> (p)")
    FavoriteRelation addToFavorite(String personName, String songName);

    @Query("MATCH (s:Song{name:$songName}) RETURN s")
    Song getSong(String songName);

    @Query("MATCH (s:Song{name:$songName})-[r:ADDED_TO_FAVORITE_BY] ->(p:Person{name:$personName}) delete(r)")
    String deleteFavorite(String songName,String personName);


}
