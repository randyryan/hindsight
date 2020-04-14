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

/**
 * Publishes {@link Event}s to {@link EventHandler}s.
 */
public interface EventPublisher {

  /**
   * Registers the {@link EventHandler}s to this event publisher.
   *
   * @param <T> type of the {@link EventHandler}s
   * @param eventHandlers to register to this publisher
   */
  <T extends EventHandler<?>> void register(T... eventHandlers);

  /**
   * Unregisters the {@link EventHandler}s from this event publisher.
   *
   * @param <T> type of the {@link EventHandler}s
   * @param eventHandlers to unregister to this publisher
   */
  <T extends EventHandler<?>> void unregister(T... eventHandlers);

  /**
   * Publishes the {@link Event}s to all registered {@link EventHandler}s.
   *
   * @param <E> type of the {@code Event}s
   * @param events to publish
   */
  <E extends DomainEvent<?>> void publish(E... events);

}
