package ch.netzwerg.viz.scale;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ScaleOrdinal<D, R> {

    private final List<R> range;

    private final Map<D, R> scale = new LinkedHashMap<>();
    private int currentRangeIndex = 0;

    @SafeVarargs
    public ScaleOrdinal(R... range) {
        this.range = Arrays.asList(range);
    }

    public R toScreen(D domain) {
        if (scale.containsKey(domain)) {
            return scale.get(domain);
        } else {
            R r = nextRangeValue();
            scale.put(domain, r);
            return r;
        }
    }

    private R nextRangeValue() {
        R rangeValue = range.get(currentRangeIndex);

        // advance index, possibly cycling in order to re-use range values
        currentRangeIndex++;
        currentRangeIndex = currentRangeIndex % range.size();

        return rangeValue;
    }

}
