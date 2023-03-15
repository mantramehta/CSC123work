// Mantra Mehta(mmehta2@toromail.csudh.edu
import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        // Check if both arguments are provided
        if (args.length < 2) {
            System.out.println("Usage: java FileCopy <source_file> <target_file>");
            return;
        }
        
        // Get source and target file paths
        String sourceFilePath = args[0];
        String targetFilePath = args[1];
        
        // Check if source file exists
        File sourceFile = new File(sourceFilePath);
        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist.");
            return;
        }
        
        // Check if source file is a directory
        if (sourceFile.isDirectory()) {
            System.out.println("Source file cannot be a directory.");
            return;
        }
        
        // Check if target file exists
        File targetFile = new File(targetFilePath);
        if (targetFile.exists()) {
            System.out.println("Target file already exists.");
            return;
        }
        
        // Check if target file is a directory
        if (targetFile.isDirectory()) {
            System.out.println("Target file cannot be a directory.");
            return;
        }
        
        // Create any required directories for target file
        File parentDirectory = targetFile.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }
        
        // Copy file contents from source file to target file
        try (FileInputStream inputStream = new FileInputStream(sourceFile);
             FileOutputStream outputStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println(sourceFile.length() + " bytes successfully copied from " + sourceFile.getName() + " to " + targetFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}