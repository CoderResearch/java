package org.jit.control;

import java.awt.BorderLayout;

import javax.swing.*;

public class QuestionsManagementView extends JFrame{
    JTabbedPane mTabbedPane;//窗格面板
    AddAndUpdateChoiceQuestionView addChoiceQuestion;//选择题管理视图
    DeleteAndSerachChoiceQuestionView deleteAndSerachView;//删除、查询试题
    AddAndUpdateJudgmentQuestionView judgmentQuestionView; //判断题管理视图
    public QuestionsManagementView()
    {
        setBounds(200,100,900,800);
        setVisible(true);
        setTitle("试题管理系统");
        mTabbedPane=new JTabbedPane();

        addChoiceQuestion=new AddAndUpdateChoiceQuestionView();
        deleteAndSerachView=new DeleteAndSerachChoiceQuestionView();
        judgmentQuestionView=new AddAndUpdateJudgmentQuestionView();

        mTabbedPane.add("选择题管理",addChoiceQuestion);
        mTabbedPane.add("判断题题管理",judgmentQuestionView);
        mTabbedPane.add("查询/删除试题",deleteAndSerachView);

        add(mTabbedPane,BorderLayout.CENTER);
        validate();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }



}
