package meta;

import com.github.abel533.database.*;
import com.github.abel533.utils.DBMetadataUtils;

import java.sql.SQLException;
import java.util.List;

public class MetaDatabaseTest {

    public static void main(String[] args) {
        SimpleDataSource dataSource = new SimpleDataSource(
                Dialect.MYSQL,
                "jdbc:mysql://42.193.142.13:3306/",
                "root",
                "Server2008!"
        );
        DBMetadataUtils dbMetadataUtils = null;
        try {
            dbMetadataUtils = new DBMetadataUtils(dataSource);

            DatabaseConfig defaultConfig = dbMetadataUtils.getDefaultConfig();
            List<IntrospectedTable> list = dbMetadataUtils.introspectTables(new DatabaseConfig("nacos_config",null,"config_info"));
//            List<IntrospectedTable> list = dbMetadataUtils.introspectTables(defaultConfig);

            for (IntrospectedTable table : list) {
                System.out.println(table.getName() + ":");
                for (IntrospectedColumn column : table.getAllColumns()) {
                    System.out.println(column.getName() + " - " +
                            column.getJdbcTypeName() + " - " +
                            column.getJavaProperty() + " - " +
                            column.getJavaProperty() + " - " +
                            column.getFullyQualifiedJavaType().getFullyQualifiedName() + " - " +
                            column.getRemarks());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
