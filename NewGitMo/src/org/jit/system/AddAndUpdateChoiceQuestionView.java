package org.jit.system;

import javax.swing.*;

//选择题管理视图类AddAndUpdateChoiceQuestionView
public class AddAndUpdateChoiceQuestionView extends JPanel {
    JTextField mQuestionID;     //输入试题编号
    JTextField mApplicable;     //适用工程
    JTextArea  mContext;        //输入试题内容
    JTextField mPictureName;    //输入图片名字
    JTextField inputA;          //输入选择a
    JTextField inputB;          //输入选择b
    JTextField inputC;          //输入选择c
    JTextField inputD;          //输入选择d
    JRadioButton[] mMultiselectBN;//多选题按钮
    JButton submitButton;          //提交按钮
    JComboBox<String> mOperate;//操作类型下拉选项
    JComboBox<String> mType;//试题类型下拉选项
    JComboBox<String> mAnswerBox;//答案选择框
    JComboBox<String> mComplexityBox;//难度选择框
    AddAndUpdateHandle handle;//该视图的数据处理类
    AddAndUpdateChoiceQuestionView()
    {
        setLayout(null);
        setVisible(true);
        submitButton=new JButton("提交");
        mQuestionID=new JTextField(10);
        mApplicable=new JTextField(10);
        mContext=new JTextArea(8,40);
        JScrollPane js=new JScrollPane(mContext);
        mPictureName=new JTextField(10);


        inputA=new JTextField(50);
        inputB=new JTextField(50);
        inputC=new JTextField(50);
        inputD=new JTextField(50);

        JLabel mComboBoxJLabel=new JLabel("请选择操作类型：");
        mOperate=new JComboBox<String>();
        mOperate.addItem("请选择");
        mOperate.addItem("添加试题");
        mOperate.addItem("更新试题");
        add(mComboBoxJLabel);
        add(mOperate);
        mComboBoxJLabel.setBounds(5, 85, 150, 35);
        mOperate.setBounds(1, 115, 150, 35);


        JLabel mTypeLabel=new JLabel("请选试题类型：");
        mType=new JComboBox<String>();
        mType.addItem("请选择");
        mType.addItem("单项选择");
        mType.addItem("多项选择");
        add(mTypeLabel);
        add(mType);
        mTypeLabel.setBounds(5, 140, 150, 35);
        mType.setBounds(1, 170, 150, 35);



        JLabel mIdLabel =new JLabel("试题号：(必填且不允许重复)");
        add(mIdLabel);
        add(mQuestionID);
        mIdLabel.setBounds(5,5,165,35);
        mQuestionID.setBounds(170,5,585,35);

        JLabel mApplicableLabel=new JLabel("适用工程：");
        add(mApplicableLabel);
        add(mApplicable);
        mApplicableLabel.setBounds(5,415,150,35);
        mApplicable.setBounds(155,415,600,35);

        JLabel mComplexityLabel=new JLabel("请选择难易程度：");
        mComplexityBox=new JComboBox<String>();
        mComplexityBox.addItem("A");
        mComplexityBox.addItem("B");
        mComplexityBox.addItem("C");
        mComplexityBox.addItem("D");
        add(mComplexityLabel);
        add(mComplexityBox);
        mComplexityLabel.setBounds(320, 455, 150, 35);
        mComplexityBox.setBounds(480, 455, 150, 35);

        JLabel mContextLabel=new JLabel("试题内容：");
        add(mContextLabel);
        add(js);
        mContextLabel.setBounds(5,45,150,35);
        js.setBounds(155,45,600,160);

        JLabel mPicNameLabel=new JLabel("图片名称：");
        add(mPicNameLabel);
        add(mPictureName);
        mPicNameLabel.setBounds(5,210,150,35);
        mPictureName.setBounds(155,210,600,35);


        JLabel tishiA =new JLabel("选项A");
        add(tishiA);
        add(inputA);
        JLabel tishiB =new JLabel("选项B:");
        add(tishiB);
        add(inputB);
        JLabel tishiC =new JLabel("选项C:");
        add(tishiC);
        add(inputC);
        JLabel tishiD =new JLabel("选项D:");
        add(tishiD);
        add(inputD);

        JLabel mAnswerLabel =new JLabel("请选择单选题正确答案:");
        mAnswerBox=new JComboBox<String>();
        mAnswerBox.addItem("A");
        mAnswerBox.addItem("B");
        mAnswerBox.addItem("C");
        mAnswerBox.addItem("D");
        add(mAnswerLabel);
        add(mAnswerBox);
        tishiA.setBounds(5,255,150,35);
        inputA.setBounds(155,255,600,35);
        tishiB.setBounds(5,295,150,35);
        inputB.setBounds(155,295,600,35);
        tishiC.setBounds(5,335,150,35);
        inputC.setBounds(155,335,600,35);
        tishiD.setBounds(5,375,150,35);
        inputD.setBounds(155,375,600,35);
        mAnswerLabel.setBounds(5, 455, 150, 35);
        mAnswerBox.setBounds(155, 455, 150, 35);

        JLabel multiselectlJLabel=new JLabel("请选择多选题答案：");
        mMultiselectBN=new JRadioButton[4];
        mMultiselectBN[0]=new JRadioButton("A");
        mMultiselectBN[1]=new JRadioButton("B");
        mMultiselectBN[2]=new JRadioButton("C");
        mMultiselectBN[3]=new JRadioButton("D");
        multiselectlJLabel.setBounds(5,500,150,35);
        mMultiselectBN[0].setBounds(160, 500, 50, 35);
        mMultiselectBN[1].setBounds(220, 500, 50, 35);
        mMultiselectBN[2].setBounds(280, 500, 50, 35);
        mMultiselectBN[3].setBounds(340, 500, 50, 35);
        add(multiselectlJLabel);
        for(int i=0;i<4;i++)
        {
            add(mMultiselectBN[i]);
        }

        JLabel mButtonLabel=new JLabel("提交：");
        add(mButtonLabel);
        add(submitButton);
        mButtonLabel.setBounds(5,540,150,35);
        submitButton.setBounds(155,540,150,35);

        handle=new AddAndUpdateHandle();
        handle.setView(this);
        submitButton.addActionListener(handle);
    }

}