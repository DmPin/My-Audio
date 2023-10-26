import java.util.ArrayList;
import java.util.HashMap;
/*
 * An AudioBook is a type of AudioContent.
 * It is a recording made available on the internet of a book being read aloud by a narrator
 * 
 */
public class AudioBook extends AudioContent
{
	public static final String TYPENAME =	"AUDIOBOOK";
	
	private String author; 
	private String narrator;
	private ArrayList<String> chapterTitles;
	private ArrayList<String> chapters;
	private int currentChapter = 0;
	private HashMap<String, String> matchedProp;
	


	public AudioBook(String title, int year, String id, String type, String audioFile, int length,
									String author, String narrator, ArrayList<String> chapterTitles, ArrayList<String> chapters)
	{
		super(title, year, id, type, audioFile, length);
		this.author = author;
		this.narrator = narrator;
		this.chapterTitles = chapterTitles;
		this.chapters = chapters;
		this.matchedProp = new HashMap<>();
		

	}
	
	public String getType()
	{
		return TYPENAME;
	}
	public void printSearch()
	{
		//calls custom print method for searchall bonus
		super.printSearch();
		System.out.print(author + " and narrated by " + narrator + "\n");

		//searches keys in in map to print out the value associated with it
		for (String key : matchedProp.keySet()) 
		{
			System.out.print(" Found in [" + key + "]: " + matchedProp.get(key) + "\n");
		}
		
		
		//clears the maps so the old values get reset before a new search
		matchedProp.clear();
		
	}

	public void printInfo()
	{
		super.printInfo();
		System.out.println("Author: " + author + " Narrated by: " + narrator);
	}
	
	public void play()
	{
		setAudioFile("Chapter " + currentChapter+1 + ". " + chapterTitles.get(currentChapter) + ".\n" + chapters.get(currentChapter));
		super.play();
	}
	
	// Print the Table of Contents of a Book
	public void printTOC()
	{
		for (int i = 0; i < chapterTitles.size(); i++)
		{
			String title = chapterTitles.get(i);
			int ch = i + 1;
			System.out.println("Chapter " + ch + ". " + title + "\n");
		}
	}

	public void selectChapter(int chapter)
	{
		if (chapter >= 1 && chapter <= chapters.size())
		{
			currentChapter = chapter - 1;
		}
	}
	
	//Two AudioBooks are equal if their AudioContent information is equal and both the author and narrators are equal
	public boolean equals(Object other)
	{
		AudioBook book = (AudioBook) other;
		return super.equals(other) && author.equals(book.author) && narrator.equals(book.narrator); 
	}
	
	public int getNumberOfChapters()
	{
		return chapters.size();
	}

	public String getAuthor()
	{
		return author;
	}

	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getNarrator()
	{
		return narrator;
	}

	public void setNarrator(String narrator)
	{
		this.narrator = narrator;
	}

	public ArrayList<String> getChapterTitles()
	{
		return chapterTitles;
	}

	public void setChapterTitles(ArrayList<String> chapterTitles)
	{
		this.chapterTitles = chapterTitles;
	}

	public ArrayList<String> getChapters()
	{
		return chapters;
	}

	public void setChapters(ArrayList<String> chapters)
	{
		this.chapters = chapters;
	}
	//Sets the matched property's key and value in matchedProp map
    public void setMatch(String property, String value) 
	{
		matchedProp.put(property, value);
	}
	
	

}
