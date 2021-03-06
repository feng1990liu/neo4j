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
package org.neo4j.cypher.internal.v3_5.logical.plans

import org.neo4j.cypher.internal.v3_5.expressions.Expression
import org.neo4j.cypher.internal.ir.v3_5.StrictnessMode
import org.neo4j.cypher.internal.util.v3_5.attribution.IdGen

/**
  * For each input row, delete the entity specified by 'expression' from the graph. If the entity is a
  *   node) all it's relationships are also deleted
  *   path) all nodes in the path and all their relationships are deleted.
  */
case class DetachDeleteExpression(source: LogicalPlan, expression: Expression)(implicit idGen: IdGen) extends LogicalPlan(idGen) {

  override def lhs: Option[LogicalPlan] = Some(source)

  override val availableSymbols: Set[String] = source.availableSymbols

  override def rhs: Option[LogicalPlan] = None

  override def strictness: StrictnessMode = source.strictness
}
