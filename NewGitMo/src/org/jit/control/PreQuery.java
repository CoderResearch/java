package org.jit.control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

//需要一个工具类PreQuery类，它负责从数据库中获取字段值和我们想要查询的数据，
// 我们可以通过他来查看数据是否被插入到表中。
public class PreQuery {
    String databaseName;
    String SQL;
    String[] columnName;
    String[][] record;
    Connection con;
    PreparedStatement preSql;
    ResultSet rs;
    public PreQuery(){
        try
        {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void setDatabaseName(String name)
    {
        databaseName=name.trim();
    }
    public void setSQL(String SQL)
    {
        this.SQL=SQL.trim();
    }
    public String[] getColumnName()
    {
        return columnName;
    }
    public String[][] getRecord()
    {
        return record;
    }
    public void startQuery()
    {

        try{
            String url="jdbc:derby:"+databaseName+" ;create=true";
            con=DriverManager.getConnection(url);
            //设置游标可以随意跳转，这样可以实现从任意一行读取数据
            preSql=con.prepareStatement(SQL,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=preSql.executeQuery();
            ResultSetMetaData metaData=rs.getMetaData();
            int ziduanNum=metaData.getColumnCount();
            columnName=new String[ziduanNum];
            for(int i=1;i<=ziduanNum;i++)
            {
                columnName[i-1]=metaData.getColumnName(i);
            }
            rs.last();
            int dataNum=rs.getRow();
            rs.beforeFirst();
            int i=0;
            record=new String[dataNum][ziduanNum];
            while(rs.next())
            {
                for(int j=1;j<=ziduanNum;j++)
                {
                    record[i][j-1]=rs.getString(j);
                }
                i++;
            }
            con.close();
        }catch(SQLException e)
        {
            System.out.println(e);
        }
    }
}
