package other;

import FactoryMethod.NegativeFriendUserFactory;
import FactoryMethod.NonActiveFriendUserFactory;
import FactoryMethod.PositiveFriendUserFactory;
import Memento.Achievement;
import Observer.FriendUser;

import java.util.LinkedList;
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
		System.out.println("for some functions debug mode should be used (json import/export)");
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
				case "changename" -> changeName();
				case "viewprofile" -> viewProfile();
				case "newachievement" -> createAchievement();
				case "removeachievement" -> removeAchievement();
				case "viewachievements" -> showAchievements(true);
				case "editachievement" -> editAchievement();
				case "saveachievement" -> saveAchievement();
				case "restoreachievement" -> restoreAchievement();
				case "importachievement" -> importAchievement();
				case "exportachievement" -> exportAchievement();
				case "updateachievement" -> updateAchievement();
				case "viewfriends" -> showFriends();
				case "addfriend" -> addFriend();
				case "removefriend" -> removeFriend();
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
		for (String name : names)
		{
			Random rng = new Random();
			int number = rng.nextInt(3) + 1;
			FriendUser friend = switch (number)
			{
				case 1 -> positiveFriendUserFactory.createFriendUser(name);
				case 2 -> negativeFriendUserFactory.createFriendUser(name);
				case 3 -> nonActiveFriendUserFactory.createFriendUser(name);
				default -> throw new IllegalStateException("Unexpected value: " + number);
			};
			user.addFriend(friend);
		}
	}

	private  static void changeName()
	{
		System.out.println("Enter your new profile username:");
		System.out.print("> ");
		user.setName(scanner.nextLine());
		System.out.println("username has been updated");
	}

	private  static void viewProfile()
	{
		System.out.println("Your profile:");
		System.out.println(user.getName());
		System.out.println("Achievements: " + user.getAchievementList().size());
		System.out.println("Friends: " + user.getFriendList().size());

	}
	
	private static void addFriend()
	{
		System.out.println("Note: As of right now all friends are simulated and not real. We apologize for the inconvenience.");
		System.out.println("Please enter the friend's name:");
		System.out.print("> ");
		String name = scanner.nextLine();
		System.out.println("Input the friend's desired attitude. 1 for positive, 2 for negative, 3 for nonactive");
		FriendUser friend = null;
		while (friend == null)
		{
			System.out.print("> ");
			try
			{
				friend = switch (scanner.nextInt())
				{
					case 1 -> positiveFriendUserFactory.createFriendUser(name);
					case 2 -> negativeFriendUserFactory.createFriendUser(name);
					case 3 -> nonActiveFriendUserFactory.createFriendUser(name);
					default -> throw new IllegalStateException("Unexpected value: " + scanner.nextInt());
				};
			}
			catch (IllegalStateException e)
			{
				System.out.println(e.getMessage());
			}
		}
		user.addFriend(friend);
	}
	
	private static void removeFriend()
	{
		LinkedList<FriendUser> friends = user.getFriendList();
		if (friends.isEmpty())
		{
			System.out.println("Input the number of the friend you wish to remove:");
			showFriends();
			System.out.print("> ");
			FriendUser friend = friends.get(selectItemFromList(friends) - 1);
			user.removeFriend(friend);
		}
		else
		{
			System.out.println("No friends detected!");
		}
	}
	
	private static void removeAchievement()
	{
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (achievements.isEmpty())
		{
			System.out.println("Input the number of the friend you wish to remove:");
			showAchievements();
			System.out.print("> ");
			Achievement achievement = achievements.get(selectItemFromList(achievements) - 1);
			System.out.println("Do you want to export this achievement before deleting?");
			String input = null;
			while(input == null)
			{
				try
				{
					input = switch (scanner.nextLine().toLowerCase())
					{
						case "yes" -> "yes";
						case "no" -> "no";
						default -> throw new IllegalStateException("Unexpected value: " + scanner.nextLine());
					};
				}
				catch (IllegalStateException e)
				{
					System.out.println(e.getMessage());
				}
			}
			if(input.equalsIgnoreCase("yes"))
			{
				achievement.serialize();
			}
			user.removeAchievement(achievement);
		}
		else
		{
			System.out.println("No friends detected!");
		}
	}
	
	private static void updateAchievement()
	{
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement you wish to update:");
			showAchievements();
			Achievement achievement = achievements.get(selectItemFromList(achievements) - 1);
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
		user.addAchievement(game, title, description, progress);
		System.out.println("Achievement created succesfully!");
		scanner.nextLine();
	}
	
	private static void editAchievement()
	{
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (!achievements.isEmpty())
		{
			System.out.println("Input the number of the achievement you wish to edit:");
			showAchievements();
			Achievement achievementToEdit = achievements.get(selectItemFromList(achievements) - 1);
			System.out.println(achievementToEdit.getInfo());
			System.out.println("Please input the name of the item you wish to edit, to edit total progress use 'totalProgress'. Enter 'done' to finish.");
			String newGame = achievementToEdit.getGame();
			String newTitle = achievementToEdit.getTitle();
			String newDescription = achievementToEdit.getDescription();
			int newTotalProgress = achievementToEdit.getTotalProgress();
			boolean madeEdit = false;
			while (!(scanner.nextLine().equalsIgnoreCase("done")))
			{
				if (madeEdit)
				{
					System.out.println("Preview:");
					System.out.println("Title: " + newTitle + "\nGame: " + newGame + "\nDescription: " + newDescription + "\nProgress: " + achievementToEdit.getProgress() + "/" + newTotalProgress);
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
						if (newTotalProgress < 0)
						{
							System.out.println("Negative input detected: Converting to positive.");
							newTotalProgress *= -1;
						}
					case "done":
						break;
					default:
						System.out.println("Invalid input detected.");
				}
			}
			user.editAchievement(achievementToEdit, newGame, newTitle, newDescription, newTotalProgress);
			System.out.println("Achievement edited succesfully!");
		}
		else
		{
			System.out.println("No achievements detected!");
		}
		scanner.nextLine();
	}
	
	private static void showAchievements()
	{
		showAchievements(false);
	}
	
	private static void showAchievements(boolean showDetailedInfo)
	{
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (showDetailedInfo)
		{
			IntStream.range(0, achievements.size()).mapToObj(i -> i + 1 + ": " + achievements.get(i).getInfo()).forEach(System.out::println);
		}
		else
		{
			IntStream.range(0, achievements.size()).mapToObj(i -> i + 1 + ": " + achievements.get(i).getSimpleInfo()).forEach(System.out::println);
		}
	}
	
	private static int selectItemFromList(LinkedList list)
	{
		int size = list.size();
		System.out.print("> ");
		int index = -1;
		while (index < 0 || index > size)
		{
			if (scanner.hasNextInt())
			{
				index = scanner.nextInt();
			}
			else
			{
				System.out.println("Invalid input. Please enter a valid integer.");
				scanner.next(); // Consume the invalid input
			}
		}
		scanner.nextLine();
		return index;
	}
	
	private static void saveAchievement()
	{
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement you wish to save:");
			showAchievements();
			System.out.print("> ");
			Achievement achievement = achievements.get(selectItemFromList(achievements) - 1);
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
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement for which you want to restore:");
			showAchievements();
			System.out.print("> ");
			Achievement achievement = achievements.get(selectItemFromList(achievements) - 1);
			if (achievement.getHistory() != 0)
			{
				System.out.println("Input the number corresponding to the achievement you wish to restore.");
				System.out.print("> ");
				int amountOfMementos = achievement.getHistory();
				user.restoreMemento(achievement, amountOfMementos - scanner.nextInt());
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
		LinkedList<Achievement> achievements = user.getAchievementList();
		if (!(achievements.isEmpty()))
		{
			System.out.println("Input the number of the achievement which you wish to export:");
			showAchievements();
			System.out.print("> ");
			Achievement achievement = achievements.get(selectItemFromList(achievements) - 1);
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
		System.out.println("Memento has been imported (when selected)");
	}
	
	private static void displayCommands(boolean includeFirstLine)
	{
		if (includeFirstLine)
		{
			System.out.println("Available Commands (case insensitive)");
		}
		System.out.println("ChangeName -> changes the username");
		System.out.println("ViewProfile -> views the user their profile");
		System.out.println("NewAchievement -> Creates a new achievement.");
		System.out.println("RemoveAchievement -> Removes an achievement.");
		System.out.println("EditAchievement -> Edits an existing achievement.");
		System.out.println("UpdateAchievement -> Update your progress on an achievement.");
		System.out.println("SaveAchievement -> Saves an achievement.");
		System.out.println("RestoreAchievement -> Restores an achievement.");
		System.out.println("ImportAchievement -> Imports a previously exported achievement.");
		System.out.println("ExportAchievement -> Exports the achievement as a JSON file.");
		System.out.println("ViewAchievements -> Shows you your achievement list.");
		System.out.println("ViewFriends -> Shows you your friend list.");
		System.out.println("AddFriend -> Adds a new friend (debug feature)");
		System.out.println("RemoveFriend -> Removes a friend.");
		System.out.println("Help -> Displays this dialog.");
		System.out.println("Exit -> Terminates the program. Changes will not be saved.");
	}
	
	private static void showFriends()
	{
		LinkedList<FriendUser> friends = user.getFriendList();
		IntStream.range(0, friends.size()).mapToObj(i -> i + 1 + ": " + friends.get(i).getName() + "(" + friends.get(i).getClass().getSimpleName() + ")").forEach(System.out::println);
	}
}
