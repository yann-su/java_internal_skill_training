package task;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class SocketWordCount {



    public static void main(String[] args) throws Exception {
        // the host and the port to connect to
        final String hostname;
        final int port;
        try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            hostname = params.has("hostname") ? params.get("hostname") : "localhost";
            port = params.getInt("port");
        } catch (Exception e) {
            System.err.println(
                    "No port specified. Please run 'SocketWindowWordCount "
                            + "--hostname <hostname> --port <port>', where hostname (localhost by default) "
                            + "and port is the address of the text server");
            System.err.println(
                    "To start a simple text server, run 'netcat -l <port>' and "
                            + "type the input text into the command line");
            return;
        }
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> stringDataStreamSource = env.socketTextStream(hostname, port, "\n").setParallelism(1);


        DataStream<Tuple2<String,Integer>> flatMapStream = stringDataStreamSource.flatMap((String a, Collector<Tuple2<String,Integer>> out) ->
                Arrays.stream(a.split(" ")).forEach(x -> out.collect(Tuple2.of(x,1)))
        ).returns(Types.TUPLE(Types.STRING,Types.INT)).setParallelism(2).slotSharingGroup("red");


        KeyedStream<Tuple2<String, Integer>, String> tuple2StringKeyedStream = flatMapStream.keyBy(x -> x.f0);
//
        DataStream<Tuple2<String, Integer>> sumStream = tuple2StringKeyedStream.sum(1).setParallelism(1).setParallelism(1).slotSharingGroup("he");
//

        sumStream.print();

        env.execute("socket stream");
    }
}
