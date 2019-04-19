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
import openconsensus.internal.Utils;
import openconsensus.resource.Resource;

/**
 * No-op implementations of metrics classes.
 *
 * @since 0.1.0
 */
public final class NoopMetrics {
  private NoopMetrics() {}

  /**
   * Returns an instance that is a no-op implementations for {@link Meter}.
   *
   * @return an instance that is a no-op implementations for {@link Meter}
   * @since 0.1.0
   */
  public static Meter newNoopMeter() {
    return new NoopMeter();
  }

  private static final class NoopMeter extends Meter {

    @Override
    public MetricCollection.Builder createMetricCollection() {
      return new NoopMetricCollection.Builder();
    }
  }

  private static final class NoopMetricCollection extends MetricCollection {
    @Override
    public LongGauge addLongGauge(String name, MetricOptions options) {
      return NoopLongGauge.create(
          Utils.checkNotNull(name, "name"),
          options.getDescription(),
          options.getUnit(),
          options.getLabelKeys());
    }

    @Override
    public DoubleGauge addDoubleGauge(String name, MetricOptions options) {
      return NoopDoubleGauge.create(
          Utils.checkNotNull(name, "name"),
          options.getDescription(),
          options.getUnit(),
          options.getLabelKeys());
    }

    @Override
    public DerivedLongGauge addDerivedLongGauge(String name, MetricOptions options) {
      return NoopDerivedLongGauge.create(
          Utils.checkNotNull(name, "name"),
          options.getDescription(),
          options.getUnit(),
          options.getLabelKeys());
    }

    @Override
    public DerivedDoubleGauge addDerivedDoubleGauge(String name, MetricOptions options) {
      return NoopDerivedDoubleGauge.create(
          Utils.checkNotNull(name, "name"),
          options.getDescription(),
          options.getUnit(),
          options.getLabelKeys());
    }

    private static final class Builder extends MetricCollection.Builder {
      private static final MetricCollection METRIC_COLLECTION = new NoopMetricCollection();

      @Override
      public MetricCollection.Builder setComponent(String component) {
        Utils.checkNotNull(component, "component");
        return null;
      }

      @Override
      public MetricCollection.Builder setResource(Resource resource) {
        Utils.checkNotNull(resource, "resource");
        return null;
      }

      @Override
      public MetricCollection build() {
        return METRIC_COLLECTION;
      }
    }
  }

  /** No-op implementations of LongGauge class. */
  private static final class NoopLongGauge extends LongGauge {
    private final int labelKeysSize;

    static NoopLongGauge create(
        String name, String description, String unit, List<LabelKey> labelKeys) {
      return new NoopLongGauge(name, description, unit, labelKeys);
    }

    /** Creates a new {@code NoopLongPoint}. */
    NoopLongGauge(String name, String description, String unit, List<LabelKey> labelKeys) {
      labelKeysSize = labelKeys.size();
    }

    @Override
    public NoopLongPoint getOrCreateTimeSeries(List<LabelValue> labelValues) {
      Utils.checkListElementNotNull(Utils.checkNotNull(labelValues, "labelValues"), "labelValue");
      Utils.checkArgument(
          labelKeysSize == labelValues.size(), "Label Keys and Label Values don't have same size.");
      return new NoopLongPoint();
    }

    @Override
    public NoopLongPoint getDefaultTimeSeries() {
      return new NoopLongPoint();
    }

    @Override
    public void removeTimeSeries(List<LabelValue> labelValues) {
      Utils.checkNotNull(labelValues, "labelValues");
    }

    @Override
    public void clear() {}

    /** No-op implementations of LongPoint class. */
    private static final class NoopLongPoint extends LongPoint {
      private NoopLongPoint() {}

      @Override
      public void add(long amt) {}

      @Override
      public void set(long val) {}
    }
  }

  /** No-op implementations of DoubleGauge class. */
  private static final class NoopDoubleGauge extends DoubleGauge {
    private final int labelKeysSize;

    static NoopDoubleGauge create(
        String name, String description, String unit, List<LabelKey> labelKeys) {
      return new NoopDoubleGauge(name, description, unit, labelKeys);
    }

    /** Creates a new {@code NoopDoublePoint}. */
    NoopDoubleGauge(String name, String description, String unit, List<LabelKey> labelKeys) {
      Utils.checkNotNull(name, "name");
      Utils.checkNotNull(description, "description");
      Utils.checkNotNull(unit, "unit");
      Utils.checkListElementNotNull(Utils.checkNotNull(labelKeys, "labelKeys"), "labelKey");
      labelKeysSize = labelKeys.size();
    }

    @Override
    public NoopDoublePoint getOrCreateTimeSeries(List<LabelValue> labelValues) {
      Utils.checkListElementNotNull(Utils.checkNotNull(labelValues, "labelValues"), "labelValue");
      Utils.checkArgument(
          labelKeysSize == labelValues.size(), "Label Keys and Label Values don't have same size.");
      return new NoopDoublePoint();
    }

    @Override
    public NoopDoublePoint getDefaultTimeSeries() {
      return new NoopDoublePoint();
    }

    @Override
    public void removeTimeSeries(List<LabelValue> labelValues) {
      Utils.checkNotNull(labelValues, "labelValues");
    }

    @Override
    public void clear() {}

    /** No-op implementations of DoublePoint class. */
    private static final class NoopDoublePoint extends DoublePoint {
      private NoopDoublePoint() {}

      @Override
      public void add(double amt) {}

      @Override
      public void set(double val) {}
    }
  }

  /** No-op implementations of DerivedLongGauge class. */
  private static final class NoopDerivedLongGauge extends DerivedLongGauge {
    private final int labelKeysSize;

    static NoopDerivedLongGauge create(
        String name, String description, String unit, List<LabelKey> labelKeys) {
      return new NoopDerivedLongGauge(name, description, unit, labelKeys);
    }

    /** Creates a new {@code NoopDerivedLongGauge}. */
    NoopDerivedLongGauge(String name, String description, String unit, List<LabelKey> labelKeys) {
      Utils.checkNotNull(name, "name");
      Utils.checkNotNull(description, "description");
      Utils.checkNotNull(unit, "unit");
      Utils.checkListElementNotNull(Utils.checkNotNull(labelKeys, "labelKeys"), "labelKey");
      labelKeysSize = labelKeys.size();
    }

    @Override
    public <T> void createTimeSeries(
        List<LabelValue> labelValues, T obj, ToLongFunction<T> function) {
      Utils.checkListElementNotNull(Utils.checkNotNull(labelValues, "labelValues"), "labelValue");
      Utils.checkArgument(
          labelKeysSize == labelValues.size(), "Label Keys and Label Values don't have same size.");
      Utils.checkNotNull(function, "function");
    }

    @Override
    public void removeTimeSeries(List<LabelValue> labelValues) {
      Utils.checkNotNull(labelValues, "labelValues");
    }

    @Override
    public void clear() {}
  }

  /** No-op implementations of DerivedDoubleGauge class. */
  private static final class NoopDerivedDoubleGauge extends DerivedDoubleGauge {
    private final int labelKeysSize;

    static NoopDerivedDoubleGauge create(
        String name, String description, String unit, List<LabelKey> labelKeys) {
      return new NoopDerivedDoubleGauge(name, description, unit, labelKeys);
    }

    /** Creates a new {@code NoopDerivedDoubleGauge}. */
    NoopDerivedDoubleGauge(String name, String description, String unit, List<LabelKey> labelKeys) {
      Utils.checkNotNull(name, "name");
      Utils.checkNotNull(description, "description");
      Utils.checkNotNull(unit, "unit");
      Utils.checkListElementNotNull(Utils.checkNotNull(labelKeys, "labelKeys"), "labelKey");
      labelKeysSize = labelKeys.size();
    }

    @Override
    public <T> void createTimeSeries(
        List<LabelValue> labelValues, T obj, ToDoubleFunction<T> function) {
      Utils.checkListElementNotNull(Utils.checkNotNull(labelValues, "labelValues"), "labelValue");
      Utils.checkArgument(
          labelKeysSize == labelValues.size(), "Label Keys and Label Values don't have same size.");
      Utils.checkNotNull(function, "function");
    }

    @Override
    public void removeTimeSeries(List<LabelValue> labelValues) {
      Utils.checkNotNull(labelValues, "labelValues");
    }

    @Override
    public void clear() {}
  }
}
