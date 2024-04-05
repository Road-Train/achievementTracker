package other;

import FactoryMethod.*;
import Memento.*;
import Observer.FriendUser;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main
{
	private static final Scanner scanner = new Scanner(System.in);
	private static final User user = new User("Gerjan");
	private final static PositiveFriendUserFactory positiveFriendUserFactory = new PositiveFriendUserFactory();
	private final static NegativeFriendUserFactory negativeFriendUserFactory = new NegativeFriendUserFactory();
	private final static NonActiveFriendUserFactory nonActiveFriendUserFactory = new NonActiveFriendUserFactory();
	
	public static void main(String[] args)
	{
		initialize();
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
				case "importachievement" -> importAchievement();
				case "exportachievement" -> exportAchievement();
				case "updateachievement" -> updateAchievement();
				case "viewfriends" -> showFriends();
				case "help" -> displayCommands(true);
				case "exit" -> System.out.println("Exiting program.");
				default -> System.out.println("Unrecognized command. Use 'help' for all available commands.");
			}
		} while (!command.equalsIgnoreCase("exit"));
		System.exit(0);
	}
	private static void initialize()
	{
		LinkedList<String> names = new LinkedList<>();
		names.add("Bart");
		names.add("Peter");
		names.add("Esmee");
		names.add("Gerjan");
		names.add("Henk");
		names.add("Jan");
		names.add("Klaas");
		for(String name : names)
		{
			Random rng = new Random();
			int number = rng.nextInt(3)+1;
			FriendUser friend = switch (number)
			{
				case 1 -> positiveFriendUserFactory.createFriendUser(name);
				case 2 -> negativeFriendUserFactory.createFriendUser(name);
				case 3 -> nonActiveFriendUserFactory.createFriendUser(name);
				default -> throw new IllegalStateException("Unexpected value: "+number);
			};
			user.addFriend(friend);
		}
	}
	private static void updateAchievement()
	{
		LinkedList<Achievement> achievements = user.getAchievementList();
		if(!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement you wish to update:");
			Achievement achievement = achievements.get(selectAchievementFromList(achievements)-1);
			System.out.println("Please specify how much progress has been made towards completion.");
			System.out.print("> ");
			int progress = scanner.nextInt();
			scanner.nextLine();
			user.updateProgress(achievement, progress);
			System.out.println("Progress updated.");
		}
		else
		{
			System.out.println("No achievements detected!");
		}
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
		int progress = scanner.nextInt();
		user.addAchievement(game,title, description, progress);
		System.out.println("Achievement created succesfully!");
		scanner.nextLine();
	}
	
	private static void editAchievement()
	{
		List<Achievement> achievements = user.getAchievementList();
		if(!achievements.isEmpty())
		{
			System.out.println("Input the number of the achievement you wish to edit:");
			Achievement achievementToEdit = achievements.get(selectAchievementFromList(achievements)-1);
			System.out.println(achievementToEdit.getinfo());
			System.out.println("Please input the name of the item you wish to edit, to edit total progress use 'totalProgress'. Enter 'done' to finish.");
			String newGame = achievementToEdit.getGame();
			String newTitle = achievementToEdit.getTitle();
			String newDescription = achievementToEdit.getDescription();
			int newTotalProgress = achievementToEdit.getTotalProgress();
			boolean madeEdit = false;
			while (!(scanner.nextLine().equalsIgnoreCase("done")))
			{
				if(madeEdit)
				{
					System.out.println("Preview:");
					System.out.println("Title: "+newTitle+"\nGame: "+newGame+"\nDescription: "+newDescription+"\nProgress: "+achievementToEdit.getProgress()+"/"+newTotalProgress);
				}
				madeEdit = false;
				System.out.println("> ");
				switch (scanner.nextLine().toLowerCase())
				{
					case "game":
						System.out.println("Please input the new game.");
						newGame = scanner.nextLine();
						madeEdit = true;
						break;
					case "title":
						System.out.println("Please input the new achievement title.");
						newTitle = scanner.nextLine();
						madeEdit = true;
						break;
					case "description":
						System.out.println("Please input the new description.");
						newDescription = scanner.nextLine();
						madeEdit = true;
						break;
					case "progress":
						System.out.println("Please use the updateAchievement functionality to update progress, this function is to edit the Achievement's data.");
						break;
					case "totalprogress":
						System.out.println("Please input the new total Progress required for completion.");
						newTotalProgress = scanner.nextInt();
						if(newTotalProgress<0)
						{
							System.out.println("Negative input detected: Converting to positive.");
							newTotalProgress*=-1;
						}
					case "done":
						break;
					default:
						System.out.println("Invalid input detected.");
				}
			}
			user.editAchievement(achievementToEdit,newGame,newTitle,newDescription,newTotalProgress);
			System.out.println("Achievement edited succesfully!");
		}
		else
		{
			System.out.println("No achievements detected!");
		}
		scanner.nextLine();
	}
	
	private static int selectAchievementFromList(List<Achievement> achievements)
	{
		IntStream.range(0, achievements.size()).mapToObj(i -> i + 1+": "+achievements.get(i).getSimpleInfo()).forEach(System.out::println);
		System.out.print("> ");
		int index = -1;
		while (index<0||index> achievements.size())
		{
			if (scanner.hasNextInt()) {
				index = scanner.nextInt();
			} else {
				System.out.println("Invalid input. Please enter a valid integer.");
				scanner.next(); // Consume the invalid input
			}
		}
		scanner.nextLine();
		return index;
	}
	
	private static void saveAchievement()
	{
		List<Achievement> achievements = user.getAchievementList();
		if(!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement you wish to save:");
			System.out.print("> ");
			Achievement achievement = achievements.get(selectAchievementFromList(achievements)-1);
			user.saveAchievement(achievement);
		}
		else
		{
			System.out.println("No achievements detected!");
		}
		scanner.nextLine();
	}
	
	private static void restoreAchievement()
	{
		List<Achievement> achievements = user.getAchievementList();
		if(!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement for which you want to restore:");
			System.out.print("> ");
			Achievement achievement = achievements.get(selectAchievementFromList(achievements)-1);
			if(achievement.getHistory()!=0)
			{
				System.out.println("Input the number corresponding to the achievement you wish to restore.");
				System.out.print("> ");
				int amountOfMementos = achievement.getHistory();
				user.restoreMemento(achievement,amountOfMementos-scanner.nextInt());
				System.out.println("Achievement restored.");
			}
			else
			{
				System.out.println("This achievement does not have any previously saved versions.");
			}
		}
		else
		{
			System.out.println("No achievements detected!");
		}
		scanner.nextLine();
	}
	
	private static void exportAchievement()
	{
		List<Achievement> achievements = user.getAchievementList();
		if(!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement which you wish to export:");
			System.out.print("> ");
			Achievement achievement = achievements.get(selectAchievementFromList(achievements)-1);
			user.saveAchievement(achievement);
			achievement.serialize();
		}
		else
		{
			System.out.println("No achievements detected!");
		}
		scanner.nextLine();
	}
	private static void importAchievement()
	{
		user.importAchievement();
	}
	
	private static void displayCommands(boolean includeFirstLine)
	{
		if (includeFirstLine)
		{
			System.out.println("Available Commands (case insensitive)");
		}
		System.out.println("NewAchievement -> Creates a new achievement.");
		System.out.println("EditAchievement -> Edits an existing achievement.");
		System.out.println("UpdateAchievement -> Update your progress on an achievement.");
		System.out.println("SaveAchievement -> Saves an achievement.");
		System.out.println("RestoreAchievement -> Restores an achievement.");
		System.out.println("ImportAchievement -> Imports a previously exported achievement.");
		System.out.println("ExportAchievement -> Exports the achievement as a JSON file.");
		System.out.println("ViewFriends -> Shows you your friend list.");
		System.out.println("Help -> Displays this dialog.");
		System.out.println("Exit -> Terminates the program. Changes will not be saved.");
	}
	private static void showFriends()
	{
		for(FriendUser friend : user.getFriendList())
		{
			System.out.println(friend.getName()+"("+friend.getClass().getSimpleName()+")");
		}
	}
}
