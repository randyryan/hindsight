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
import org.basil.twentytwenty.domain.Id;

/**
 * An event sourcing event.
 *
 * @param <ID> type of the ID
 */
public interface Event<ID extends Id<?>> extends DomainEvent<ID> {

}
