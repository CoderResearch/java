package org.jit.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class PreQueryDelete {
    String databaseName;
    String SQL;
    Connection con;
    PreparedStatement preSql;
    public PreQueryDelete(){
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
    public void startQuery()
    {

        try{
            String url="jdbc:derby:"+databaseName+" ;create=true";
            con=DriverManager.getConnection(url);
            preSql=con.prepareStatement(SQL);
            preSql.executeUpdate();
        }catch(SQLException e)
        {
            System.out.println(e);

        }
    }



}
