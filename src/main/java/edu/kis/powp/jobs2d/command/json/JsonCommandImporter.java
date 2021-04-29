package edu.kis.powp.jobs2d.command.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import edu.kis.powp.jobs2d.command.CompoundCommand;
import edu.kis.powp.jobs2d.command.DriverCommand;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JsonCommandImporter {
	private static final Gson gson = new GsonBuilder()
			.registerTypeAdapter(DriverCommand.class,new JsonCommandSerializer())
			.setPrettyPrinting()
			.create();

	public static CompoundCommand importCommand(String filepath) throws IOException {
		JsonReader reader = new JsonReader(new FileReader(filepath));
		return gson.fromJson(reader, CompoundCommand.class);
	}

	public static void saveCommand(String filepath, CompoundCommand command) throws IOException {
		FileWriter writer = new FileWriter(filepath);
		gson.toJson(command, writer);
		writer.close();
	}
}
