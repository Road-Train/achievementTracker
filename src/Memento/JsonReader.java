package Memento;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonReader
{
	private static final JFileChooser fileChooser = new JFileChooser();
	static Achievement.Memento readMementoFromJson()
	{
		Path filePath = Paths.get(jsonFilePicker().getPath());
		try (FileInputStream fileIn = new FileInputStream(filePath.toFile()); ObjectInputStream objectIn = new ObjectInputStream(fileIn))
		{
			return (Achievement.Memento) objectIn.readObject();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	private static File jsonFilePicker()
	{
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files only (*.json)", "json");
		fileChooser.setFileFilter(filter);
		
		fileChooser.showSaveDialog(null);
		
		System.out.println(fileChooser.getSelectedFile());
		
		return fileChooser.getSelectedFile();
	}
	
}
