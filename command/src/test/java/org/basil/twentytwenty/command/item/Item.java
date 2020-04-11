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

public class Item {

  private String name;

  public Item(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static interface Command extends org.basil.twentytwenty.command.Command {
  }

  public static class CreateItem implements Command {

    private final String name;

    public CreateItem(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

  }

  public static class RenameItem implements Command {

    private final Item item;
    private final String newName;

    public RenameItem(Item item, String newName) {
      this.item = item;
      this.newName = newName;
    }

    public Item getItem() {
      return item;
    }

    public String getNewName() {
      return newName;
    }

  }

}
