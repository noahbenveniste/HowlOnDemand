package edu.ncsu.csc216.howlondemand.platform;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.howlondemand.platform.enums.CommandValue;

/**
 * Unit tests for the Command class
 * @author Noah Benveniste
 */
public class CommandTest {
	/** Command reference variable to be used throughout testing */
	private Command cmd;
	
	/**
	 * Executes before each test to initialize objects to be used during testing
	 */
	@Before
	public void setUp() {
		
	}
	
	/**
	 * test Command() constructor
	 */
	@Test
	public void testCommand() {
		cmd = new Command(CommandValue.BUFFERING);
		assertEquals(CommandValue.BUFFERING, cmd.getCommand());
	}
	
	/**
	 * test getCommand()
	 */
	@Test
	public void testGetCommand() {
		
	}
	
	/**
	 * test toString()
	 */
	@Test
	public void testToString() {
		cmd = new Command(CommandValue.BUFFERING);
		assertEquals("BUFFERING", cmd.toString());
	}
	
}
