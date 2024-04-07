package Memento;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class JsonReader
{
	private static final JFileChooser fileChooser = new JFileChooser();
	static Achievement.Memento readMementoFromJson()
	{
		try {
			JsonNode jsonNode = null;
			String path = jsonFilePicker().getPath();
			if (path != null) {
				jsonNode = readJsonFromFile(path);
			}

			if(jsonNode == null)
			{
				return null;
			}

			LocalDateTime dateTime = LocalDateTime.parse(jsonNode.get("dateTime").asText());
			String game = jsonNode.get("game").asText();
			String description = jsonNode.get("description").asText();
			int progress = jsonNode.get("progress").asInt();
			LocalDateTime dateAchieved = LocalDateTime.parse(jsonNode.get("dateAchieved").asText());
			int totalProgress = jsonNode.get("totalProgress").asInt();

			Achievement.Memento memento = new Achievement.Memento(game,"title",description,totalProgress,progress,dateAchieved);

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

	public static File jsonFilePicker() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files only (*.json)", "json");
		fileChooser.setFileFilter(filter);

		fileChooser.showSaveDialog(null);

		if (fileChooser.getSelectedFile() != null)
		{
			System.out.println(fileChooser.getSelectedFile());
		}
		else
		{
			return null;
		}

		return fileChooser.getSelectedFile();
	}

}
