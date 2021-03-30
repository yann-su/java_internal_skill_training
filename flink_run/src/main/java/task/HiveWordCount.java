package task;

import org.apache.flink.api.common.JobStatus;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.catalog.hive.HiveCatalog;

import java.util.concurrent.CompletableFuture;

public class HiveWordCount {

    public static void main(String[] args) {

//        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
        TableEnvironment tableEnv = TableEnvironment.create(settings);
        String name            = "feat";
        String defaultDatabase = "feat";
        String hiveConfDir     = "/Users/backbook/opt/hive-conf";
        HiveCatalog hive = new HiveCatalog(name, defaultDatabase, hiveConfDir);

        tableEnv.registerCatalog("feat",hive);
        tableEnv.useCatalog("feat");
//        String sql = "insert into feat.relation_multi_afi_afiuid_phonenumber_openpay select" +
//                " cast(19 as BIGINT),CAST('21311' as STRING),CAST(3123131 as BIGINT),CAST(31231 as BIGINT),CAST(3213141 AS BIGINT),CAST(2013 AS INT) ";

        String sql = "select * from tableA";
        tableEnv.executeSql(sql).print();


    }

}
