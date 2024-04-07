package Memento;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonWriter
{
	private static final JFileChooser fileChooser = new JFileChooser();
	static void writeMementoToJson(Achievement.Memento memento)
	{
		Path filePath = Paths.get(locationPicker().getPath() + "\\" + memento.getFilePath() + ".json"); // Define the file path
		try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile()); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut))
		{
			objectOut.writeObject(memento);
			System.out.println("Memento object written to file successfully at:");
			System.out.println("File path: " + filePath.toAbsolutePath());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private static File locationPicker()
	{
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.showSaveDialog(null);
		
		System.out.println(fileChooser.getCurrentDirectory());
		System.out.println(fileChooser.getSelectedFile());
		
		if (fileChooser.getSelectedFile() != null)
		{
			return fileChooser.getSelectedFile();
		}
		else
		{
			return fileChooser.getCurrentDirectory();
		}
	}
	
}
