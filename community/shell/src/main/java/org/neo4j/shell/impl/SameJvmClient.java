/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.shell.impl;

import org.neo4j.shell.Output;
import org.neo4j.shell.ShellClient;
import org.neo4j.shell.ShellServer;

/**
 * An implementation of {@link ShellClient} optimized to use with a server
 * in the same JVM.
 */
public class SameJvmClient extends AbstractClient
{
	private Output out;
	private ShellServer server;
	
	public SameJvmClient( ShellServer server )
	{
	    this( server, new SystemOutput() );
	}
	
	/**
	 * @param server the server to communicate with.
	 */
	public SameJvmClient( ShellServer server, Output out )
	{
	    this.out = out;
		this.server = server;
		init();
	    updateTimeForMostRecentConnection();
	}
	
	public Output getOutput()
	{
		return this.out;
	}

	public ShellServer getServer()
	{
		return this.server;
	}
}
