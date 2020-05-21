package org.jit.control;

import java.sql.*;

import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAndUpdateHandle implements ActionListener{
    //所要监听的视图
    AddAndUpdateChoiceQuestionView mView;
    //链接数据库所用工具
    Connection con;
    PreparedStatement ppStatement;
    PreQuery query;
    String sql="";
    String url="jdbc:derby:Examination;create=true";

    //写入参数
    String OperateType;//操作类型
    String complexity;//复杂度
    String mAnswer;//答案
    int isSucceed;//判断是否与数据库建立链接
    int id;//试题号
    int type;//试题类型

    public AddAndUpdateHandle() {
        //数据库连接预处理
        query=new PreQuery();
        try{

            con=DriverManager.getConnection(url);
            //建立名为Question的表
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
    //设置所需要的视图
    public void setView(AddAndUpdateChoiceQuestionView addAndUpdateChoiceQuestion)
    {
        this.mView=addAndUpdateChoiceQuestion;
    }
    //点击提交按钮响应
    @Override
    public void actionPerformed(ActionEvent e) {
        try{

            con=DriverManager.getConnection(url);
            OperateType=mView.mOperate.getSelectedItem().toString();
            sql="";
            //判断操作类型
            if(OperateType.equals("添加试题"))
            {
                //使用预处理命令可以更灵活的设置各项的值
                sql="insert into Question values(?,?,?,?,?,?,?,?,?,?,?)";
                id=Integer.valueOf(mView.mQuestionID.getText().trim());
                //判断试题类型
                if(mView.mType.getSelectedItem().toString().equals("单项选择"))
                {
                    //设置试题类型参数
                    type=1;
                    //得到用户选择的答案
                    mAnswer=mView.mAnswerBox.getSelectedItem().toString();
                }else if(mView.mType.getSelectedItem().toString().equals("多项选择"))
                {
                    type=2;
                    StringBuffer sb=new StringBuffer();
                    for(int i=0;i<4;i++)
                    {
                        if(mView.mMultiselectBN[i].isSelected())
                        {
                            sb.append(mView.mMultiselectBN[i].getText().toString().trim());
                        }
                    }
                    //得到多选题多个答案
                    mAnswer=sb.toString();
                }
                ppStatement=con.prepareStatement(sql);
                ppStatement.setInt(1,id); //向数据库中写入该题id
                ppStatement.setString(2,mView.mApplicable.getText().trim());//写入适用工程
                ppStatement.setString(3, mView.mComplexityBox.getSelectedItem().toString());//复杂度
                ppStatement.setInt(4,type);//试题类型
                ppStatement.setString(5,mView.mContext.getText().trim()); //试题内容
                ppStatement.setString(6,mView.mPictureName.getText().trim()); //图片名字(可为空，为空表示无图)
                ppStatement.setString(7,mView.inputA.getText().trim()); //a选项内容
                ppStatement.setString(8,mView.inputB.getText().trim()); //b选项内容
                ppStatement.setString(9,mView.inputC.getText().trim()); //c选项内容
                ppStatement.setString(10,mView.inputD.getText().trim());//d选项内容
                ppStatement.setString(11,mAnswer);//答案
                isSucceed=ppStatement.executeUpdate();//提交数据
                con.close();//关闭与数据库连接

            }else if(OperateType.equals("更新试题"))
            {
                id=Integer.valueOf(mView.mQuestionID.getText().trim());
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
                if(mView.mType.getSelectedItem().toString().equals("单项选择"))
                {
                    type=1;
                    mAnswer=mView.mAnswerBox.getSelectedItem().toString();
                }else if(mView.mType.getSelectedItem().toString().equals("多项选择"))
                {
                    type=2;
                    StringBuffer sb=new StringBuffer();
                    for(int i=0;i<4;i++)
                    {
                        if(mView.mMultiselectBN[i].isSelected())
                        {
                            sb.append(mView.mMultiselectBN[i].getText().toString().trim());
                        }
                    }
                    mAnswer=sb.toString();
                }else{
                    //如果用户未选择操作类型则进行提示
                    JOptionPane.showMessageDialog
                            (null,""+"请选择试题类型！","消息对话框", JOptionPane.WARNING_MESSAGE);
                }
                ppStatement=con.prepareStatement(sql);
                ppStatement.setString(1,mView.mApplicable.getText().trim());
                ppStatement.setString(2, mView.mComplexityBox.getSelectedItem().toString());
                ppStatement.setInt(3,type);
                ppStatement.setString(4,mView.mContext.getText().trim());
                ppStatement.setString(5,mView.mPictureName.getText().trim());
                ppStatement.setString(6,mView.inputA.getText().trim());
                ppStatement.setString(7,mView.inputB.getText().trim());
                ppStatement.setString(8,mView.inputC.getText().trim());
                ppStatement.setString(9,mView.inputD.getText().trim());
                ppStatement.setString(10,mAnswer);
                isSucceed=ppStatement.executeUpdate();
                con.close();
            }else{
                JOptionPane.showMessageDialog
                        (null,""+"请选择操作类型！","消息对话框", JOptionPane.WARNING_MESSAGE);
            }
        }catch(SQLException exception)
        {
            //对数据库操作失败时弹出对话框进行提示
            JOptionPane.showMessageDialog
                    (null,""+exception,"消息对话框", JOptionPane.WARNING_MESSAGE);
        }
        //根据返回值可以判断如果isSucceed=0则表示操作失败，不为0表示操作成功
        if(isSucceed!=0)
        {
            //操作成功则对话框显示刚刚插入或更新的数据
            query.setDatabaseName("Examination");
            query.setSQL("select * from Question where id="+id+"");
            query.startQuery();
            //得到字段值
            String ziduan[] =query.getColumnName();
            //得到记录
            String [][]record =query.getRecord();
            //对话框中显示操作后的各项值
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