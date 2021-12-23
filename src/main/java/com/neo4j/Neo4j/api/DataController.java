package com.neo4j.Neo4j.api;


import com.neo4j.Neo4j.data.*;
import com.neo4j.Neo4j.mysql.ArtistStatistics;
import com.neo4j.Neo4j.mysql.PersonStatistics;
import com.neo4j.Neo4j.mysql.SongStatistics;
import com.neo4j.Neo4j.neo4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class DataController {


    @Autowired
    private DataService dataService;
    //*********************methods for artists ***************************************
    //return an artist given his name
    @GetMapping("/artist")
    public FullArtist getArtist(@RequestParam String artistName) {
        return dataService.getArtist(artistName);
    }
    //add a new artist
    @PostMapping("/artists/add")
    public Artist addAuthor(@RequestBody Artist artist) {
        return dataService.saveArtist(artist);

    }
    //return all artists
    @GetMapping("/artists")
    public Collection getAllArtist() {
        return dataService.getAllArtists();
    }

    //add a song to the artist( relationship between artist and a song) given the name of the artist and the name song
    @PostMapping("/artists/addRelation")
    public SangRelation addASongtoArtist(@RequestParam String artistName, @RequestParam String songName){
        return dataService.addASongtoArtist(artistName,songName);
    }


    // ****************** methods for persons***********************

    //return all artists
    @GetMapping("/persons")
    public Collection getAllPersons() {
        return dataService.getAllPersons();
    }

    //return a person given his name
   // @GetMapping("/person")
  //  public Person getPerson( @RequestParam String personName) {
 //       return dataService.getPerson(personName);
 //   }
    //add a person
    @PostMapping("/persons/add")
    public Person addPerson(@RequestBody Person person) {
        return dataService.savePerson(person);
//here we should update the table of general_statistics by incrementing the total number of persons
        // and the table of persons_statistics by ading a new person in the table with a null value for the other attributes

    }
    //for a person who want to susbcribe to an artist given the name of the artist and the name of the person
    @PostMapping("/persons/addSubscription")
    public SubscriptionRelation addSubscription(@RequestParam String personName, @RequestParam String artistName) {
        return dataService.addSubscription(personName, artistName);

    }

    //another way to return the infos of a person given his name
    @GetMapping("/person")
    public FullPerson getPersonByName(@RequestParam String personName) {

        return  dataService.getPerson(personName);

    }

    // a person want to delete a sbuscription
    @DeleteMapping("/person/deleteSubscription")
    public String deletePersonSubscription(@RequestParam String personName,@RequestParam String artistName) {
       return  dataService.deleteSubscription(artistName,personName);
        //here we should update the table of general_statistics (if an artist is sill the top artist)
        // and the table of persons_statistics by decrementing the number of subscription and the table of artist by decrementing the number of susbscibed persons
    }

    //@GetMapping("/persons/topartist")
    //public Optional<SubscribedArtistforPerson> topArtist(@RequestParam String personName){
      //  return dataService.topArtist(personName);
    //}

    //*************************methods for songs*******************************

    //get all songs
    @GetMapping("/songs")
    public Collection getAllSongs() {
        return dataService.getAllSongs();
    }

    //return a song given it's title
    @GetMapping("/song")
    public FullSong getSong(@RequestParam String songName) {
        return dataService.getSong(songName);
    }
    //add a song
    @PostMapping("/songs/add")
    public Song addSong(@RequestBody Song song) {
        return dataService.saveSong(song);
    //here we should update the table of general_statistics by incrementing the total number of songs
        // and the table of songs_statistics by ading a new song in the table with a null value for the other attributes

    }
    //when a person want to add a song as a favorite song given the name of the person and the title of the song
    @PostMapping("/songs/favoriteSong")
    public FavoriteRelation favoriteSong(@RequestParam String personName, @RequestParam String songName) {
        return dataService.favoriteSong(personName, songName);
        //here we should update the table of songs_statistics by incrementing the number of liked people and the table of the person  by incrementing the number of liked musics
    // we should check which one is the top song so if there is a new top song we should update  the table of general_statistics to put the new top song
    }

    //another way to return the infos of a song
    //@GetMapping("/song")
   // public Optional<Song> getSongByName(@RequestParam String songName) {

    //    return  dataService.findSongById(songName);

  //  }

    //a person that has deleted a song from hsi favorite songs
    @DeleteMapping("/songs/delete")
    public String deleteFavorite(@RequestParam String songName,@RequestParam String personName) {
       return dataService.deleteFavorite(songName,personName);
        //here we should update the table of general_statistics (if a song is still the top song)
        // and the table of songs_statistics by decrementing the number of liked users and the table of person by decrementing the number of liked musics
    }

    //***************************************************** just for testing**********************************

    @GetMapping("/topArtist")
    public String topFan(@RequestParam String personName) {
        return dataService.topFan(personName);
    }
   //*********************mysql*******************
   @GetMapping("/artist_statistics")
   public Collection<ArtistStatistics> artistStatistics() {
       System.out.println("**********" + dataService.getallArtistStatistics().get(0));return dataService.getallArtistStatistics();
   }

    @GetMapping("/general_statistics")
    public Collection generalStatistics() {
        return dataService.getallGeneralstat();
    }
    @GetMapping("/song_statistics")
    public  @ResponseBody Collection<SongStatistics> songStatistics() {
        return dataService.getallGeneralstat();
    }
    @GetMapping("/person_statistics")
    public Collection<PersonStatistics> personStatistics() {
        return dataService.getallPersonsstat();
    }

    @GetMapping("/one_artist_statistics")
    public ArtistStatistics getstatisticsForArtist(){
        return dataService.getstatisticsForArtist();
    }
}
