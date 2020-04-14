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
 * The handler for event sourcing {@link Event}s, must be registered to at least one
 * {@link EventPublisher} to receive {@code Event}s from.
 *
 * @param <E> the type of events that this handler handles.
 */
public interface EventHandler<E extends DomainEvent<?>> {

  /**
   * Handle the published {@link Event}.
   *
   * @param event to handle
   */
  void handle(E event);

}
