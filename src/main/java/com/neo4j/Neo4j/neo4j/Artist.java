package com.neo4j.Neo4j.neo4j;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
public class Artist {
    @Id
    private final String name;
    private final String picture ;

    @Relationship(type = "SUBSCRIBED_TO", direction = Relationship.Direction.INCOMING)
    private final List<Person> persons;
    public Artist(String name, String picture, List<Person> persons) {
        this.name = name;
        this.picture = picture;
        this.persons= persons;
    }

    public String getName() {
        return name;
    }

    public String getPicture() {
        return picture;
    }

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", persons=" + persons +
                '}';
    }
}
