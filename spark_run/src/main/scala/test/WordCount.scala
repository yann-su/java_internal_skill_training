package test

import conf.Args
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{Row, SparkSession}

object WordCount {


  def main(args: Array[String]): Unit = {


    val arguments = Args(args)
    val startPartition = arguments.required("start")
    val endPartition = arguments.required("end")
//    val tdwSinkDb = arguments.required("tdwDB")
//    val tdwSinkTable = arguments.required("tdwTable")
    val mysqlSourceDb = arguments.required("mysqlDB")
    val mysqlSourceTable = arguments.required("mysqlTable")
//    val targetDb = arguments.required("clickhouseDB")
//    val targetTable = arguments.required("clickhouseTable")
//    val partitionKey = arguments.required("partitionedBy")
//    val orderByKey = arguments.required("orderBy")


    val spark = SparkSession
      .builder
      .appName("Mysql2Tdw")
      .master("local")
      .getOrCreate()

    import spark.implicits._

//    val sql = "select * from "+mysqlSourceDb+"."+mysqlSourceTable + "+ where " + " create_time > " + startPartition + " and create_time < " + endPartition
    val sql = "select * from " +mysqlSourceDb+"."+mysqlSourceTable +" limit 1"
    val jdbcDF = spark.read
      .format("jdbc")
      .option("url", "jdbc:mysql://9.134.36.79:3306/"+mysqlSourceDb)
      .option("dbtable", mysqlSourceTable)
      .option("user", "root")
      .option("password", "1qaz2wsx#EDC")
      .option("query", sql)
      .load()



    jdbcDF.printSchema()
    jdbcDF.select("*").show(100)
//    jdbcDF.select("*").show(100)

//    val names = jdbcDF.schema.fieldNames
//    for (elem <- names) {
//      println(elem)
//    }




  }

}
