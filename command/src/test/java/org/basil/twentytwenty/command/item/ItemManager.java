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
package org.basil.twentytwenty.command.item;

import org.basil.twentytwenty.command.CommandDispatcher;
import org.basil.twentytwenty.command.CommandExecutor;

import com.google.common.eventbus.Subscribe;

public class ItemManager implements CommandExecutor<Item.Command> {

  private Item item;

  @Subscribe
  @Override
  public void execute(Item.Command command) {
  }

  @Subscribe
  public void create(Item.CreateItem command) {
    item = new Item(command.getName());
  }

  @Subscribe
  private void rename(Item.RenameItem command) {
    item.setName(command.getNewName());
  }

  public boolean hasItem() {
    return item != null;
  }

  public Item getItem() {
    return item;
  }

  public UI getUI(CommandDispatcher commandDispatcher) {
    return new UI(commandDispatcher);
  }

  public class UI {

    private final CommandDispatcher commandDispatcher;

    public UI(CommandDispatcher commandDispatcher) {
      this.commandDispatcher = commandDispatcher;
    }

    public void createItem(String name) {
      commandDispatcher.dispatch(new Item.CreateItem(name));
    }

    public void renameItem(String newName) {
      commandDispatcher.dispatch(new Item.RenameItem(item, newName));
    }

  }

}
