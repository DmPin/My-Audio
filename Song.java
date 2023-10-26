/*
 * A Song is a type of AudioContent. A Song has extra fields such as Artist (person(s) singing the song) and composer 
 */
import java.util.HashMap;
public class Song extends AudioContent implements Comparable<Song>
{
	public static final String TYPENAME =	"SONG";
	
	public static enum Genre {POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL}; 
	private String artist; 		// Can be multiple names separated by commas
	private String composer; 	// Can be multiple names separated by commas
	private Genre  genre; 
	private String lyrics;
	private HashMap<String, String> matchedProp;
	
	
	
	public Song(String title, int year, String id, String type, String audioFile, int length, String artist,
			        String composer, Song.Genre genre, String lyrics)
	{
		super(title, year, id, type, audioFile, length);
		this.artist = artist;
		this.composer = composer;
		this.genre = genre;
		this.lyrics = lyrics;
		this.matchedProp = new HashMap<>();
	}
	
	public String getType()
	{
		return TYPENAME;
	}
	
	// Print information about the song. First print the basic information of the AudioContent 
	// by calling the superclass method and then print Artist, composer, Genre 
	public void printSearch() 
	{
		//calls print method from AudioContent for formatting of bonus method
		super.printSearch();
		System.out.print(artist.toString() + " and composed by " + composer + "\n");
	
		for (String key : matchedProp.keySet()) 
		{
			System.out.print(" Found in [" + key + "]: " + matchedProp.get(key) + "\n");
		}
		//clears content of map so old search results are stored and reprinted
		matchedProp.clear();
	}
	

	public void printInfo()
	{
		super.printInfo();
		System.out.println("Artist: " + artist.toString() + " Composer: " + composer + " Genre: " + genre);
	}
	
	// Play the song by setting the audioFile to the lyrics string and calling the play() method of the superclass
	public void play()
	{
		setAudioFile(lyrics);
		super.play();
	}
	
	public String getComposer()
	{
		return composer;
	}
	
	public void setComposer(String composer)
	{
		this.composer = composer;
	}
	
	public String getArtist()
	{
		return artist;
	}
	public void setArtist(String artist)
	{
		this.artist = artist;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}

	public Genre getGenre()
	{
		return genre;
	}

	public String nGetGenre()
	{
		return genre.toString();
	}

	public void setGenre(Genre genre)
	{
		this.genre = genre;
	}	
	
	// Two songs are equal if their AudioContent information is equal and both the composer and artists are the same
	public boolean equals(Object other)
	{
		Song otherSong = (Song) other;
		return super.equals(other) && composer.equals(otherSong.composer) && artist.equals(otherSong.artist);
	}
	
	// Compare two songs based on their title
	// This method will allow songs to be sorted alphabetically
	public int compareTo(Song other)
	{
		return this.getTitle().compareTo(other.getTitle());
	}
	
	//Sets the matched property's key and value in matchedProp map
    public void setMatch(String property, String value) 
	{
		matchedProp.put(property, value);
	}
}
