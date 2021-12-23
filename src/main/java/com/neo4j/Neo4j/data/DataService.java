package com.neo4j.Neo4j.data;

import com.neo4j.Neo4j.mysql.*;
import com.neo4j.Neo4j.neo4j.*;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.neo4j.driver.SessionConfig;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.mapping.Neo4jMappingContext;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.neo4j.driver.Values.parameters;


@Service
public class DataService {
    private final ArtistRepository artistRepository;
    private final PersonRepository personRepository;
    private final SongRepository songRepository;
   private final ArtistRepositoryMysql artistRepositoryMysql;
    private final SongRepositoryMysql songRepositoryMysql;
    private final PersonRepositoryMysql personRepositoryMysql;
    private final Neo4jClient neo4jClient;

    private final Driver driver;
    private final GeneralRepositoryMysql generalRepositoryMysql;
    private final DatabaseSelectionProvider databaseSelectionProvider;

    Neo4jMappingContext neo4jMappingContext = null;

    Map<String, Object> map = null;


    public DataService(ArtistRepository artistRepository, PersonRepository personRepository, SongRepository songRepository, ArtistRepositoryMysql artistRepositoryMysql, SongRepositoryMysql songRepositoryMysql, PersonRepositoryMysql personRepositoryMysql, Neo4jClient neo4jClient, Driver driver, GeneralRepositoryMysql generalRepositoryMysql, DatabaseSelectionProvider databaseSelectionProvider) {
        this.artistRepository = artistRepository;
        this.personRepository = personRepository;
        this.songRepository = songRepository;
        this.artistRepositoryMysql = artistRepositoryMysql;
        this.songRepositoryMysql = songRepositoryMysql;
        this.personRepositoryMysql = personRepositoryMysql;

        this.neo4jClient = neo4jClient;
        this.driver = driver;
        this.generalRepositoryMysql = generalRepositoryMysql;
        this.databaseSelectionProvider = databaseSelectionProvider;
    }

   //for artist
    public List getAllArtists() {
        return artistRepository.getAllAuthors();

    }
    public int getArtistCount(){return artistRepository.countArtist();}
    public Artist saveArtist(Artist author) {
        ArtistStatistics artistStatistics=new ArtistStatistics();
        artistStatistics.setArtist_name(author.getName());
        artistStatistics.setNbSongs(0);
        artistStatistics.setNbSubscibedPersons(0);
        artistStatistics.setTopFan(null);
        artistRepositoryMysql.save(artistStatistics);
        GeneralStatistics generalStatistics =generalRepositoryMysql.findById(1).get();
        generalStatistics.setTotalArtists(generalStatistics.getTotalArtists()+1);
        //int compteur_artist=generalRepositoryMysql.findById(0).get().getTotalArtists()+1;
        System.out.println(generalStatistics.getTotalArtists());
       // generalRepositoryMysql.findById(0).get().setTotalArtists(compteur_artist);

        return artistRepository.save(author);

    }



    //here we should update the table of artist_statistics by incrementing the number of musics and the table of the song by adding the name of the artist that sang this song

    public SangRelation addASongtoArtist(String artistName,String songName){
        ArtistStatistics artistStat=artistRepositoryMysql.findById(artistName).get();
        artistStat.setNbSongs(artistStat.getNbSongs()+1);
        artistRepositoryMysql.save(artistStat);
        SongStatistics songStatistics=songRepositoryMysql.findById(songName).get();
        songStatistics.setArtistName(artistName);
        songRepositoryMysql.save(songStatistics);

        return artistRepository.addASongtoArtist(artistName,songName);
    }


    public FullArtist getArtist(String artistName){
        FullArtist fullArtist=new FullArtist(artistRepository.getArtist(artistName), artistRepositoryMysql.findById(artistName).get());

        return fullArtist;
    }




    //for person

    public List getAllPersons() {
        return personRepository.getAllPersons();
    }
    public int getPersonCount(){return personRepository.countPerson();}
    public Person savePerson(Person person) {

        PersonStatistics personStatistics=new PersonStatistics();
        personStatistics.setNbLikedSong(0);
        personStatistics.setNbSubscription(0);
        personStatistics.setUser_name(person.getName());
        personStatistics.setTopArtist(null);
        personRepositoryMysql.save(personStatistics);
        GeneralStatistics generalStatistics =generalRepositoryMysql.findById(1).get();
        generalStatistics.setTotal_Users(generalStatistics.getTotal_Users()+1);

        return personRepository.save(person);
    }



    public String deleteSubscription(String artistName,String personName) { personRepository.deleteSusbcription(artistName,personName);
        PersonStatistics personStatistics=personRepositoryMysql.findById(personName).get();
        personStatistics.setNbSubscription(personStatistics.getNbSubscription()+1);
        personRepositoryMysql.save(personStatistics);
        ArtistStatistics artistStatistics =artistRepositoryMysql.findById(artistName).get();
        artistStatistics.setNbSubscibedPersons(artistStatistics.getNbSubscibedPersons()-1);
        artistRepositoryMysql.save(artistStatistics);
        return "the user "+personName+" has deleted a subsciption to the artist:  "+ artistName;
    }

    //here we should update the table of person_statistics by incrementing the number of subscription and the table of the artist  by incrementing the number of subcribed users
    public SubscriptionRelation addSubscription(String personName,String artistName){

        PersonStatistics personStatistics = personRepositoryMysql.findById(personName).get();
        personStatistics.setNbSubscription(personStatistics.getNbSubscription()+1);
        ArtistStatistics artistStatistics = artistRepositoryMysql.findById(artistName).get();
        artistStatistics.setNbSubscibedPersons(artistStatistics.getNbSubscibedPersons()+1);

        return personRepository.addSubscription(personName,artistName);
    }
    public FullPerson getPerson(String personName){
        FullPerson fullPerson=new FullPerson(personRepository.getPerson(personName),personRepositoryMysql.findById(personName).get());
        return fullPerson ;}




//for songs
  //  public Map<String,String> getSongsAndArtists(){return personRepository.getArtistsSongs();}

    public List<Song> getAllSongs(){
        return songRepository.getAllSongs();
    }

    public Song saveSong(Song song) {

        SongStatistics songStatistics=new SongStatistics();
        songStatistics.setSong_name(song.getTitle());
        songStatistics.setArtistName(null);
        songStatistics.setNbLikedPerson(0);
        songRepositoryMysql.save(songStatistics);
        GeneralStatistics generalStatistics =generalRepositoryMysql.findById(1).get();
        generalStatistics.setTotal_Songs(generalStatistics.getTotal_Songs()+1);
        return songRepository.save(song);
    }



    public String deleteFavorite(String songName,String personName) {

        PersonStatistics personStatistics = personRepositoryMysql.findById(personName).get();
        personStatistics.setNbLikedSong(personStatistics.getNbLikedSong()-1);
        String topArtist= topArtist(personName);
        personStatistics.setTopArtist(topArtist);
        SongStatistics songStatistics =songRepositoryMysql.findById(songName).get();
        songStatistics.setNbLikedPerson(songStatistics.getNbLikedPerson()-1);
        songRepository.deleteFavorite(songName,personName);

        return "the user "+personName+" has deleted the song "+ songName+" from the favorite songs  ";
    }

    public FavoriteRelation favoriteSong(String personName, String songName){
        PersonStatistics personStatistics = personRepositoryMysql.findById(personName).get();
        personStatistics.setNbLikedSong(personStatistics.getNbLikedSong()+1);
        String topArtist= topArtist(personName);
        personStatistics.setTopArtist(topArtist);
        SongStatistics songStatistics =songRepositoryMysql.findById(songName).get();
        songStatistics.setNbLikedPerson(songStatistics.getNbLikedPerson()+1);
        return songRepository.addToFavorite(personName,songName);
    }
    public FullSong getSong(String songName){
        FullSong fullSong=new FullSong(songRepository.getSong(songName),songRepositoryMysql.findById(songName).get());
        return fullSong ;}



    private Session sessionFor(String database) {
        if (database == null) {
            return driver.session();
        }
        return driver.session(SessionConfig.forDatabase(database));
    }

    private String database() {
        return databaseSelectionProvider.getDatabaseSelection().getValue();
    }

    //***********************************for finding the top artist and top fan ********************************

    //this method will be used to determine the top artist of a user (depending of the musics he added to favorite ) so it will be called every time a user add or delete a song from his favorite list
    public String topArtist(String personName) {

        var nodes = new ArrayList<SubscribedArtistforPerson>();

        Map<String, List<SubscribedArtistforPerson>> map;
        try (Session session = sessionFor(database())) {
            var records = session.readTransaction(tx -> tx.run(""
                    + "Match (s:Song)-[r:ADDED_TO_FAVORITE_BY]-> (p:Person{name:$personName}),(a:Artist)-[sa:SANG]->(s)"
                    + "RETURN count(s) AS nbSongs, a.name AS artist" ,  parameters( "personName", personName )

            ).list());
            records.forEach(record -> {
                        var user=new SubscribedArtistforPerson(record.get("artist").asString(),record.get("nbSongs").asInt());
                        nodes.add(user);
            });


        }
        map=  Map.of("nodes", nodes);
        int max=0;
        int index=-1;
        for (int i=0;i<map.get("nodes").size();i++){
            if(map.get("nodes").get(i).getNumberOfSongsforArtist()>max)
            {
                max=map.get("nodes").get(i).getNumberOfSongsforArtist();
                index=i;
            }

        }
        if (index==-1) return "not yet :)" ;
        else
        return map.get("nodes").get(index).getNameArtist();

    }

    //this method will be used to determine the top fan of an artist so it will be called every time a user add or delete a subscription

    public String topFan(String artistName) {

        var nodes = new ArrayList<SubscribedArtistforPerson>();

        Map<String, List<SubscribedArtistforPerson>> map;
        try (Session session = sessionFor(database())) {
            var records = session.readTransaction(tx -> tx.run(""
                    + "Match (s:Song)-[r:ADDED_TO_FAVORITE_BY]-> (p:Person),(a:Artist{name:$artistName})-[sa:SANG]->(s)"
                    + "RETURN count(s) AS nbSongs, p.name AS user" ,  parameters( "artistName", artistName )

            ).list());
            records.forEach(record -> {
                var user=new SubscribedArtistforPerson(record.get("user").asString(),record.get("nbSongs").asInt());
                nodes.add(user);
            });


        }
        map=  Map.of("nodes", nodes);
        int max=0;
        int index=-1;
        for (int i=0;i<map.get("nodes").size();i++){
            if(map.get("nodes").get(i).getNumberOfSongsforArtist()>max)
            {
                max=map.get("nodes").get(i).getNumberOfSongsforArtist();
                index=i;
            }

        }
        if (index==-1) return "not yet :)" ;
        else
        return map.get("nodes").get(index).getNameArtist();

    }
    //**********************************************mysql *****************************************
    public List<ArtistStatistics> getallArtistStatistics(){
        List<ArtistStatistics> artistStatistics = new ArrayList<>();
        artistRepositoryMysql.findAll().forEach(artistStatistics::add);

        return artistStatistics;
    }
    public List getallGeneralstat(){
        return generalRepositoryMysql.findAll();
    }
    public List<SongStatistics> getallSongsgstat(){
        return songRepositoryMysql.findAll();
    }

    public List<PersonStatistics> getallPersonsstat(){
        return personRepositoryMysql.findAll();
    }
    public ArtistStatistics getstatisticsForArtist(){
        return artistRepositoryMysql.findById("BTS").get();
    }
}
