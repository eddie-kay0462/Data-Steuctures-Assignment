import java.util.Scanner;

public class PlayListCompleteTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner object to read user input
        FullyFunctionalPlayList playlist = new FullyFunctionalPlayList(); // Create a new FullyFunctionalPlayList
        boolean exit = false; // Flag to control the main loop

        while (!exit) {
            // Display menu options
            System.out.println("\n1. Add Song");
            System.out.println("2. Add Song at Position");
            System.out.println("3. Remove Song by Title");
            System.out.println("4. Remove Song by Position");
            System.out.println("5. Play Next Song");
            System.out.println("6. Play Previous Song");
            System.out.println("7. Shuffle Playlist");
            System.out.println("8. Display Playlist");
            System.out.println("9. Toggle continuous play");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            // Input validation for menu choice
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number (1-10).");   
                scanner.next(); // Consume the invalid input
                System.out.print("Choose an option: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Switch case to handle user choice
            switch (choice) {
                case 1:
                    // Add a song to the end of the playlist
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine().trim();
                    System.out.print("Enter duration in seconds: ");
                    //Ensures the user enter an integer 
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the duration.");
                        scanner.next(); // Consume the invalid input
                        System.out.print("Enter duration in seconds: ");
                    }
                    int duration = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.addSongAtEnd(new Song(title, artist, duration));
                    break;
                case 2:
                    // Add a song at a specific position in the playlist
                    System.out.print("Enter song title: ");
                    String titlePos = scanner.nextLine().trim();
                    System.out.print("Enter artist name: ");
                    String artistPos = scanner.nextLine().trim();
                    System.out.print("Enter duration in seconds: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the duration.");
                        scanner.next(); // Consume the invalid input
                        System.out.print("Enter duration in seconds: ");
                    }
                    int durationPos = scanner.nextInt();
                    System.out.print("Enter position: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the position.");
                        scanner.next(); // Consume the invalid input
                        System.out.print("Enter position: ");
                    }
                    int position = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.addSongAtPosition(new Song(titlePos, artistPos, durationPos), position);
                    break;
                case 3:
                    // Remove a song by its title
                    System.out.print("Enter song title to remove: ");
                    String removeTitle = scanner.nextLine().trim();
                    playlist.removeSongByTitle(removeTitle);
                    break;
                case 4:
                    // Remove a song by its position
                    System.out.print("Enter song position to remove: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Invalid input. Please enter a valid number for the position.");
                        scanner.next(); // Consume the invalid input
                        System.out.print("Enter song position to remove: ");
                    }
                    int removePosition = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    playlist.removeSongByPosition(removePosition);
                    break;
                case 5:
                    // Play the next song in the playlist
                    playlist.playNextSong();
                    break;
                case 6:
                    // Play the previous song in the playlist
                    playlist.playPreviousSong();
                    break;
                case 7:
                    // Shuffle the playlist
                    playlist.shufflePlaylist();
                    System.out.println("Playlist shuffled");
                    break;
                case 8:
                    // Display the playlist
                    System.out.println("----------------------------------------------");
                    playlist.displayPlaylist();
                    break;
                case 9:
                    // Toggle continuous play mode
                    playlist.toggleContinuousPlayMode();
                    break;
                case 10:
                    // Exit the program
                    System.out.println("Exiting...");
                    scanner.close();
                    exit = true;
                    return;
                default:
                    // Handle invalid menu choices
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close(); // Close the scanner
    }
}
