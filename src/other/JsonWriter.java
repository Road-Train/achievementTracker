package other;

import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonWriter
{
	public void writeMementoToJson(Memento.Memento memento)
	{
		Path filePath = Paths.get(STR."\{locationPicker().getPath()}\\\{memento.getGame()}\{memento.getDateTime().toString().replace(":", "-")}.json"); // Define the file path
		try (FileOutputStream fileOut = new FileOutputStream(filePath.toFile()); ObjectOutputStream objectOut = new ObjectOutputStream(fileOut))
		{
			objectOut.writeObject(memento);
			System.out.println("Memento object written to file successfully at:");
			System.out.println(STR."File path: \{filePath.toAbsolutePath()}");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private File locationPicker()
	{
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.showSaveDialog(null);
		
		System.out.println(f.getCurrentDirectory());
		System.out.println(f.getSelectedFile());
		
		if (f.getSelectedFile() != null)
		{
			return f.getSelectedFile();
		}
		else
		{
			return f.getCurrentDirectory();
		}
	}
	
}
