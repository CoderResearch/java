package org.jit.control;

import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

//ControlHandle对应上图右下角倒计时和交卷视图的数据处理
public class ControlHandle implements ActionListener{

    ControlView mControlView;
    QuestionView mQuestionView;
    QuestionModel mQuestionModel;
    Questions mQuestions;
    Questions[] mAllQuestions;
    TimeView mTimeView;
    int count=0;//计算试题正确数量
    ImageIcon icon;//试题中带有的图片以图标形式显示
    Image temp;
    String imagePath="./试题图片/";//图片路径父目录
    //绑定试题模型
    public void setQuestionModel(QuestionModel model)
    {
        this.mQuestionModel=model;
    }
    //绑定QuestionView视图
    public void setQuestionView(QuestionView view)
    {
        this.mQuestionView=view;
    }
    //绑定ControlView视图
    public void setControlView(ControlView view)
    {
        this.mControlView=view;
    }
    //绑定TimeView视图
    public void setTimeView(TimeView timeView)
    {
        mTimeView=timeView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //是否点击提交按钮
        if(e.getActionCommand().equals("submit"))
        {
            //获取所有试题
            mAllQuestions=mQuestionModel.getAllQuestions();
            //判题
            for(int i=0;i<mAllQuestions.length;i++)
            {
                if(mAllQuestions[i].getUserAnswer().equals(mAllQuestions[i].getCurrectAnswer()))
                {
                    count++;
                }
            }
            //使倒计时停止计时
            mTimeView.isOK=true;
            //使提交按钮不可以继续按下
            mControlView.submitBN.setEnabled(false);
            //弹出对话框告知用户答对数量
            JOptionPane.showMessageDialog(null,"您答对了："+count+"道题！","成绩",JOptionPane.OK_OPTION);
        }

    }

}

