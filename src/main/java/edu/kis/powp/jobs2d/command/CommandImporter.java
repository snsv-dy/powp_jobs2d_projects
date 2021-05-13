package edu.kis.powp.jobs2d.command;

import java.io.IOException;

public interface CommandImporter   {

	CompoundCommand importCommand(String command) throws IOException;
	String exportCommand(CompoundCommand command) throws IOException;

}
