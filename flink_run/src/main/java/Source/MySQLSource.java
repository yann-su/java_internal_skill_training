package Source;

import entity.Student;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.lang.Thread.sleep;

public class MySQLSource extends RichParallelSourceFunction<Student> {


    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean flags = true;


    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        conn = DriverManager.getConnection("jdbc:mysql://42.193.142.13:3306/wiki", "root", "Server2008!");
        String sql = "select id,name,id from wiki.demo";
        ps = conn.prepareStatement(sql);
    }

    @Override
    public void run(SourceContext<Student> sourceContext) throws Exception {

        while (flags){
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("id");
                sourceContext.collect(new Student(id,name,age));
            }
            sleep(10000);
        }



    }

    @Override
    public void cancel() {
        flags = false;
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (conn != null){
            conn.close();
        }
        if (rs != null){
            rs.close();
        }
        if (ps != null){
            ps.close();
        }


    }
}
