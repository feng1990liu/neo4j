/*
 * Copyright (c) 2002-2018 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.neo4j.cypher.internal.frontend.v3_5.ast.conditions

import org.neo4j.cypher.internal.frontend.v3_5.helpers.rewriting.RewriterCondition
import org.neo4j.cypher.internal.frontend.v3_5.phases.{BaseState, Condition}

case class StatementCondition(inner: Any => Seq[String]) extends Condition {
  override def check(state: AnyRef): Seq[String] = state match {
    case s: BaseState => inner(s.statement())
    case x => throw new IllegalArgumentException(s"Unknown state: $x")
  }
}

object StatementCondition {
  def apply(inner: RewriterCondition) = new StatementCondition(inner.condition)
}
