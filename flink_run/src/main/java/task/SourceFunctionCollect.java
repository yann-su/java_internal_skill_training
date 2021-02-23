package task;

import entity.SensorReading;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

public class SourceFunctionCollect extends RichParallelSourceFunction<SensorReading> {
    @Override
    public void run(SourceContext<SensorReading> sourceContext) throws Exception {
        sourceContext.collect(new SensorReading("sen01",1313L,10.2));
    }

    @Override
    public void cancel() {

    }
}
