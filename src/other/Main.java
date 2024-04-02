package other;

import Memento.Memento;

import java.util.Scanner;
import java.time.LocalDateTime;

public class Main
{
	private static final Scanner scanner = new Scanner(System.in);
	private static final User user = new User("Gerjan");
	private static final JsonReader reader = new JsonReader();
	private static final JsonWriter writer = new JsonWriter();
	
	public static void main(String[] args)
	{
		System.out.println("Achievement Tracker 1.0");
		System.out.println("Please use one of the following commands (case insensitive):");
		displayCommands(false);
		//Main loop: Scanning for commands.
		String command;
		do
		{
			System.out.print("> ");
			command = scanner.nextLine();
			switch (command.toLowerCase())
			{
				case "newachievement" -> createAchievement();
				case "editachievement" -> editAchievement();
				case "saveachievement" -> saveAchievement();
				case "restoreachievement" -> restoreAchievement();
				case "help" -> displayCommands(true);
				case "exit" -> System.out.println("Exiting program.");
				default -> System.out.println("Unrecognized command. Use 'help' for all available commands.");
			}
		} while (!command.equalsIgnoreCase("exit"));
		System.exit(0);
		Memento memento = new Memento("fortnite","Slash and Dash", "Get 10 kills before reaching 10 players remaining", 10, 3, LocalDateTime.of(2020, 2, 23, 11, 54));
		// writer.writeMementoToJson(memento);
		reader.readMementoFromJson();
	}
	
	private static void createAchievement()
	{
		System.out.println("Please enter the game's name:");
		System.out.print("> ");
		String game = scanner.nextLine();
		System.out.println("Please enter the achievement's title:");
		System.out.print("> ");
		String title = scanner.nextLine();
		System.out.println("Please enter the achievement's description:");
		System.out.print("> ");
		String description = scanner.nextLine();
		System.out.println("Please enter the achievement's total Progress:");
		System.out.print("> ");
		int progress = Integer.parseInt(scanner.nextLine());
		Achievement achievement = new Achievement(game,title, description, progress);
		user.addAchievement(achievement);
		System.out.println("Achievement created succesfully!");
	}
	
	private static void editAchievement()
	{
	
	}
	
	private static void saveAchievement()
	{
	
	}
	
	private static void restoreAchievement()
	{
	
	}
	
	private static void displayCommands(boolean includeFirstLine)
	{
		if (includeFirstLine)
		{
			System.out.println("Available Commands (case insensitive)");
		}
		System.out.println("NewAchievement -> Creates a new Achievement.");
		System.out.println("EditAchievement -> Edits an existing achievement.");
		System.out.println("SaveAchievement -> Saves an achievement as a JSON file.");
		System.out.println("RestoreAchievement -> Restores an achievement.");
		System.out.println("Help -> Displays this dialog.");
		System.out.println("Exit -> Terminates the program. Changes will not be saved.");
	}
}
