/*
 * Copyright (c) 2020 Li Wan
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.basil.twentytwenty.domain;

import java.util.Optional;

/**
 * A Repository for saving and loading entities within an <em>Aggregate</em>.
 *
 * @param <AR> type of the <em>Aggregate Root</em>
 */
public interface Repository<AR extends AggregateRoot<?>> {

  /**
   * Save the entities from the specified <em>Aggregate Root</em>.
   *
   * @param aggregateRoot containing the entities to save
   */
  void save(AR aggregateRoot);

  /**
   * Returns the <em>Aggregate Root</em> of the specified ID.
   *
   * @param <ID> type of the ID
   * @param id to find the <em>Aggregate Root</em>
   * @return the <em>Aggregate Root</em> of the specified ID
   */
  <ID extends Id<?>> Optional<AR> findById(ID id);

  /**
   * Deletes the <em>Aggregate Root</em> of the specified ID.
   *
   * @param <ID> type of the ID
   * @param id to find the <em>Aggregate Root</em> and delete
   */
  <ID extends Id<?>> void deleteByID(ID id);

}
