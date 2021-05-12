package edu.kis.powp.jobs2d.command;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileOpertor {
	public static String loadFileContent(String filepath) throws IOException {
		return new Scanner(new File(filepath)).useDelimiter("\\Z").next();
	}

	public static void writeFileContent(String filepath, String content) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
		writer.write(content);
		writer.close();
	}
}
