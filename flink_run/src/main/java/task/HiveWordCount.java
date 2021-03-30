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
        String sql = "select * from feat.relation_multi_afi_afiuid_phonenumber_openpay";

        tableEnv.executeSql(sql).print();


    }

}
