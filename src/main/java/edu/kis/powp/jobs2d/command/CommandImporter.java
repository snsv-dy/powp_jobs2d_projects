package edu.kis.powp.jobs2d.command;

import java.io.IOException;

public interface CommandImporter   {

	CompoundCommand importCommand(String filepath) throws IOException;
	void saveCommand(String filepath, CompoundCommand command) throws IOException;
}
