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

import java.util.List;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Double Gauge metric, to report instantaneous measurement of a double value. Gauges can go both up
 * and down. The gauges values can be negative.
 *
 * <p>Example 1: Create a Gauge with default labels.
 *
 * <pre>{@code
 * class YourClass {
 *
 *   private static final MetricCollection metricRegistry = Metrics.getMetricRegistry();
 *
 *   List<LabelKey> labelKeys = Arrays.asList(LabelKey.create("Name", "desc"));
 *
 *   DoubleGauge gauge = metricRegistry.addDoubleGauge("queue_size",
 *                       "Pending jobs", "1", labelKeys);
 *
 *   // It is recommended to keep a reference of a point for manual operations.
 *   DoublePoint defaultPoint = gauge.getDefaultTimeSeries();
 *
 *   void doWork() {
 *      // Your code here.
 *      defaultPoint.add(10);
 *   }
 *
 * }
 * }</pre>
 *
 * <p>Example 2: You can also use labels(keys and values) to track different types of metric.
 *
 * <pre>{@code
 * class YourClass {
 *
 *   private static final MetricCollection metricRegistry = Metrics.getMetricRegistry();
 *
 *   List<LabelKey> labelKeys = Arrays.asList(LabelKey.create("Name", "desc"));
 *   List<LabelValue> labelValues = Arrays.asList(LabelValue.create("Inbound"));
 *
 *   DoubleGauge gauge = metricRegistry.addDoubleGauge("queue_size",
 *                       "Pending jobs", "1", labelKeys);
 *
 *   // It is recommended to keep a reference of a point for manual operations.
 *   DoublePoint inboundPoint = gauge.getOrCreateTimeSeries(labelValues);
 *
 *   void doSomeWork() {
 *      // Your code here.
 *      inboundPoint.set(15);
 *   }
 *
 * }
 * }</pre>
 *
 * @since 0.1.0
 */
@ThreadSafe
public abstract class DoubleGauge {

  /**
   * Creates a {@code TimeSeries} and returns a {@code DoublePoint} if the specified {@code
   * labelValues} is not already associated with this gauge, else returns an existing {@code
   * DoublePoint}.
   *
   * <p>It is recommended to keep a reference to the DoublePoint instead of always calling this
   * method for manual operations.
   *
   * @param labelValues the list of label values. The number of label values must be the same to
   *     that of the label keys passed to {@link MetricCollection#addDoubleGauge}.
   * @return a {@code DoublePoint} the value of single gauge.
   * @throws NullPointerException if {@code labelValues} is null OR any element of {@code
   *     labelValues} is null.
   * @throws IllegalArgumentException if number of {@code labelValues}s are not equal to the label
   *     keys.
   * @since 0.1.0
   */
  public abstract DoublePoint getOrCreateTimeSeries(List<LabelValue> labelValues);

  /**
   * Returns a {@code DoublePoint} for a gauge with all labels not set, or default labels.
   *
   * @return a {@code DoublePoint} for a gauge with all labels not set, or default labels.
   * @since 0.1.0
   */
  public abstract DoublePoint getDefaultTimeSeries();

  /**
   * Removes the {@code TimeSeries} from the gauge metric, if it is present. i.e. references to
   * previous {@code DoublePoint} objects are invalid (not part of the metric).
   *
   * @param labelValues the list of label values.
   * @throws NullPointerException if {@code labelValues} is null or any element of {@code
   *     labelValues} is null.
   * @since 0.1.0
   */
  public abstract void removeTimeSeries(List<LabelValue> labelValues);

  /**
   * Removes all {@code TimeSeries} from the gauge metric. i.e. references to all previous {@code
   * DoublePoint} objects are invalid (not part of the metric).
   *
   * @since 0.1.0
   */
  public abstract void clear();

  /**
   * The value of a single point in the Gauge.TimeSeries.
   *
   * @since 0.1.0
   */
  public abstract static class DoublePoint {

    /**
     * Adds the given value to the current value. The values can be negative.
     *
     * @param amt the value to add
     * @since 0.1.0
     */
    public abstract void add(double amt);

    /**
     * Sets the given value.
     *
     * @param val the new value.
     * @since 0.1.0
     */
    public abstract void set(double val);
  }
}
