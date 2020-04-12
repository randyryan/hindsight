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
package org.basil.twentytwenty.command.impl;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.basil.twentytwenty.command.Command;
import org.basil.twentytwenty.command.CommandDispatcher;
import org.basil.twentytwenty.command.CommandExecutor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

/**
 * A very simple implementation of CommandDispatcher using Google Guava's EventBus.
 */
public class SimpleCommandDispatcher implements CommandDispatcher {

  public static CommandDispatcher async() {
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    return new SimpleCommandDispatcher(new AsyncEventBus("commandDispatcher", executorService));
  }

  public static CommandDispatcher sync() {
    return new SimpleCommandDispatcher(new EventBus("commandDispatcher"));
  }

  private final EventBus eventBus;

  SimpleCommandDispatcher(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public <T extends CommandExecutor> void register(T... commandExecutors) {
    Arrays.stream(commandExecutors)
        .forEach(eventBus::register);
  }

  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public <T extends CommandExecutor> void unregister(T... commandExecutors) {
    Arrays.stream(commandExecutors)
        .forEach(eventBus::unregister);
  }

  @Override
  public void dispatch(Command... commands) {
    Arrays.stream(commands)
        .forEach(eventBus::post);
  }

}
