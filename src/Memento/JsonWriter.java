package Memento;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonWriter {

	public static void writeMementoToJson(Achievement.Memento memento)
	{
		Map<String, Object> data = new HashMap<>();
		data.put("game", memento.getGame());
		data.put("progress", memento.getProgress());
		data.put("totalProgress", memento.getTotalProgress());
		data.put("description", memento.getDescription());
		data.put("dateTime", memento.getDateCreated());
		data.put("dateAchieved", memento.getDateAchieved());

		Path filePath = Paths.get(locationPicker().getPath() + "\\" + memento.getGame() + memento.getDateCreated().toString().replace(":", "-") + ".json"); // Define the file path
		try (Writer writer = Files.newBufferedWriter(filePath)) {
			writer.write(mapToJson(data));
			System.out.println("JSON file created successfully!" + " at;");
			System.out.println("File path: " + filePath.toAbsolutePath()); // Print absolute path
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static String mapToJson(Map<String, Object> map) {
		return "{" + map.entrySet().stream()
				.map(entry -> "\"" + entry.getKey() + "\": \"" + entry.getValue() + "\"")
				.collect(Collectors.joining(", ")) + "}";
	}

	public static File locationPicker()
	{
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.showSaveDialog(null);

		System.out.println(f.getCurrentDirectory());
		System.out.println(f.getSelectedFile());

		if(f.getSelectedFile() != null)
		{
			return f.getSelectedFile();
		}
		else
		{
			return f.getCurrentDirectory();
		}
	}

}
