package other;

import Memento.Memento;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class JsonReader {

	public Memento readMementoFromJson()
	{
		try {
			JsonNode jsonNode = readJsonFromFile(jsonFilePicker().getPath());

			LocalDateTime dateTime = LocalDateTime.parse(jsonNode.get("dateTime").asText());
			String game = jsonNode.get("game").asText();
			String description = jsonNode.get("description").asText();
			int progress = jsonNode.get("progress").asInt();
			LocalDateTime dateAchieved = LocalDateTime.parse(jsonNode.get("dateAchieved").asText());
			int totalProgress = jsonNode.get("totalProgress").asInt();

			System.out.println("dateTime: " + dateTime);
			System.out.println("game: " + game);
			System.out.println("description: " + description);
			System.out.println("progress: " + progress);
			System.out.println("dateAchieved: " + dateAchieved);
			System.out.println("totalProgress: " + totalProgress);

			Memento memento = new Memento(game,description,totalProgress,progress,dateAchieved);
			memento.setDateTime(dateTime);

			return memento;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JsonNode readJsonFromFile(String filePath) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readTree(new File(filePath));
	}

	public File jsonFilePicker()
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files only (*.json)", "json");
		fileChooser.setFileFilter(filter);

		fileChooser.showSaveDialog(null);

		System.out.println(fileChooser.getSelectedFile());

		return fileChooser.getSelectedFile();
	}

}
