package org.jit.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
public class AddAndUpdateJudgmentQuestionHandle implements ActionListener{

    //判断题视图
    AddAndUpdateJudgmentQuestionView mView;
    //数据库连接工具
    Connection con;
    PreparedStatement ppStatement;
    PreQuery query;
    String sql="";
    String url="jdbc:sqlserver://192.168.220.1:1433;databaseName=MyDB_3;user=sa;password=123";

    //写入参数
    String OperateType;
    String complexity;
    String mAnswer;
    int isSucceed;
    int type=3;
    int id;

    public AddAndUpdateJudgmentQuestionHandle()
    {
        //初始化数据库连接
        query=new PreQuery();
        try{

            con=DriverManager.getConnection(url);
            sql ="create table Question"+
                    "(id int primary key not null,"+
                    "adaptEngineering varchar(20)," +
                    "complexity  varchar(20),"+
                    "type  int,"+
                    "content  varchar(500),"+
                    "pic varchar(300),"+
                    "a varchar(300),"+
                    "b varchar(300),"+
                    "c varchar(300),"+
                    "d varchar(300),"+
                    "answer varchar(20))";
            ppStatement=con.prepareStatement(sql);
            ppStatement.executeUpdate();
            con.close();

        }catch(SQLException exception)
        {
            System.out.println(exception);
        }

    }
    //设置所需监视视图
    public void setView(AddAndUpdateJudgmentQuestionView view)
    {
        mView=view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            con=DriverManager.getConnection(url);
            OperateType=mView.mOperate.getSelectedItem().toString();
            sql="";
            //判断操作类型并执行相应操作
            if(OperateType.equals("添加试题"))
            {
                sql="insert into Question values(?,?,?,?,?,?,?,?,?,?,?)";
                id=Integer.valueOf(mView.mQuestionID.getText().trim());
                if(mView.radioButtonTrue.isSelected())
                {
                    mAnswer="A";
                }else {
                    mAnswer="B";
                }
                ppStatement=con.prepareStatement(sql);
                ppStatement.setInt(1,id);
                ppStatement.setString(2,mView.mApplicable.getText().trim());
                ppStatement.setString(3, mView.mComplexityBox.getSelectedItem().toString());
                ppStatement.setInt(4,type);
                ppStatement.setString(5,mView.mContext.getText().trim());
                ppStatement.setString(6,mView.mPictureName.getText().trim());
                ppStatement.setString(7,"正确");
                ppStatement.setString(8,"错误");
                ppStatement.setString(9,"");
                ppStatement.setString(10,"");
                ppStatement.setString(11,mAnswer);
                isSucceed=ppStatement.executeUpdate();
                con.close();

            }else if(OperateType.equals("更新试题"))
            {
                sql="update Question set "
                        + "adaptEngineering=?,"
                        + "complexity=?,"
                        + "type=?,"
                        + "content=?,"
                        +"pic=?,"
                        +"a=?,"
                        +"b=?,"
                        +"c=?,"
                        +"d=?,"
                        +"answer=? "
                        +"where id ="
                        +id;
                id=Integer.valueOf(mView.mQuestionID.getText().trim());
                if(mView.radioButtonTrue.isSelected())
                {
                    mAnswer="A";
                }else {
                    mAnswer="B";
                }
                ppStatement=con.prepareStatement(sql);
                ppStatement.setString(1,mView.mApplicable.getText().trim());
                ppStatement.setString(2, mView.mComplexityBox.getSelectedItem().toString());
                ppStatement.setInt(3,type);
                ppStatement.setString(4,mView.mContext.getText().trim());
                ppStatement.setString(5,mView.mPictureName.getText().trim());
                ppStatement.setString(6,"正确");
                ppStatement.setString(7,"错误");
                ppStatement.setString(8,"");
                ppStatement.setString(9,"");
                ppStatement.setString(10,mAnswer);
                isSucceed=ppStatement.executeUpdate();
                con.close();
            }else{
                JOptionPane.showMessageDialog
                        (null,""+"请选择操作类型！","消息对话框", JOptionPane.WARNING_MESSAGE);
            }
        }catch(SQLException exception)
        {
            System.out.println(exception);
            JOptionPane.showMessageDialog
                    (null,""+exception,"消息对话框", JOptionPane.WARNING_MESSAGE);
        }
        if(isSucceed!=0)
        {
            query.setDatabaseName("Examination");
            query.setSQL("select * from Question where id="+id+"");
            query.startQuery();

            String ziduan[] =query.getColumnName();
            String [][]record =query.getRecord();

            DialogOne dialog = new DialogOne();
            dialog.setZiduan(ziduan);
            dialog.setRecord(record);
            dialog.init();
            dialog.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog
                    (null,"添加试题失败","消息对话框", JOptionPane.WARNING_MESSAGE);
        }
    }

}