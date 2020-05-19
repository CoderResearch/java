package org.jit.control;

import org.jit.add.TimeView;

import java.awt.Font;

import javax.swing.*;
public class ControlView extends JPanel{
    JButton submitBN;//交卷按钮
    TimeView mTimeView;//倒计时视图
    ControlHandle mHandle;//ControlView的数据处理类
    QuestionView mView;//试题窗口
    QuestionModel mQuestionModel;//试题模型
    long ExamTime;//考试时间

    public void showView()
    {
        setVisible(true);
        setLayout(null);
        mHandle=new ControlHandle();
        mHandle.setControlView(this);

        mTimeView=new TimeView();
        mTimeView.setControlView(this);
        mTimeView.getTime(ExamTime);
        mTimeView.setQuestionModel(mQuestionModel);
        add(mTimeView);
        mTimeView.setBounds(0, 0, 360, 40);
        mHandle.setTimeView(mTimeView);

        submitBN=new JButton("交卷");
        submitBN.setFont(new Font("", 1, 30));
        add(submitBN);
        submitBN.setBounds(20, 90, 300, 100);
        submitBN.addActionListener(mHandle);
        submitBN.setActionCommand("submit");
    }
    //初始化窗口并传入考试时间
    public ControlView(long ExamTime)
    {
        this.ExamTime=ExamTime;
    }

}
