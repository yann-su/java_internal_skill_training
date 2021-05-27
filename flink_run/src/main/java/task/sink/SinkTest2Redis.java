package task.sink;

import entity.Student;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;
import org.apache.flink.util.Collector;

public class SinkTest2Redis {

    public static void main(String[] args) throws Exception {

        // the host and the port to connect to
        final String hostname;
        final int port;
        try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            hostname = params.has("hostname") ? params.get("hostname") : "42.193.142.13";
            port = params.has("port") ? Integer.parseInt(params.get("hostname")) : 9999;
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
        DataStreamSource<String> stringDataStreamSource = env.socketTextStream(hostname, port, "\n");

        SingleOutputStreamOperator<Student> returns = stringDataStreamSource.flatMap((String a, Collector<Student> out) ->
        {
            String[] s = a.split(" ");
            int s0 = Integer.parseInt(s[0]);
            String s1 = s[1];
            int s2 = Integer.parseInt(s[2]);

            out.collect(new Student(s0, s1, s2,System.currentTimeMillis()));

        }).returns(Student.class);


        returns.print();

        FlinkJedisPoolConfig config = new FlinkJedisPoolConfig.Builder()
                .setHost("42.193.142.13").setPort(6379).build();

        //写入到redis的数据
        returns.addSink(new RedisSink<>(config, new RedisMapper<Student>() {
            @Override
            public RedisCommandDescription getCommandDescription() {
                return new RedisCommandDescription(RedisCommand.HSET,"person");
            }

            @Override
            public String getKeyFromData(Student student) {
                return "User:Id:"+student.getId()+"";
            }

            @Override
            public String getValueFromData(Student student) {
                return student.getName();
            }
        }));

        env.execute("");






    }
}
