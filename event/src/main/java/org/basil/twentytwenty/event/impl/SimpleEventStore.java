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
package org.basil.twentytwenty.event.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.basil.twentytwenty.domain.DomainEvent;
import org.basil.twentytwenty.domain.Id;
import org.basil.twentytwenty.event.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * A very simple in-memory event store using {@link List}.
 */
public class SimpleEventStore implements EventStore {

  public static EventStore list() {
    return new SimpleEventStore(Lists.newArrayList());
  }

  private final Logger logger = LoggerFactory.getLogger(SimpleEventStore.class);

  private final List<DomainEvent> events;

  /**
   * Using the List as an interface for event crud.
   */
  SimpleEventStore(List<DomainEvent> events) {
    this.events = events;
  }

  @Override
  public synchronized <E extends DomainEvent> void save(E event) {
    logger.info("save(E event):{}", event.getId().toString());

    events.add(event);
  }

  @Override
  public <E extends DomainEvent> void save(Iterable<E> events) {
    events.forEach(this::save);
  }

  @Override
  public <E extends DomainEvent, ID extends Id> List<E> find(ID aggregateId) {
    return events.stream()
        .filter(event -> event.getId().equals(aggregateId))
        .map(event -> (E) event)
        .sorted(Collections.reverseOrder())
        .collect(Collectors.toList());
  }

}
