package com.neo4j.Neo4j.neo4j;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, String> {

  //  @Query("match (s:Song)-[r:ADDED_TO_FAVORITE_BY]->(p:Person{name:\"Mouna\"}),(a:Artist)-[:SANG]->(s) return a.name as artistName,count(r) as nbOfSongs")
  //  Collection<SubscribedArtistforPerson> topArtist(String personName);
    @Query("MATCH (a:Artist) ,(p:Person) where a.name=$artistName AND p.name=$personName CREATE (p)-[stu:SUBSCRIBED_TO] -> (a)")
    SubscriptionRelation addSubscription( String personName , String artistName );

    @Query("MATCH (p:Person) RETURN p")
    List<Person> getAllPersons();
    @Query("MATCH (p:Person{name:$personName}) RETURN p")
    Person getPerson(String personName);
    @Query("MATCH (p:Person) RETURN count(p) ")
    int countPerson();
    @Query("MATCH (a:Artist{name:$artistName})<- [r:SUBSCRIBED_TO]-(p:Person{name:$personName}) delete(r)")
    String deleteSusbcription(String artistName,String personName);
   // @Query("Match (s:Song)-[r:ADDED_TO_FAVORITE_BY]-> (p:Person{name:$personName}),(a:Artist)-[sa:SANG]->(s) return a.name as nameArtist,count(s) as numberOfSongsforArtist")
   // Collection<SubscribedArtistforPerson> topArtist(String personName);
  @Query("Match (a:Artist)-[r:SANG]->(s:Song) return a,s")
  Map<String,String> getArtistsSongs();

}

