package org.jit.system;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JOptionPane;
//QuestionHandle对应上图左边最大矩形的视图的数据处理
public class QuestionHandle implements ActionListener {

    QuestionView mView;
    QuestionModel mModel;
    Questions mQuestions;
    Questions[] mAllQuestions;
    QuestionNumberView mQuestionNumberView;
    int selectNumbers=0;//勾选数量
    String userAnswer="";//用户答案
    int questionNumbers=0;//试题数量
    int number;//保存试题对应序号

    //绑定试题选择视图
    public void setQuestionNumberView(QuestionNumberView questionNumberView)
    {
        this.mQuestionNumberView=questionNumberView;
    }
    //绑定试题视图
    public void setQuestionView(QuestionView view)
    {
        this.mView=view;
    }
    //得到试题在数组中的序号
    public void setQuestionNumbers(int number)
    {
        questionNumbers=number;
    }
    //得到试题模型
    public void setQuestionModel(QuestionModel questionModel)
    {
        this.mModel=questionModel;
    }
    //得到相应问题
    public void setmQuestions(Questions questions) {
        this.mQuestions = questions;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //不同题型勾选答案数量并不一样，所以要进行判断
        if(mQuestions.isChoice)
        {
            if(mView.mCheckBoxA.isSelected())
            {
                userAnswer="A";
                selectNumbers++;
            }
            if(mView.mCheckBoxB.isSelected())
            {
                userAnswer="B";
                selectNumbers++;
            }
            if(mView.mCheckBoxC.isSelected())
            {
                userAnswer="C";
                selectNumbers++;
            }
            if(mView.mCheckBoxD.isSelected())
            {
                userAnswer="D";
                selectNumbers++;
            }
            if(selectNumbers>1&&!userAnswer.equals(""))
            {
                JOptionPane.showMessageDialog
                        (null,"保存试题失败!!!该提为单选题！请查看是否选择了多个选项！或者未选择答案","消息对话框", JOptionPane.WARNING_MESSAGE);
            }else {
                number=mQuestions.getNumber();
                mQuestionNumberView.buttons[number].setForeground(Color.GREEN);
                mQuestions.setUserAnswer(userAnswer);
            }
            selectNumbers=0;

        }else if(mQuestions.isMultipleChoice)
        {
            StringBuilder sBuilder=new StringBuilder();
            if(mView.mCheckBoxA.isSelected())
            {
                sBuilder.append("A");
                selectNumbers++;
            }
            if(mView.mCheckBoxB.isSelected())
            {
                sBuilder.append("B");
                selectNumbers++;
            }
            if(mView.mCheckBoxC.isSelected())
            {
                sBuilder.append("C");
                selectNumbers++;
            }
            if(mView.mCheckBoxD.isSelected())
            {
                sBuilder.append("D");
                selectNumbers++;
            }
            if(selectNumbers==1)
            {
                JOptionPane.showMessageDialog
                        (null,"保存试题失败!!!该提为多选题！请查看是否只选择了一个选项！","消息对话框", JOptionPane.WARNING_MESSAGE);
            }else {
                number=mQuestions.getNumber();
                mQuestionNumberView.buttons[number].setForeground(Color.GREEN);
                mQuestions.setUserAnswer(sBuilder.toString());
            }
            selectNumbers=0;
        }else if(mQuestions.isJudgement)
        {
            if(mView.mCheckBoxA.isSelected())
            {
                userAnswer="A";
                selectNumbers++;
            }
            if(mView.mCheckBoxB.isSelected())
            {
                userAnswer="B";
                selectNumbers++;
            }
            if(selectNumbers>1)
            {
                JOptionPane.showMessageDialog
                        (null,"保存试题失败!!!该提为判断题！请查看是否选择了多个选项！","消息对话框", JOptionPane.WARNING_MESSAGE);
            }else {
                number=mQuestions.getNumber();
                mQuestionNumberView.buttons[number].setForeground(Color.GREEN);
                mQuestions.setUserAnswer(userAnswer);
            }
            selectNumbers=0;
        }

    }


}
