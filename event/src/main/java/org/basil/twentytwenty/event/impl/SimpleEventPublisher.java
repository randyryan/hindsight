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

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.basil.twentytwenty.domain.DomainEvent;
import org.basil.twentytwenty.event.EventHandler;
import org.basil.twentytwenty.event.EventPublisher;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * A very simple implementation of {@link EventPublisher} using Google Guava's EventBus.
 */
public class SimpleEventPublisher implements EventPublisher {

  public static EventPublisher async() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    return new SimpleEventPublisher(new AsyncEventBus("eventBus", executorService));
  }

  private final EventBus eventBus;

  SimpleEventPublisher(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  public <T extends EventHandler<?>> void register(T... eventHandlers) {
    Arrays.stream(eventHandlers)
        .forEach(eventBus::register);
  }

  @Override
  public <T extends EventHandler<?>> void unregister(T... eventHandlers) {
    Arrays.stream(eventHandlers)
        .forEach(eventBus::unregister);
  }

  @Override
  public <E extends DomainEvent<?>> void publish(E... events) {
    Arrays.stream(events)
        .forEach(eventBus::post);
  }

}
