import java.util.Scanner;

public class BasicPlaylistTest {
    public static void main(String[] args) {
        BasicPlaylist playlist = new BasicPlaylist();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false; //Variable to control the exit condition of the loop

        // Main loop to display menu and perform actions based on user choice
        while (!exit) {
            System.out.println("\n--- Playlist Menu ---");
            System.out.println("1. Add a song to the end of the playlist");
            System.out.println("2. Add a song at a specific position");
            System.out.println("3. Remove a song by title");
            System.out.println("4. Remove a song by position");
            System.out.println("5. Display the playlist");
            System.out.println("6. Calculate the total duration of the playlist");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            
            //Input validation to ensure the user enters a number
            while (!scanner.hasNextInt())
            {
                System.out.println("Invalid input. Please enter a number (1-7)");   
                scanner.next(); //consume the invalid input
            }
            int choice = scanner.nextInt(); //Get the users choice
            scanner.nextLine();  // Consume newline
            String title, artist;
            int duration;
            int position;

            // Switch case to handle the user's choice
            switch (choice) {
                case 1:
                    System.out.print("Enter song title: ");
                    title = scanner.nextLine().trim(); // Get song title
                    System.out.print("Enter song artist: ");
                    artist = scanner.nextLine().trim(); //Get song artist
                    System.out.print("Enter song duration (in seconds): ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the duration (in seconds): ");
                        scanner.next(); // Consume the invalid input
                    }
                    duration = scanner.nextInt();
                    playlist.addSongAtEnd(new Song(title, artist, duration));
                    System.out.println("Song added.");
                    break;
                case 2:
                    System.out.print("Enter song title: ");
                    title = scanner.nextLine().trim();
                    System.out.print("Enter song artist: ");
                    artist = scanner.nextLine().trim();
                    System.out.print("Enter song duration (in seconds): "); 
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the duration (in seconds): ");
                        scanner.next(); // Consume the invalid input
                    }
                    duration = scanner.nextInt();
                    System.out.print("Enter position to add the song: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the position: ");
                        scanner.next(); // Consume the invalid input
                    }
                    position = scanner.nextInt(); // Get the position to add the song
                    playlist.addSongAtPosition(new Song(title, artist, duration), position);
                    break;
                case 3:
                    if(playlist.isPlaylistEmpty())
                    {
                        System.out.println("Play list is empty");
                        continue;
                    }
                    System.out.print("Enter song title to remove: ");
                    title = scanner.nextLine().trim();
                    playlist.removeSongByTitle(title);
                    break;
                case 4:
                    if(playlist.isPlaylistEmpty())
                    {
                        System.out.println("Play list is empty");
                        continue;
                    }
                    System.out.print("Enter position to remove the song: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the position: ");
                        scanner.next(); // Consume the invalid input
                    }
                    position = scanner.nextInt(); // Get the position to add the song
                    playlist.removeSongByPosition(position);
                    break;
                case 5:
                    System.out.println("Displaying playlist:");
                    playlist.displayPlaylist();
                    break;
                case 6:
                    if(playlist.isPlaylistEmpty())
                    {
                        System.out.println("Play list is empty");
                        continue;
                    }
                    System.out.println("Total duration: " + playlist.getTotalDuration() + " seconds");
                    break;
                case 7:
                    exit = true; // Set exit condition to true to exit the loop
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("-----------Exiting playlist manager---------------");
    }
}