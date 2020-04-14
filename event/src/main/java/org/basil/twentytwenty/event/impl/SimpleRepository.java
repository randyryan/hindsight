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

import java.util.Optional;

import org.basil.twentytwenty.domain.AggregateRoot;
import org.basil.twentytwenty.domain.Id;
import org.basil.twentytwenty.domain.Repository;
import org.basil.twentytwenty.event.EventPublisher;
import org.basil.twentytwenty.event.EventStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Simple ({@code Event}) {@code Aggregate} repository.
 */
public class SimpleRepository implements Repository<AggregateRoot<?>> {

  private final Logger logger = LoggerFactory.getLogger(SimpleRepository.class);

  private final EventPublisher eventPublisher;
  private final EventStore eventStore;

  public SimpleRepository(EventPublisher eventPublisher, EventStore eventStore) {
    this.eventPublisher = eventPublisher;
    this.eventStore = eventStore;
  }

  @Override
  public void save(AggregateRoot<?> aggregateRoot) {
    aggregateRoot.getUncommittedChanges()
        .forEach(change -> {
          eventStore.save(change);
          eventPublisher.publish(change);
        });
  }

  @Override
  public <ID extends Id<?>> Optional<AggregateRoot<?>> findById(ID id) {
    return Optional.empty();
  }

  @Override
  public <ID extends Id<?>> void deleteByID(ID id) {
    throw new UnsupportedOperationException();
  }

}
