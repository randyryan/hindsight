/*
 * Copyright 2020 Li Wan
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
package org.basil.twentytwenty.command;

/**
 * The CommandDispatcher interface.
 *
 * All Command executors (or handlers) should be registered to at least one CommandDispatcher
 * in order to receive Commands to execute. When the dispatch is invoked on CommandDispatcher
 * it should send the Command to all of its registered Command executors (or handlers).
 */
public interface CommandDispatcher {

  /**
   * Register the CommandExecutors to receive Commands from this dispatcher.
   *
   * @param <T> type of the CommandExecutors
   * @param commandExecutors to register to this dispatcher
   */
  @SuppressWarnings({ "rawtypes", "unchecked" })
  <T extends CommandExecutor> void register(T... commandExecutors);

  /**
   * Unregister the CommandExecutors that receive Commands from this dispatcher.
   *
   * @param <T> type of the CommandExecutors
   * @param commandExecutors to unregister from this dispatcher
   */
  <T extends CommandExecutor> void unregister(T... commandExecutors);

  /**
   * Dispatches the specified Command to all registered CommandExecutors.
   *
   * @param commands to dispatch
   */
  void dispatch(Command... commands);

}
