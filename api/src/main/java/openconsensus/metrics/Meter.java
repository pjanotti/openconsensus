/*
 * Copyright 2019, OpenConsensus Authors
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

package openconsensus.metrics;

/** Entry point fot metrics API, this object allows to create new {@link MetricCollection}. */
public abstract class Meter {

  /**
   * Returns a new builder for a {@code MetricCollection}.
   *
   * @return a new builder for a {@code MetricCollection}.
   */
  public abstract MetricCollection.Builder createMetricCollection();
}
