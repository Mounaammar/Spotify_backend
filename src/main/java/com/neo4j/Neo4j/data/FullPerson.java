package com.neo4j.Neo4j.data;

import com.neo4j.Neo4j.mysql.PersonStatistics;
import com.neo4j.Neo4j.neo4j.Person;

public class FullPerson {
    private Person  person;
    private PersonStatistics personStatistics;

    public FullPerson(Person person, PersonStatistics personStatistics) {
        this.person = person;
        this.personStatistics = personStatistics;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonStatistics getPersonStatistics() {
        return personStatistics;
    }

    public void setPersonStatistics(PersonStatistics personStatistics) {
        this.personStatistics = personStatistics;
    }
}
