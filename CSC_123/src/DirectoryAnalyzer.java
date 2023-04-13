// Mantra Mehta(mmehta2@toromail.csudh.edu
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DirectoryAnalyzer {
	private static final int KILOBYTE = 1024;
	private static final int MEGABYTE = KILOBYTE * 1024;
	private static final int GIGABYTE = MEGABYTE * 1024;

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java DirectoryAnalyzer <directoryName>");
			System.exit(1);
		}

		String directoryName = args[0];
		File directory = new File(directoryName);

		if (!directory.exists()) {
			System.err.println("Directory does not exist: " + directoryName);
			System.exit(1);
		}

		if (!directory.isDirectory()) {
			System.err.println("Not a directory: " + directoryName);
			System.exit(1);
		}

		if (!directory.canRead()) {
			System.err.println("No read permission: " + directoryName);
			System.exit(1);
		}

		File[] files = directory.listFiles();

		if (files == null) {
			System.err.println("Could not read files in directory: " + directoryName);
			System.exit(1);
		}

		int totalFiles = 0;
		int totalAlphaChars = 0;
		int totalNumericChars = 0;
		int totalSpaceChars = 0;
		long totalSize = 0;
		System.out.println();
		System.out.printf("%-30s %-15s %-15s %-15s %-15s%n", "File Name", "Size", "Alpha Chars", "Numeric Chars",
				"Spaces");
		// System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		for (File file : files) {
			if (file.isFile()) {
				String fileName = file.getName();
				long fileSize = file.length();
				int alphaChars = 0;
				int numericChars = 0;
				int spaceChars = 0;

				try (Scanner scanner = new Scanner(file)) {
					while (scanner.hasNext()) {
						String token = scanner.next();
						for (char c : token.toCharArray()) {
							if (Character.isLetter(c)) {
								alphaChars++;
							} else if (Character.isDigit(c)) {
								numericChars++;
							} else if (Character.isSpaceChar(c)) {
								spaceChars++;
							}
						}
					}
				} catch (FileNotFoundException e) {
					System.err.println("Could not read file: " + fileName);
					continue;
				}

				totalFiles++;
				totalAlphaChars += alphaChars;
				totalNumericChars += numericChars;
				totalSpaceChars += spaceChars;
				totalSize += fileSize;

				System.out.printf("%-30s %-15s %-15s %-15s %-15s%n", fileName, bytesFileSize(fileSize), alphaChars,
						numericChars, spaceChars);
			}
		}
		System.out.println();
		// System.out.println("---------------------------------------------------------------------------------");
		System.out.println("Total Files\t\t : " + totalFiles);
		System.out.println("Total Alpha Chars\t : " + totalAlphaChars);
		System.out.println("Total Numeric Chars\t : " + totalNumericChars);
		System.out.println("Total Space Chars\t : " + totalSpaceChars + "\n");
		System.out.println("Total Size Disk : " + formatSizeOnDisk(totalSize));
	}

	private static String bytesFileSize(long fileSize) {
		return fileSize + " bytes";
	}

	private static String formatSizeOnDisk(long totalSize) {
		if (totalSize < KILOBYTE) {
			return totalSize + " bytes";
		} else if (totalSize < MEGABYTE) {
			return String.format("%.2f KB", totalSize / (double) KILOBYTE);
		} else if (totalSize < GIGABYTE) {
			return String.format("%.2f MB", totalSize / (double) MEGABYTE);
		} else {
			return String.format("%.2f GB", totalSize / (double) GIGABYTE);
		}
	}
}

