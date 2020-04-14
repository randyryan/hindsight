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
package org.basil.twentytwenty.event;

import org.basil.twentytwenty.domain.DomainEvent;
import org.basil.twentytwenty.domain.Id;

import java.util.List;

/**
 * The ({@code Aggregate}) event store interface.
 */
public interface EventStore {

  /**
   * Saves the specified event.
   *
   * @param <E> type of the event
   * @param event to save
   */
  <E extends DomainEvent> void save(E event);

  /**
   * Saves the specified events.
   *
   * @param <E>
   * @param events to save
   */
  <E extends DomainEvent> void save(Iterable<E> events);

  /**
   * Finds the events from the {@code Aggregate} of the specified ID.
   *
   * @param <E> type pf the events
   * @param <ID> type of the {@code Aggregate}'s ID
   * @param aggregateId of the {@code Aggregate}
   * @return a {@link List} of events
   */
  <E extends DomainEvent, ID extends Id> List<E> find(ID aggregateId);

}
