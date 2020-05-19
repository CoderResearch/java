package org.jit.system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
//QuestionNumberHandle类对应上图右上角试题选择区域的数据处理
public class QuestionNumberHandle implements ActionListener{

    QuestionNumberView mView;
    QuestionModel mModel;
    Questions mQuestions;
    QuestionView mQuestionView;
    ExamImageView examImageView;//图片显示视图
    ImageIcon icon;
    Image temp;
    String imagePath="./试题图片/";
    int index;
    //绑定窗口
    public void setQuestionNumberView(QuestionNumberView view) {
        this.mView=view;
    }
    public void setQuestionView(QuestionView view)
    {
        this.mQuestionView=view;
    }
    public void setQuestionModel(QuestionModel questionModel)
    {
        this.mModel=questionModel;
    }
    public void setQestion(Questions questions)
    {
        this.mQuestions=questions;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //获取是几号按钮触发
        index=Integer.parseInt(e.getActionCommand());
        //找到该索引对应试题
        mQuestions=mModel.getQuestions(index);
        if(mQuestions.getIsJudgement())
        {
            mQuestionView.mJLabelC.setVisible(false);
            mQuestionView.mJLabelD.setVisible(false);

            mQuestionView.mTextC.setVisible(false);
            mQuestionView.mTextD.setVisible(false);

            mQuestionView.mCheckBoxC.setVisible(false);
            mQuestionView.mCheckBoxD.setVisible(false);

        }else if(mQuestions.getIsMultipleChoice()){
            mQuestionView.mJLabelC.setVisible(true);
            mQuestionView.mJLabelD.setVisible(true);

            mQuestionView.mTextC.setVisible(true);
            mQuestionView.mTextD.setVisible(true);

            mQuestionView.mCheckBoxC.setVisible(true);
            mQuestionView.mCheckBoxD.setVisible(true);

        }else if(mQuestions.getIsChoice())
        {
            mQuestionView.mJLabelC.setVisible(true);
            mQuestionView.mJLabelD.setVisible(true);

            mQuestionView.mTextC.setVisible(true);
            mQuestionView.mTextD.setVisible(true);

            mQuestionView.mCheckBoxC.setVisible(true);
            mQuestionView.mCheckBoxD.setVisible(true);

        }
        //保存用户选择答案信息
        if(mQuestions.getUserAnswer().equals("")||mQuestions.getUserAnswer()==null)
        {
            mQuestionView.mCheckBoxA.setSelected(false);
            mQuestionView.mCheckBoxB.setSelected(false);
            mQuestionView.mCheckBoxC.setSelected(false);
            mQuestionView.mCheckBoxD.setSelected(false);
        }else
        {
            mQuestionView.mCheckBoxA.setSelected(false);
            mQuestionView.mCheckBoxB.setSelected(false);
            mQuestionView.mCheckBoxC.setSelected(false);
            mQuestionView.mCheckBoxD.setSelected(false);
            if(mQuestions.getUserAnswer().contains("A"))
            {
                mQuestionView.mCheckBoxA.setSelected(true);
            }
            if(mQuestions.getUserAnswer().contains("B"))
            {
                mQuestionView.mCheckBoxB.setSelected(true);
            }
            if(mQuestions.getUserAnswer().contains("C"))
            {
                mQuestionView.mCheckBoxC.setSelected(true);
            }
            if(mQuestions.getUserAnswer().contains("D"))
            {
                mQuestionView.mCheckBoxD.setSelected(true);
            }

        }
        //更新试题视图
        mQuestionView.mContext.setText(mQuestions.getContext());
        icon=new ImageIcon(imagePath+mQuestions.getImageName());
        temp=icon.getImage().getScaledInstance(300,200, icon.getImage().SCALE_DEFAULT);
        icon=new ImageIcon(temp);
        mQuestionView.mQuestionHandle.setmQuestions(this.mQuestions);
        mQuestionView.mImageView.setImageNmae(mQuestions.getImageName());
        mQuestionView.mImageView.showImageJLabel.setIcon(icon);
        mQuestionView.mTextA.setText(mQuestions.getChoiceAContext());
        mQuestionView.mTextB.setText(mQuestions.getChoiceBContext());
        mQuestionView.mTextC.setText(mQuestions.getChoiceCContext());
        mQuestionView.mTextD.setText(mQuestions.getChoiceDContext());
        mQuestionView.mQusetionType.setText(mQuestions.getType());
        mQuestionView.validate();
    }

}
