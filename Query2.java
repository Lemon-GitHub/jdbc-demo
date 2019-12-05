import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Query2 {
    public static void main(String[] args) {
            //queryByName("唐三藏' or '1'='1");
            queryByName("唐三藏");
    }

    public static void queryByName(String stuName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            try {
                // 创建数据库连接Connection
                connection = DBUtil.getConnection2();
                // 根据数据库连接创建操作命令对象Statement
                // 参数使用?占位符 多参数占位符一个？
                String sql = "select id,name,chinese,math,english from exam_result" +
                        " where name=?";
                //API
                statement = connection.prepareStatement(sql);
                //第一参数：第几个占位符 第二个参数：传入的参数
                statement.setString(1, stuName);
                // 操作命令对象执行sql语句，返回结果集resultSet
                // resultSet类似  List<Map<String, Object>>
                resultSet = statement.executeQuery();//使用无参方法

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
