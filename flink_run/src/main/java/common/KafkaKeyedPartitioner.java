package common;

import org.apache.flink.streaming.connectors.kafka.partitioner.FlinkKafkaPartitioner;
import org.apache.kafka.common.utils.Utils;

public class KafkaKeyedPartitioner<T> extends FlinkKafkaPartitioner<T> {

    public int partition(T record, byte[] keyBytes, byte[] value, String targetTopic, int[] partitions) {
        int numPartitions = partitions.length;
        if (keyBytes == null) {
            return 0;
        } else {
            // hash the keyBytes to choose a partition
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
        }
    }
}