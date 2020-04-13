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
package org.basil.twentytwenty.domain;

import java.util.UUID;
import java.util.function.Supplier;

/**
 * ID
 *
 * @param <T> type of the ID object
 */
public abstract class Id<T> {

  /**
   * The wrapped ID object.
   */
  protected final T id;

  /**
   * Constructs the ID object by the default way in respect of its type.
   */
  protected Id() {
    this.id = getDefault();
  }

  /**
   * Constructs the ID object by the a {@link String} object, usually a {@code fromString} method.
   *
   * @param id in the String representation
   */
  protected Id(String id) {
    this.id = fromString(id);
  }

  /**
   * Constructs the ID object in help with the specified {@link Supplier} object.
   *
   * @param idSupplier to supply the ID object
   */
  protected Id(Supplier<T> idSupplier) {
    this.id = idSupplier.get();
  }

  /**
   * Constructs the ID object by direct specify.
   *
   * @param id the ID object
   */
  protected Id(T id) {
    this.id = id;
  }

  /**
   * Override to allow to generate a default ID.
   *
   * @return the wrapped ID object
   */
  protected abstract T getDefault();

  /**
   * Override to allow to generate the ID from a {@link String} object.
   *
   * @param id
   * @return
   */
  protected abstract T fromString(String id);

  // Object overrides

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (!o.getClass().equals(getClass())) {
      return false;
    }
    return getClass().cast(o).id.equals(id);
  }

  @Override
  public String toString() {
    return id.toString();
  }

  /**
   * An ordered ID. Initially named as Sequential, but it's too long.
   */
  public static class Order extends Id<Long> {

    public Order() {
      super(new Long(0));
    }

    public Order(String id) {
      super(id);
    }

    public Order(Long id) {
      super(id);
    }

    @Override
    protected Long getDefault() {
      return Long.valueOf(0);
    }

    @Override
    protected Long fromString(String string) {
      return Long.parseLong(string);
    }

  }

  /**
   * An token, though it can be any string.
   */
  public static class Token extends Id<String> {

    public Token() {
      super();
    }

    public Token(String id) {
      super(() -> id);
    }

    @Override
    protected String getDefault() {
      return "";
    }

    @Override
    protected String fromString(String string) {
      return string;
    }

  }

  /**
   * Universally unique identifier.
   */
  public static class Uuid extends Id<UUID> {

    public Uuid() {
      super();
    }

    public Uuid(String string) {
      super(string);
    }

    public Uuid(Supplier<UUID> idSupplier) {
      super(idSupplier);
    }

    public Uuid(UUID id) {
      super(id);
    }

    @Override
    protected UUID getDefault() {
      return UUID.randomUUID();
    }

    @Override
    protected UUID fromString(String string) {
      return UUID.fromString(string);
    }

  }

}
