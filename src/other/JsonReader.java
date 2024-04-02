package other;

import Memento.Memento;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonReader
{
	
	public Memento readMementoFromJson()
	{
		Path filePath = Paths.get(jsonFilePicker().getPath());
		try (FileInputStream fileIn = new FileInputStream(filePath.toFile()); ObjectInputStream objectIn = new ObjectInputStream(fileIn))
		{
			return (Memento) objectIn.readObject();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	private File jsonFilePicker()
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
