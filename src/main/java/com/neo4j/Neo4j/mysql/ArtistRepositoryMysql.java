package com.neo4j.Neo4j.mysql;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepositoryMysql extends JpaRepository<ArtistStatistics,String> {
}
