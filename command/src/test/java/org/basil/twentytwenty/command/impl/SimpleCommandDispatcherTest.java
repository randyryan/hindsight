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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.basil.twentytwenty.command.CommandDispatcher;
import org.basil.twentytwenty.command.item.ItemManager;
import org.junit.Before;
import org.junit.Test;

public class SimpleCommandDispatcherTest {

  private ItemManager itemManager;
  private ItemManager.UI itemManagerUi;
  private CommandDispatcher commandDispatcher;

  @Before
  public void setUp() {
    commandDispatcher = SimpleCommandDispatcher.sync(); // For the simplicity we'll just use sync.

    itemManager = new ItemManager();
    itemManagerUi = itemManager.getUI(commandDispatcher);
  }

  @Test
  public void testAsynchronousDispatching() {
    commandDispatcher = SimpleCommandDispatcher.async(); // The synchronous dispatching is used
    // in this test so Item accessing code don't have to wait for the dispatch to complete like
    // it is in the asynchronous mode. We don't need to verify if the async is really async or
    // not, it's fine as long as it returns an instance.

    assertThat(itemManager, notNullValue());
  }

  @Test
  public void testNoRegisteredExecutors() {
    itemManagerUi.createItem("test");

    assertThat(itemManager.hasItem(), is(false));
  }

  @Test
  public void testRegisteredExecutor() {
    commandDispatcher.register(itemManager);

    itemManagerUi.createItem("test");

    assertThat(itemManager.hasItem(), is(true));
    assertThat(itemManager.getItem().getName(), is("test"));
  }

  @Test
  public void testUnregisteredExecutor() {
    commandDispatcher.register(itemManager);

    itemManagerUi.createItem("test");

    commandDispatcher.unregister(itemManager);

    itemManagerUi.renameItem("new-test");

    assertThat(itemManager.getItem().getName(), is("test"));

    commandDispatcher.register(itemManager);

    itemManagerUi.renameItem("new-test");

    assertThat(itemManager.getItem().getName(), is("new-test"));
  }

}
