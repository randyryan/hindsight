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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

/**
 * The Aggregate Root.
 *
 * <p>The most important purpose of the <em>Aggregate Root</em> is to maintain all invariants inside
 * the <em>Aggregate</em> it's in. Concrete implementation should offer available operations of the
 * <em>Aggregate</em> as public APIs for the outside to use. When an operation is invoked, the
 * <em>Aggregate Root</em> produce a series (or at least one) of <em>Domain Events</em>. These
 * events will then be "applied" to the <em>Aggregate Root</em>, fulfilling the "maintain invariant"
 * purpose.</p>
 *
 * <p>The "applied" <em>Domain Event</em>s is saved as "uncommitted changes" in the <em>Aggregate
 * Root</em> and will be saved to an <em>Event Store</em> when the <em>Aggregate Root</em> is saved
 * to a <em>Repository</em>.</p>
 *
 * <p>Derived from Greg Young's <em>Aggregate Root</em> implementation.</p>
 *
 * @see <a href="https://github.com/gregoryyoung/m-r/blob/master/SimpleCQRS/Domain.cs">Greg Young's
 *      Aggregate Root</a>
 */
public class AggregateRoot<ID extends Id.Uuid> {

  protected final Logger logger = LoggerFactory.getLogger(getClass());

  private final List<DomainEvent<?>> changes = Lists.newArrayList();

  protected ID id;
  protected int version;

  public ID getId() {
    return id;
  }

  public int getVersion() {
    return version;
  }

  public boolean hasUncommittedChanges() {
    return !changes.isEmpty();
  }

  public List<DomainEvent<?>> getUncommittedChanges() {
    return changes;
  }

  public void markChangesAsCommitted() {
    changes.clear();
  }

  public void loadFromHistory(List<DomainEvent<?>> historicalChanges) {
    historicalChanges.forEach(change -> applyChange(change, false));
  }

  protected void applyChange(DomainEvent<?> event) {
    applyChange(event, true);
  }

  protected void applyChange(DomainEvent<?> event, boolean isNew) {
    apply(event);
    if (isNew) {
      changes.add(event);
    }
  }

  /**
   * The Java version of this.AsDynamic().Apply(@event).
   */
  private void apply(DomainEvent<?> event) {
    try {
      Method applyMethod = getClass().getDeclaredMethod("apply", event.getClass());
      applyMethod.setAccessible(true);
      applyMethod.invoke(this, event);
    } catch (NoSuchMethodException e) {
      logger.warn("apply({} event) does not exist.", event.getClass().getSimpleName());
    } catch (IllegalAccessException | InvocationTargetException e) {
      Throwables.throwIfUnchecked(e);
    }
  }

}
