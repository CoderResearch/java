package org.jit.control;

import java.awt.Font;

import javax.swing.*;
public class ExamWorkView extends JFrame{

    Questions questions;//试题类
    QuestionModel mQuestionModel;//试题模型
    ControlView controlView;//控制视图
    QuestionView questionView;//试题视图
    QuestionNumberView questionNumberView;//试题网格选择视图
    JLabel questionNumberJLabel;//提示标签
    int index=0;//第一个题目索引值
    public ExamWorkView(String workName,QuestionModel questionModel,int QuestionsNumber,long ExanMinute)
    {
        //获取试题模型
        this.mQuestionModel=questionModel;
        //得到第一个试题
        questions=mQuestionModel.getQuestions(index);
        setBounds(0, 0, 1300, 800);
        setLayout(null);
        setTitle(workName);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        questionView=new QuestionView(questions);
        add(questionView);
        questionView.setBounds(10,30, 920, 900);
        questionView.setQuestionModel(this.mQuestionModel);

        questionNumberJLabel=new JLabel("试题选择区：");
        questionNumberJLabel.setFont(new Font("", 1, 25));
        add(questionNumberJLabel);
        questionNumberJLabel.setBounds(930, 0, 150, 35);

        questionNumberView=new QuestionNumberView(QuestionsNumber);
        questionNumberView.handle.setQuestionView(questionView);
        questionNumberView.handle.setQuestionModel(mQuestionModel);
        questionNumberView.setListener();
        questionView.mQuestionHandle.setQuestionNumberView(questionNumberView);
        add(questionNumberView);
        questionNumberView.setBounds(930, 40, 345, 410);

        controlView=new ControlView(ExanMinute);
        controlView.showView();
        controlView.mHandle.setQuestionView(questionView);
        controlView.mHandle.setQuestionModel(mQuestionModel);
        add(controlView);
        controlView.setBounds(930, 450, 360, 350);

        setVisible(true);
    }

}
