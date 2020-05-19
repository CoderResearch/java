package org.jit.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
public class QuestionView extends JPanel{
    Questions mQuestions;
    QuestionModel mModel;

    JTextArea mContext;

    ExamImageView mImageView;

    JLabel mJLabelContext;
    JLabel mJLabelA;
    JLabel mJLabelB;
    JLabel mJLabelC;
    JLabel mJLabelD;
    JLabel mJLabelType;
    JLabel mQusetionType;

    JTextField mTextA;
    JTextField mTextB;
    JTextField mTextC;
    JTextField mTextD;


    JCheckBox mCheckBoxA;
    JCheckBox mCheckBoxB;
    JCheckBox mCheckBoxC;
    JCheckBox mCheckBoxD;
    JButton mConfirmBN;

    JPanel controlPanel;
    QuestionNumberHandle NumberHandle;
    QuestionHandle mQuestionHandle;
    int mIndex=0;

    public QuestionView(Questions questions)
    {
        setLayout(null);
        setVisible(true);
        setSize(930,900);
        this.mQuestions=questions;
        mContext=new JTextArea(mQuestions.getContext());
        mContext.setFont(new Font("", 1, 20));
        mContext.setLineWrap(true);//自动换行
        mContext.setEditable(false);
        JScrollPane js=new JScrollPane(mContext);
        add(js);
        js.setBounds(110, 5, 800, 100);

        mJLabelContext=new JLabel("试题内容：");
        mJLabelContext.setFont(new Font("", 1, 20));
        add(mJLabelContext);
        mJLabelContext.setBounds(5, 5, 100, 35);


        mImageView=new ExamImageView();
        //System.out.println("qv:"+mQuestions.getImageName());
        mImageView.setImageNmae(mQuestions.getImageName());
        mImageView.showImage();
        add(mImageView);
        mImageView.setBounds(260, 110, 300, 200);

        mJLabelA=new JLabel("A:");
        mJLabelA.setFont(new Font("", 1, 15));
        mJLabelB=new JLabel("B:");
        mJLabelB.setFont(new Font("", 1, 15));
        mJLabelC=new JLabel("C:");
        mJLabelC.setFont(new Font("", 1, 15));
        mJLabelD=new JLabel("D:");
        mJLabelD.setFont(new Font("", 1, 15));
        mJLabelType=new JLabel("试题类型：");
        mJLabelType.setFont(new Font("",1, 20));
        add(mJLabelA);
        add(mJLabelB);
        add(mJLabelC);
        add(mJLabelD);
        add(mJLabelType);
        mJLabelA.setBounds(5, 320, 20, 35);
        mJLabelB.setBounds(5, 360, 20, 35);
        mJLabelC.setBounds(5, 400, 20, 35);
        mJLabelD.setBounds(5, 440, 20, 35);
        mJLabelType.setBounds(5, 480, 100, 35);
        mQusetionType=new JLabel(mQuestions.getType());
        mQusetionType.setForeground(Color.RED);
        mQusetionType.setFont(new Font("", 1, 20));
        add(mQusetionType);
        mQusetionType.setBounds(110,480, 150, 35);

        mTextA=new JTextField(mQuestions.getChoiceAContext());
        mTextA.setFont(new Font("", 1, 15));
        mTextA.setEditable(false);
        mTextB=new JTextField(mQuestions.getChoiceBContext());
        mTextB.setFont(new Font("", 1, 15));
        mTextB.setEditable(false);
        mTextC=new JTextField(mQuestions.getChoiceCContext());
        mTextC.setFont(new Font("", 1, 15));
        mTextC.setEditable(false);
        mTextD=new JTextField(mQuestions.getChoiceDContext());
        mTextD.setFont(new Font("", 1, 15));
        mTextD.setEditable(false);

        add(mTextA);
        add(mTextB);
        add(mTextC);
        add(mTextD);


        mTextA.setBounds(30, 320, 880, 35);
        mTextB.setBounds(30, 360, 880, 35);
        mTextC.setBounds(30, 400, 880, 35);
        mTextD.setBounds(30, 440, 880, 35);

        mQuestionHandle=new QuestionHandle();
        mQuestionHandle.setQuestionView(this);
        mQuestionHandle.setQuestionModel(mModel);
        mQuestionHandle.setmQuestions(mQuestions);
        //mQuestionHandle.setQuestionIndex(mIndex);


        mCheckBoxA=new JCheckBox("A");
        mCheckBoxB=new JCheckBox("B");
        mCheckBoxC=new JCheckBox("C");
        mCheckBoxD=new JCheckBox("D");
        mCheckBoxA.setFont(new Font("", 1, 15));
        mCheckBoxB.setFont(new Font("", 1, 15));
        mCheckBoxC.setFont(new Font("", 1, 15));
        mCheckBoxD.setFont(new Font("", 1, 15));

        mConfirmBN=new JButton("保存");
        mConfirmBN.setFont(new Font("", 1, 20));
        mConfirmBN.addActionListener(mQuestionHandle);
        add(mCheckBoxA);
        add(mCheckBoxB);
        add(mCheckBoxC);
        add(mCheckBoxD);
        add(mConfirmBN);
        mCheckBoxA.setBounds(10, 520, 100, 35);
        mCheckBoxB.setBounds(120, 520, 100, 35);
        mCheckBoxC.setBounds(220, 520, 100, 35);
        mCheckBoxD.setBounds(320, 520, 100, 35);
        mConfirmBN.setBounds(620, 520, 100, 35);

        if(mQuestions.getIsChoice())
        {
            InitChooiceView();
        }
        if(mQuestions.getIsMultipleChoice())
        {
            InitMultiselectView();
        }
        if(mQuestions.getIsJudgement())
        {
            InitJudgmentView();
        }
        validate();
    }
    public void InitChooiceView()
    {
        mJLabelC.setVisible(true);
        mJLabelD.setVisible(true);

        mTextC.setVisible(true);
        mTextD.setVisible(true);

        mCheckBoxC.setVisible(true);
        mCheckBoxD.setVisible(true);
    }
    public void InitMultiselectView()
    {
        mJLabelC.setVisible(true);
        mJLabelD.setVisible(true);

        mTextC.setVisible(true);
        mTextD.setVisible(true);

        mCheckBoxC.setVisible(true);
        mCheckBoxD.setVisible(true);
    }
    public void InitJudgmentView()
    {
        mJLabelC.setVisible(false);
        mJLabelD.setVisible(false);

        mTextC.setVisible(false);
        mTextD.setVisible(false);

        mCheckBoxC.setVisible(false);
        mCheckBoxD.setVisible(false);
    }
    public void setQuestionModel(QuestionModel questionModel)
    {
        this.mModel=questionModel;
    }
    public QuestionHandle getQuestionHandle()
    {
        return this.mQuestionHandle;
    }
    public void setQuestionIndex(int index)
    {
        mIndex=index;
    }

}