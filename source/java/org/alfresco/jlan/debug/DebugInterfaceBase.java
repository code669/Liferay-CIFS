/*
 * Copyright (C) 2006-2008 Alfresco Software Limited.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.

 * As a special exception to the terms and conditions of version 2.0 of 
 * the GPL, you may redistribute this Program in connection with Free/Libre 
 * and Open Source Software ("FLOSS") applications as described in Alfresco's 
 * FLOSS exception.  You should have recieved a copy of the text describing 
 * the FLOSS exception, and it is also available here: 
 * http://www.alfresco.com/legal/licensing"
 */

package org.alfresco.jlan.debug;

import org.springframework.extensions.config.ConfigElement;

/**
 * Debug Interface Base Class
 * 
 * @author gkspencer
 */
public abstract class DebugInterfaceBase implements DebugInterface {

	// Log output level
	
	private int m_logLevel = Debug.Debug;
	
	/**
	 * Class constructor
 	 */
	public DebugInterfaceBase() {
	}

	/**
	 * Close the debug output.
	 */
	public void close() {}

	/**
	 * Output a debug string.
	 *
	 * @param str java.lang.String
	 */
	public final void debugPrint(String str) {
	  debugPrint(str, Debug.Debug);
	}

	/**
	 * Output a debug string with a specific logging level
	 * 
	 * @param str String
	 * @param level int
	 */
	public abstract void debugPrint(String str, int level);
	
	/**
	 * Output a debug string, and a newline.
	 *
	 * @param str java.lang.String
	 */
	public final void debugPrintln(String str) {
	  debugPrintln(str, Debug.Debug);
	}

	/**
	 * Output a debug string, and a newline, with a specific logging level
	 *
	 * @param str String
	 */
	public abstract void debugPrintln(String str, int level);

	/**
	 * Return the debug interface logging level
	 * 
	 * @return int
	 */
	public final int getLogLevel() {
	  return m_logLevel;
	}

	/**
	 * Set the logging level
	 * 
	 * @param logLevel int
	 */
	public final void setLogLevel(int logLevel) {
	  m_logLevel = logLevel;
	}
	
	/**
	 * Initialize the debug interface using the specified parameters.
	 *
	 * @param params ConfigElement
	 */
	public void initialize(ConfigElement params)
		throws Exception {
		
		// Check for a logging level
		
		ConfigElement logLevelElem = params.getChild( "logLevel");
		
		if ( logLevelElem != null) {
			
			// Validate the logging level
			
			String logLevelStr = logLevelElem.getValue();
			
			if ( logLevelStr != null) {
				if ( logLevelStr.equalsIgnoreCase("Debug"))
				  m_logLevel = Debug.Debug;
				else if ( logLevelStr.equalsIgnoreCase("Info"))
				  m_logLevel = Debug.Info;
				else if ( logLevelStr.equalsIgnoreCase("Warn"))
				  m_logLevel = Debug.Warn;
				else if ( logLevelStr.equalsIgnoreCase("Error"))
				  m_logLevel = Debug.Error;
				else if ( logLevelStr.equalsIgnoreCase("Fatal"))
				  m_logLevel = Debug.Fatal;
				else
				  throw new Exception("Invalid debug logging level, " + logLevelStr);
			}
		}
	}
}
