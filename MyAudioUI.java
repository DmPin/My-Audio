import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
    public static void main(String[] args)
    {
        // Simulation of audio content in an online store
        // The songs, podcasts, audiobooks in the store can be downloaded to your library
        AudioContentStore store = new AudioContentStore();
        
        // Create my music library
        Library library = new Library();

        Scanner scanner = new Scanner(System.in);
        System.out.print(">");

        // Process keyboard actions
        while (scanner.hasNextLine())
        {
            String action = scanner.nextLine();

            if (action == null || action.equals("")) 
            {
                System.out.print("\n>");
                continue;
            }
            else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
                return;
            
            else if (action.equalsIgnoreCase("STORE"))  // List all songs
            {
                store.listAll(); 
            }
            else if (action.equalsIgnoreCase("SONGS"))  // List all songs
            {
                library.listAllSongs(); 
            }
            else if (action.equalsIgnoreCase("BOOKS"))  // List all songs
            {
                library.listAllAudioBooks(); 
            }
            else if (action.equalsIgnoreCase("PODCASTS"))   // List all songs
            {
                library.listAllPodcasts(); 
            }
            else if (action.equalsIgnoreCase("ARTISTS"))    // List all songs
            {
                library.listAllArtists(); 
            }
            else if (action.equalsIgnoreCase("PLAYLISTS"))  // List all play lists
            {
                library.listAllPlaylists(); 
            }

            // try
            // {        
                else if (action.equalsIgnoreCase("DOWNLOAD")) 
                {
                    //prints starting value
                    int fromInd = 0;
                    System.out.print("From Store Content #: ");
                    if(scanner.hasNextInt())
                    {
                        fromInd = scanner.nextInt();
                    }
                    else
                    {
                        System.out.println("Range is invalid.");
                        continue;
                    }    
                        

                    
                    //prints end value
                    System.out.print("To Store Content #: ");
                    int toInd = 0;
                    if(scanner.hasNextInt())
                    {
                        toInd = scanner.nextInt();
                    }
                    else
                        System.out.println("Range is invalid."); 
                
                    ArrayList<AudioContent> temp = new ArrayList<>();
                
                    try 
                    {
                        for (int i = fromInd; i <= toInd; i++) 
                        {
                            if (store.getContent(i) == null) 
                            {
                                //checks if null with print custom message
                                System.out.println("Range is invalid.");
                            } 
                            else 
                            {
                                temp.add(store.getContent(i));
                            }
                        }
                
                        library.download(temp);
                    } 
                    catch (ContentNotInStoreEx e) 
                    {
                        System.out.println(e.getMessage());
                    }
                }
                

                    
                                    
            
            try
            {   
                if (action.equalsIgnoreCase("PLAYSONG")) 
                {
                    int index = 0;

                    System.out.print("Song Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                    // consume the nl character since nextInt() does not
                        scanner.nextLine(); 
                    }
                    library.playSong(index);
                        
                }
                else if (action.equalsIgnoreCase("BOOKTOC")) 
                {
                    int index = 0;

                    System.out.print("Audio Book Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine();
                    }
                    library.printAudioBookTOC(index);
                        
                }
                else if (action.equalsIgnoreCase("PLAYBOOK")) 
                {
                    int index = 0;

                    System.out.print("Audio Book Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                    }
                    int chapter = 0;
                    System.out.print("Chapter: ");
                    if (scanner.hasNextInt())
                    {
                        chapter = scanner.nextInt();
                        scanner.nextLine();
                    }
                    library.playAudioBook(index, chapter);
                        
                }
                
                else if (action.equalsIgnoreCase("PODTOC")) 
                {
                    int index = 0;
                    int season = 0;
                    
                    System.out.print("Podcast Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                    }
                    System.out.print("Season: ");
                    if (scanner.hasNextInt())
                    {
                        season = scanner.nextInt();
                        scanner.nextLine();
                    }
                    library.printPodcastEpisodes(index, season);
                            
                }
                else if (action.equalsIgnoreCase("PLAYPOD")) 
                {
                    int index = 0;

                    System.out.print("Podcast Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine();
                    }
                    int season = 0;
                    System.out.print("Season: ");
                    if (scanner.hasNextInt())
                    {
                        season = scanner.nextInt();
                        scanner.nextLine();
                    }
                    int episode = 0;
                    System.out.print("Episode: ");
                    if (scanner.hasNextInt())
                    {
                        episode = scanner.nextInt();
                        scanner.nextLine();
                    }
                    library.playPodcast(index, season, episode);
                        
                }
                else if (action.equalsIgnoreCase("PLAYALLPL")) 
                {
                    String title = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                    {
                        title = scanner.nextLine();
                    }
                    library.playPlaylist(title);
                        
                }
                else if (action.equalsIgnoreCase("PLAYPL")) 
                {
                    String title = "";
                    int index = 0;
            
                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                    {
                        title = scanner.nextLine();
                    }
                    System.out.print("Content Number: ");
                    if (scanner.hasNextInt())
                    {
                        index = scanner.nextInt();
                        scanner.nextLine();
                    }
                    library.playPlaylist(title, index);
                        
                }
                // Delete a song from the library and any play lists it belongs to
                else if (action.equalsIgnoreCase("DELSONG")) 
                {
                    int songNum = 0;

                    System.out.print("Library Song #: ");
                    if (scanner.hasNextInt())
                    {
                        songNum = scanner.nextInt();
                        scanner.nextLine();
                    }
                    
                    library.deleteSong(songNum);
                    
                }
                else if (action.equalsIgnoreCase("MAKEPL")) 
                {
                    String title = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                    {
                        title = scanner.nextLine();
                    }
                    library.makePlaylist(title);
                        
                }
                else if (action.equalsIgnoreCase("PRINTPL"))    // print playlist content
                {
                    String title = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                        title = scanner.nextLine();

                    library.printPlaylist(title);
                        
                }
                // Add content from library (via index) to a playlist
                else if (action.equalsIgnoreCase("ADDTOPL")) 
                {
                    int contentIndex = 0;
                    String contentType = "";
                    String playlist = "";
            
                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                        playlist = scanner.nextLine();
            
                    System.out.print("Content Type [SONG, PODCAST, AUDIOBOOK]: ");
                    if (scanner.hasNextLine())
                        contentType = scanner.nextLine();
                    
                    System.out.print("Library Content #: ");
                    if (scanner.hasNextInt())
                    {
                        contentIndex = scanner.nextInt();
                        scanner.nextLine(); // consume nl
                    }
                    
                    library.addContentToPlaylist(contentType, contentIndex, playlist);
                        
                }
                // Delete content from play list
                else if (action.equalsIgnoreCase("DELFROMPL")) 
                {
                    int contentIndex = 0;
                    String playlist = "";

                    System.out.print("Playlist Title: ");
                    if (scanner.hasNextLine())
                        playlist = scanner.nextLine();
                    
                    System.out.print("Playlist Content #: ");
                    if (scanner.hasNextInt())
                    {
                        contentIndex = scanner.nextInt();
                        scanner.nextLine(); // consume nl
                    }
                    library.delContentFromPlaylist(contentIndex, playlist);
                            
                }
                else if(action.equalsIgnoreCase("SEARCH"))
                {
					System.out.print("Enter Title: ");
					String title = scanner.nextLine();
                    //calls stitle method to search store for inputted title
					store.stitle(title);
					
                }
                //searches audio contents by artist
                else if(action.equalsIgnoreCase("SEARCHA"))
                {
                    System.out.print("Artist: ");
                    String artist = ""; 
                    if(scanner.hasNextLine())
                    {
                        artist = scanner.nextLine();
                    }
                    store.sartist(artist);
                    
                    
                }
                else if(action.equalsIgnoreCase("SEARCHG"))
                {
                    System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]: ");
                    String genre = "";

                     //Gets array list of audio content to print by searching the store for the inputted genre
                     if(scanner.hasNextLine())
                     {
                         genre = scanner.nextLine();
                     }
                     store.sgenre(genre);
                   
                    
                }
                else if(action.equalsIgnoreCase("DOWNLOADA"))
                {
                    
                    //downloads objects from the arraylist of audio content
                    System.out.print("Artist: ");
                    String artist = scanner.nextLine();
                    try
                    {
                        ArrayList<Integer> temp = store.searchN.get(artist.toLowerCase());
                        
                        for(Integer i: temp)
                        {
                            //downloads specified artist's arraylist and gets list of their songs
                            ArrayList<AudioContent> art = new ArrayList<>();
                            art.add(store.getContent(i+1));
                            
                            library.download(art);
                        }
                    }
                    catch(NullPointerException eqqq)
                    {
                        System.out.println(artist + " not found");
                    }
                    // library.download(temp);
                }
                else if(action.equalsIgnoreCase("DOWNLOADG"))
                {
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    try
                    {  
                        //retrives arraylist from map for store objects
                        //keys are genres and value is arraylist of content
                        ArrayList<Integer> temp = store.searchG.get(genre.toLowerCase());
                    
                        for(Integer i: temp)
                        {
                            //downloads specified arraylist and gets list of its contents
                            ArrayList<AudioContent> gen = new ArrayList<>();
                            gen.add(store.getContent(i+1));
                            
                            library.download(gen);
                        }
                    }
                    catch(NullPointerException eqq)
                    {
                        System.out.println(genre + " not found.");
                    }
                        
                }
				else if (action.equalsIgnoreCase("SEARCHP"))
				{
                    
					// Get string to search for from user
					System.out.print("All Instances of: ");
					String target = scanner.nextLine();

                    // stores search results on target text by calling the sall() method
                    // searches the matched audio content in the arraylist
                    //calls print method on each object
					
					ArrayList<AudioContent> temp = store.sall(target);

					for(int i = 0; i < temp.size(); i++)
                    {
                        temp.get(i).printSearch();
                        
                    }
				}

            }
            // If any Exceptions are caught, they will print following messages
            catch(AudioContentNotFoundException e)
            {
                System.out.println(e.getMessage());
            }
            catch(DuplicatePlaylistException e2)
            {
                System.out.println(e2.getMessage());
            }
            catch(InvalidDownloadException e3)
            {
                System.out.println(e3.getMessage());
            }
            catch(PlaylistNotFoundException e4)
            {
                System.out.println(e4.getMessage());
            }
           
            if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
            {
                library.sortSongsByYear();
            }
            else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
            {
                library.sortSongsByName();
            }
            else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
            {
                library.sortSongsByLength();
            }

            System.out.print("\n>");
        }
    }
}

