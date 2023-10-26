import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
		private ArrayList<AudioContent> contents; 
		public Map<String,ArrayList<Integer>> searchG;
		public Map<String,ArrayList<Integer>> searchN;
		private Map<String, Integer> searchT;
		
		public AudioContentStore() 
		{
			
			try
			{
				//reads contents from file using ReAudio() and stores in contents ArrayList
				contents = ReAudio();
			}
			//Prints message if IO ex is caught
			catch(IOException e)
			{
				System.out.println(e.getMessage());
				System.exit(1);
			}

			// intializes title index hash map
			searchT = new HashMap<>();
			for(int i = 0; i < contents.size(); i++)
			{
					
				searchT.put(contents.get(i).getTitle().toLowerCase(), i);
			}
			
			//intializes Artist/Author storing hashmap
			searchN = new HashMap<>();
			for(int i = 0; i < contents.size(); i++)
			{
				AudioContent content = contents.get(i);
				
				//checks if content is a song
				if(contents.get(i).getType().equals(Song.TYPENAME))
				{
					//casts song to audio content object to call the getArtist method
					String artist = ((Song) content).getArtist().toLowerCase();
					//checks if map contains artist key
					if(searchN.containsKey(artist))
					{
						searchN.get(artist).add(i);
					}
					else
					{
						ArrayList<Integer> ind = new ArrayList<>();
						ind.add(i);
						searchN.put(artist, ind);

					}
				}
				//checks if content is an audiobook
				else if(contents.get(i).getType().equals(AudioBook.TYPENAME))
				{
					String author = ((AudioBook) content).getAuthor().toLowerCase();
					if(searchN.containsKey(author))
					{
						searchN.get(author).add(i);
					}
					else
					{
						ArrayList<Integer> ind = new ArrayList<>();
						ind.add(i);
						searchN.put(author, ind);
					}
					
				}
			}
		
			//initialize genre hash map
			searchG = new HashMap<>();
			for(int i = 0; i < contents.size(); i++)
			{
				AudioContent content = contents.get(i);
				//checks if content is a song
				if(contents.get(i).getType().equals(Song.TYPENAME))
				{
					String genre = ((Song) content).nGetGenre().toLowerCase();
					if(searchG.containsKey(genre))
					{
						searchG.get(genre).add(i);
					}
					else
					{
						ArrayList<Integer> ind = new ArrayList<>();
						ind.add(i);
						searchG.put(genre, ind);
					}
				}
			}
		}

		
		private ArrayList<AudioContent> ReAudio() throws FileNotFoundException
		{
			ArrayList<AudioContent> tempCon = new ArrayList<>();

			File teFile = new File("store.txt");
			Scanner in = new Scanner(teFile);
			

			while(in.hasNextLine())
			{
				//checks for content identifier
				String contentID = in.nextLine();
				if(contentID.equals("SONG"))
				{
					System.out.println("Loading " + contentID);
					
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());
					String artist = in.nextLine();
					String composer = in.nextLine();
					Song.Genre genre = Song.Genre.valueOf(in.nextLine());
					int numLyrics = Integer.parseInt(in.nextLine());
					String lyrics = "";
					//will loops the number of lyrics given and inputs eachline lyrics string
					for(int i = 0; i<numLyrics; i++)
					{
						lyrics += in.nextLine() + "\n";
						
					}
					//creates instance of song to add to arraylist
					Song song = new Song(title, year, id, Song.TYPENAME, "store.txt", length, artist, composer, genre, lyrics);
					tempCon.add(song);
				}
				
				//checks if contentid is an audiobook
				if(contentID.equals("AUDIOBOOK"))
				{
					System.out.println("Loading " + contentID);
					String id = in.nextLine();
					String title = in.nextLine();
					int year = Integer.parseInt(in.nextLine());
					int length = Integer.parseInt(in.nextLine());
					String author = in.nextLine();
					String narrator = in.nextLine();
					int numChaps = Integer.parseInt(in.nextLine());
					ArrayList<String> chapNames =  new ArrayList<>();
					
					//loops the number of chapters
					for(int i = 0; i<numChaps; i++)
					{
						//adds read chapter  names to arraylist
						chapNames.add(in.nextLine());
					}

					//lines of the chapters
					ArrayList<String> chapLines = new ArrayList<>();
					//loops each of chapters in arraylist
					for(int i = 0; i < chapNames.size(); i++)
					{
						String content = "";
						int numChapLines = Integer.parseInt(in.nextLine());
						//adds chapter line to content of each chapter
						for(int j = 0; j < numChapLines; j++)
						{
							content += in.nextLine() + "\n";
							
						}
						chapLines.add(content);
						
					}
					AudioBook book = new AudioBook(title, year, contentID, id, title, length, author, narrator, chapNames, chapLines);
					tempCon.add(book);

				}
			}
			//closes reader
			in.close();
			return tempCon;
		} 
		//searches for titles uses index title map to get int value
		public void stitle(String title)
		{
			Integer ind = searchT.get(title.toLowerCase());
			//throws an ex if ind is found null
			if(ind == null){throw new AudioContentNotFoundException(title + " not found.");}
			System.out.println((ind.intValue() + 1) + ". ");
			contents.get(ind.intValue()).printInfo();
		}

		//searches for artists; will return arraylist of songs from inputted artist
		public void sartist(String artist)
		{
			ArrayList<Integer> ind = searchN.get(artist.toLowerCase());
			
		
			if(ind == null){throw new AudioContentNotFoundException(artist + " not found.");}
			for(int i = 0; i < ind.size(); i++)
			{
				System.out.print((ind.get(i) + 1) + ". ");
				contents.get(ind.get(i)).printInfo();
				System.out.println();
			}
			
		}
		//searches for song genre; will return arraylist of songs from inputted genre
		public void sgenre(String genre)
		{
			ArrayList<Integer> ind = searchG.get(genre.toLowerCase());
			if(ind == null){throw new AudioContentNotFoundException(genre + " not found.");}
			for(int i = 0; i < ind.size(); i++)
			{
				System.out.print((ind.get(i) + 1) + ". ");
				contents.get(ind.get(i)).printInfo();
				System.out.println();
			}

		}

		//searches for all instances of the target string though various forms ignores cases
		public ArrayList<AudioContent> sall(String target) 
		{
			target = target.toLowerCase();
			
			ArrayList<AudioContent> acontents = new ArrayList<>();

			for (int i = 0; i < contents.size(); i++) 
			{
				
				//gets song properties from song/audiocontent class
				if(contents.get(i).getType().equals(Song.TYPENAME))
				{
					String artist = ((Song)contents.get(i)).getArtist();
					String composer = ((Song)contents.get(i)).getComposer();
					String lyrics = ((Song)contents.get(i)).getLyrics();
					String title = contents.get(i).getTitle();
					String audiofile = contents.get(i).getAudioFile();
					
					
					//checks for instances of target string in either property
					if(title.toLowerCase().contains(target) || audiofile.toLowerCase().contains(target) || lyrics.toLowerCase().contains(target) ||artist.toLowerCase().contains(target) || composer.toLowerCase().contains(target))
					{
						Song song = (Song)contents.get(i);
						
						//checks if property contains target
						// sets the matching value for each property
						if (artist.toLowerCase().contains(target)) 
						{
							song.setMatch("Artist", artist);
						}
						if (composer.toLowerCase().contains(target)) 
						{
							song.setMatch("Composer", composer);
						}
						if (lyrics.toLowerCase().contains(target)) 
						{
							song.setMatch("Lyrics", lyrics);
						}
						if (title.toLowerCase().contains(target))
						{
							song.setMatch("Title", title);
						}
						if (audiofile.toLowerCase().contains(target))
						{
							song.setMatch("AudioFile", title);
						}

						//adds song to list of matching contents
						acontents.add(contents.get(i));
					}
						
				}
				//gets audiobook properties from audiobook/audiocontent class
				else if (contents.get(i).getType().equals(AudioBook.TYPENAME))
				{
		
					String narrator = ((AudioBook)contents.get(i)).getNarrator();
					String author = ((AudioBook)contents.get(i)).getAuthor();
					ArrayList<String> chapterTitles = ((AudioBook)contents.get(i)).getChapterTitles();
					ArrayList<String> chapters = ((AudioBook)contents.get(i)).getChapters();
					String title = contents.get(i).getTitle();
					String audiofile = contents.get(i).getAudioFile();
					

					// checks for target string in instances of chapter titles
					
					for(int c = 0; c < chapterTitles.size(); c++)
					{
						if(chapterTitles.get(c).toLowerCase().contains(target))
						{
							
							AudioBook book = (AudioBook)contents.get(i);
							// sets the matching value for the "Chapters" property
							book.setMatch("Chapter Title", chapterTitles.get(c));
							// adds the audiobook to the list of matching contents
							acontents.add(contents.get(i));
							break;
							
						}
					}
					
					for(int ci = 0; ci < chapters.size(); ci++)
					{
						if(chapters.get(ci).toLowerCase().contains(target))
						{
							
							AudioBook book = (AudioBook)contents.get(i);
							book.setMatch("Chapters", chapters.get(ci));
							
							acontents.add(contents.get(i));
							break;
							
						}
							
					}

					if (narrator.toLowerCase().contains(target) || author.toLowerCase().contains(target) || audiofile.toLowerCase().contains(target) || title.toLowerCase().contains(target))
					{
						AudioBook book = (AudioBook)contents.get(i);

						//sets matching values for audiobook properties
						if (narrator.toLowerCase().contains(target)) 
						{
							book.setMatch("Narrator", narrator);
						}
						if (author.toLowerCase().contains(target)) 
						{
							book.setMatch("Author", author);
						}
						if (audiofile.toLowerCase().contains(target)) 
						{
							book.setMatch("AudioFile", audiofile);
						}
						if (title.toLowerCase().contains(target))
						{
							book.setMatch("Title", title);
						}
						
						acontents.add(contents.get(i));
					}


				}
			
			}
			// if no target matches any of the content (list is empty), an exception is thrown
			if(acontents.isEmpty())
				throw new AudioContentNotFoundException(target + " not found.");

			//arraylist to be downloaded
			return acontents;
		}
			
		
		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print(index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		
		
		
		
		
		
}
