package com.neo4j.Neo4j.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArtistRepository extends Neo4jRepository<Artist, String> {

    //@Query("MATCH (a:Artist)<-[sub:SUBSCRIBED_TO]-(p:Person),(a)-[:SANG]->(s:Song) RETURN sub,a,collect(p),s")
    @Query("MATCH (a:Artist) RETURN a")
    List<Artist> getAllAuthors();
    @Query("MATCH (a:Artist{name:$artistName}) RETURN a")
    Artist getArtist(String artistName);
    @Query("MATCH (a:Artist) RETURN count(a) ")
    int countArtist();
    @Query("MATCH (a:Artist) ,(s:Song) where a.name=$artistName AND s.title=$songName CREATE (a)-[stu:SANG] -> (s)")
    SangRelation addASongtoArtist( String artistName , String  songName);

    @Query("MATCH (s:Song{name:$songName})<- [r:SANG] -(a:Artist{name:$artistName}) delete(r)")
    String deleteSong(String songName,String artistName);

}