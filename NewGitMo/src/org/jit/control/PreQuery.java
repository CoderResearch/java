package org.jit.control;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

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
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

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
            String url="jdbc:sqlserver://localhost:1433;databaseName=examination;user=sa;password=123";
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
