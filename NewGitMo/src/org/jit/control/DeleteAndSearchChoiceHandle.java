package org.jit.control;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
public class DeleteAndSearchChoiceHandle implements ActionListener{

    DeleteAndSerachChoiceQuestionView mView;
    Connection con;
    PreparedStatement ppStatement;
    PreQuery query;
    PreQueryDelete queryDelete;
    String sql="";
    String id;
    String OperateType;
    String complexity;
    String adaptEngineering;
    int isSucceed;
    String url="jdbc:sqlserver://localhost:1433;databaseName=examination;user=sa;password=123";
    String SQL="";
    String[] ziduan;//字段值
    String[][] record;//记录值
    int isGoing;//判断弹出对话框后用户是否点击继续

    public DeleteAndSearchChoiceHandle ()
    {
        query=new PreQuery();
        queryDelete=new PreQueryDelete();
    }
    //绑定DeleteAndSerachChoiceQuestionView视图
    public void setView(DeleteAndSerachChoiceQuestionView deleteAndSerachChoiceQuestionView)
    {
        this.mView=deleteAndSerachChoiceQuestionView;

    }
    //重绘表格
    private void RePaintTable(String SQL) {
        query.setDatabaseName("examination");
        query.setSQL(SQL);
        query.startQuery();
        ziduan=query.getColumnName();
        record=query.getRecord();
        mView.table=new JTable(this.record,this.ziduan);
        mView.js.setViewportView(mView.table);
    }
    //精确查询
    private void accurateSearch()
    {
        id=mView.mQuestionID.getText().toString().trim();
        SQL="select * from Question where id="+id+"";
        RePaintTable(SQL);
    }
    //模糊查询
    private void dimSearch() {
        adaptEngineering=mView.mApplicable.getText().toString().trim();
        StringBuffer sb=new StringBuffer();
        int mark=0;
        for(int i=0;i<4;i++)
        {
            if(mView.mComplexityBN[i].isSelected())
            {
                //如果为全选则置标记为1，后面直接可以按照查询全部进行
                if(mView.mComplexityBN[4].getText().toString().trim().equals("全选"))
                {
                    mark=1;
                    break;
                }
                sb.append(mView.mComplexityBN[i].getText().toString().trim());
            }
        }
        if(mark==1)
        {
            complexity="全选";
        }else {

            complexity=sb.toString();
        }
        //是否适用工程为空
        if(adaptEngineering.equals(""))
        {

            if(complexity.equals("全选"))
            {
                SQL="select * from Question";
                RePaintTable(SQL);
            }else if(complexity.equals(""))
            {
                //用户忘记选择难易程度弹出对话框提示
                JOptionPane.showMessageDialog(null,"请选择难易程度！","消息对话框",JOptionPane.INFORMATION_MESSAGE );
            }
            else {
                //设定查询语句
                SQL="select * from Question where complexity ="+"'"+complexity+"'";
                //System.out.println(SQL);
                //重绘表格视图
                RePaintTable(SQL);
            }
        }else {
            if(complexity.equals("全选"))
            {

                SQL="select * from Question where adaptEngineering ="+"'"+adaptEngineering+"'";
                RePaintTable(SQL);
            }else if(complexity.equals(""))
            {
                JOptionPane.showMessageDialog(null,"请选择难易程度！","消息对话框",JOptionPane.INFORMATION_MESSAGE );
            }
            else {
                SQL="select * from Question where complexity ="+"'"+complexity+"'"+" and adaptEngineering ="+"'"+adaptEngineering+"'";
                RePaintTable(SQL);
            }
        }
    }
    //删除操作
    private void deleteOpration(String SQL) {
        queryDelete.setDatabaseName("Examination");
        queryDelete.setSQL(SQL);
        queryDelete.startQuery();
    }
    //复杂删除选项
    private void deleteItem()
    {
        id=mView.mQuestionID.getText().toString().trim();
        if(!id.equals(""))
        {

            isGoing=JOptionPane.showConfirmDialog(null,"警告：这将删除试题号为："+id+"的全部内容！！！请选择是否继续：","消息对话框", JOptionPane.YES_NO_OPTION);
            if(isGoing==0)
            {
                System.out.println("delete");
                SQL="delete from Question where id ="+id;
                deleteOpration(SQL);
                SQL="select * from Question";
                RePaintTable(SQL);
            }

        }else{
            adaptEngineering=mView.mApplicable.getText().toString().trim();
            StringBuffer sb=new StringBuffer();
            int mark=0;
            for(int i=0;i<4;i++)
            {
                if(mView.mComplexityBN[i].isSelected())
                {
                    if(mView.mComplexityBN[4].getText().toString().trim().equals("全选"))
                    {
                        mark=1;
                        break;
                    }
                    sb.append(mView.mComplexityBN[i].getText().toString().trim());
                }
            }
            if(mark==1)
            {
                complexity="全选";
            }else {
                complexity=sb.toString();
            }
            if(adaptEngineering.equals(""))
            {
                if(complexity.equals("全选"))
                {
                    isGoing=JOptionPane.showConfirmDialog(null,"警告：这将删除全部试题请选择是否继续：","消息对话框", JOptionPane.YES_NO_OPTION);
                    if(isGoing==0)
                    {
                        SQL="delete from Question ";
                        deleteOpration(SQL);
                        SQL="select * from Question";
                        RePaintTable(SQL);
                    }

                }else {

                    isGoing=JOptionPane.showConfirmDialog(null,"警告：这将删除难度为："+complexity+"的试题请选择是否继续：","消息对话框", JOptionPane.YES_NO_OPTION);
                    if(isGoing==0)
                    {
                        SQL="delete from Question where complexity ="+"'"+complexity+"'";
                        deleteOpration(SQL);
                        SQL="select * from Question";
                        RePaintTable(SQL);
                    }
                }
            }else {
                if(complexity.equals("全选"))
                {

                    isGoing=JOptionPane.showConfirmDialog(null,"警告：这将删除适用工程为："+adaptEngineering+"的试题请选择是否继续：","消息对话框", JOptionPane.YES_NO_OPTION);
                    if(isGoing==0)
                    {
                        SQL="delete from Question where adaptEngineering ="+"'"+adaptEngineering+"'";
                        deleteOpration(SQL);
                        SQL="select * from Question";
                        RePaintTable(SQL);
                    }
                }else if(complexity.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"请选择难易程度！","消息对话框",JOptionPane.INFORMATION_MESSAGE );

                }
                else {
                    int isGoing;
                    isGoing=JOptionPane.showConfirmDialog(null,"警告：这将删除难度为："+complexity+"和"+"适用工程为："+adaptEngineering+"的试题请选择是否继续：","消息对话框", JOptionPane.YES_NO_OPTION);
                    if(isGoing==0)
                    {
                        SQL="delete from Question where complexity ="+"'"+complexity+"'"+" and adaptEngineering ="+"'"+adaptEngineering+"'";
                        deleteOpration(SQL);
                        SQL="select * from Question";
                        RePaintTable(SQL);
                    }
                }
            }

        }


    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(((JButton)e.getSource()).getText().toString().equals("精确查询"))
        {
            accurateSearch();
        }else if(((JButton)e.getSource()).getText().toString().equals("模糊查询"))
        {
            dimSearch();
        }else if(((JButton)e.getSource()).getText().toString().equals("删除记录"))
        {
            deleteItem();

        }


    }

}