/*
 * Copyright (c) 2002-2018 "Neo Technology,"
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
package org.neo4j.kernel.impl.newapi;

import org.neo4j.collection.PrimitiveLongResourceIterator;
import org.neo4j.storageengine.api.schema.IndexProgressor;

class NodeLabelIndexProgressor implements IndexProgressor
{
    private final PrimitiveLongResourceIterator iterator;
    private final NodeLabelClient client;

    NodeLabelIndexProgressor( PrimitiveLongResourceIterator iterator, NodeLabelClient client )
    {
        this.iterator = iterator;
        this.client = client;
    }

    @Override
    public boolean next()
    {
        while ( iterator.hasNext() )
        {
            if ( client.acceptNode( iterator.next(), null ) )
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void close()
    {
        iterator.close();
    }
}
