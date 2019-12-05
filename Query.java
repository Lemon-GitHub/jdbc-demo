import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class Query {

    public static void main(String[] args) {
        queryByName("唐三藏' or '1'='1");//查询出全部信息 有安全隐患 解决：采用PreparedStatement
    }

    public static void queryByName(String stuName) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                // 创建数据库连接Connection
                connection = DBUtil.getConnection2();
                // 根据数据库连接创建操作命令对象Statement  statement用于执行不带参数的简单sql语句
                statement = connection.createStatement();
                String sql = "select id,name,chinese,math,english from exam_result" +
                        " where name='"+stuName+"'"; //传入字符串 加单引号
                // 操作命令对象执行sql语句，返回结果集resultSet
                // resultSet类似  List<Map<String, Object>>
                resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    BigDecimal chinese = resultSet.getBigDecimal("chinese");
                    BigDecimal math = resultSet.getBigDecimal("math");
                    BigDecimal english = resultSet.getBigDecimal("english");
                    System.out.printf("id=%s, name=%s, chinese=%s, " +
                                    "math=%s, english=%s", id, name, chinese,
                            math, english);
                    System.out.println();
                }
            } finally {
                // 释放资源
                DBUtil.close(connection, statement, resultSet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
