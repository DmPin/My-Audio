/*
 *  Audio Content contains information common to all types of audio (e.g. songs, audiobooks etc)
 */
import java.util.HashMap;

abstract public class AudioContent
{
	public static enum Type {SONG, PODCAST, AUDIOBOOK};
	
	private String title;			
	private int year; 				// year published
	private String id;				// id
	private String type;			// 
	private String audioFile; // file containing the audio content - e.g. a song.
	private int length; 			// minutes
	// private HashMap<String, String> matchingProperties;
	
	public AudioContent(String title, int year, String id, String type, String audioFile, int length)
	{
		this.title = title;
		this.year = year;
		this.id = id;
		this.type = type;
		this.audioFile = audioFile;
		this.length = length;
		// this.matchingProperties = new HashMap<>();
	}

	// Subclasses define their type (e.g. "Song") 
	abstract public String getType();

	// Print Information 

	//custom print method for bonus method
  public void printSearch()
  {
	if(type.equals("SONG"))
		System.out.print(type + ": " +  "\"" + title + "\"" + " by ");
	else
		System.out.print(id + ": " + "\"" + title + "\"" + " by ");

  }

  public void printInfo()
  {
    System.out.println("Title: " + title + " Id: " + id + " Year: " + year + " Type: " + type + " Length: " + length);	 
  }
	
  // Play the content via the audio file
	public void play()
	{
		this.printInfo();
		// Simulate playing of the audio file. For example, for a song this would be printing the lyrics
		System.out.println("\n" + audioFile);
	}
	
	// Two AudioContent objects are equal if they have the same title and id
	public boolean equals(Object other)
	{
		AudioContent otherCon = (AudioContent) other;
		return title.equals(otherCon.title) && id.equals(otherCon.id);
	}
  
	public String getAudioFile()
	{
		return this.audioFile;
	}

	public void setAudioFile(String file)
	{
		this.audioFile = file;
	}

	public int getLength()
	{
		return this.length;
	}

	public void setLength(int length)
	{
		this.length = length;
	}

	public String getTitle()
	{
		return this.title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getYear()
	{
		return this.year;
	}

	public void setYear(int year)
	{
		this.year = year;
	}

	public String getId()
	{
		return this.id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
	// public HashMap<String, String> getMatchingProperties()
    // {
    //     return matchingProperties;
    // }

    // public void setMatchingProperties(String property, String value) {
	// 	this.matchingProperties.put(property, value);
	// }

}
