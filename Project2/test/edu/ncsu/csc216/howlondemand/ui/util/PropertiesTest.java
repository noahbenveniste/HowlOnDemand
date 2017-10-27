package edu.ncsu.csc216.howlondemand.ui.util;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import edu.ncsu.csc216.howlondemand.ui.util.Properties;;

/**
 * Unit tests for Properties
 * @author Noah Benveniste
 */
public class PropertiesTest {

	/**
	 * Tests the value of the "Black" field
	 */
	@Test
	public void testBlack() {
		assertEquals(Color.decode("#2C3E50"), Properties.BLACK);
	}
}
