package task;

import entity.Message;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;

public class FloodScreen {


    public static void main(String[] args) {


        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        executionEnvironment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        executionEnvironment.setParallelism(1);


        ArrayList<Object> objects = new ArrayList<>();

        DataStreamSource<Message> messageDataStreamSource = executionEnvironment.fromCollection(new ArrayList<Message>() {
        });


    }
}
