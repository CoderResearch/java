package org.jit.control;

import java.awt.BorderLayout;

import javax.swing.*;
public class ExamMainView extends JFrame{
    JTabbedPane mTabbedPane; //窗格视图
    InitExamView initExamView;//初始化考试视图
    LogInView logInView;//登录视图
    public ExamMainView()
    {
        setBounds(350,200,600,400);
        setVisible(true);
        setTitle("考试系统");


        mTabbedPane=new JTabbedPane();

        initExamView=new InitExamView();

        logInView=new LogInView();
        logInView.InitView();

        mTabbedPane.add("考试",initExamView);
        mTabbedPane.add("试题管理",logInView);

        add(mTabbedPane,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();

    }

}
