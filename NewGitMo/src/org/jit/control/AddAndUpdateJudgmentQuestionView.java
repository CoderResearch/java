package org.jit.control;

import javax.swing.*;
public class AddAndUpdateJudgmentQuestionView extends JPanel{
    JTextField mQuestionID;
    JTextField mApplicable;
    JTextArea  mContext;
    JTextField mPictureName;
    JComboBox<String> mComplexityBox;
    JComboBox<String> mOperate;
    JButton submitButton;
    ButtonGroup buttonGroup;
    JRadioButton radioButtonTrue;
    JRadioButton radioButtonFalse;
    AddAndUpdateJudgmentQuestionHandle mHandle;

    AddAndUpdateJudgmentQuestionView()
    {
        setLayout(null);
        setVisible(true);
        submitButton=new JButton("提交");
        mQuestionID=new JTextField(10);
        mApplicable=new JTextField(10);
        mContext=new JTextArea(8,40);
        JScrollPane js=new JScrollPane(mContext);
        mPictureName=new JTextField(10);

        JLabel mIdLabel =new JLabel("试题号：(必填且不允许重复)");
        add(mIdLabel);
        add(mQuestionID);
        mIdLabel.setBounds(5,5,165,35);
        mQuestionID.setBounds(170,5,585,35);

        JLabel mContextLabel=new JLabel("试题内容：");
        add(mContextLabel);
        add(js);
        mContextLabel.setBounds(5,45,150,35);
        js.setBounds(155,45,600,160);

        JLabel mComboBoxJLabel=new JLabel("请选择操作类型：");
        mOperate=new JComboBox<String>();
        mOperate.addItem("请选择");
        mOperate.addItem("添加试题");
        mOperate.addItem("更新试题");
        add(mComboBoxJLabel);
        add(mOperate);
        mComboBoxJLabel.setBounds(5, 85, 150, 35);
        mOperate.setBounds(1, 115, 150, 35);


        JLabel mPicNameLabel=new JLabel("图片名称：");
        add(mPicNameLabel);
        add(mPictureName);
        mPicNameLabel.setBounds(5,210,150,35);
        mPictureName.setBounds(155,210,600,35);

        JLabel mApplicableLabel=new JLabel("适用工程：");
        add(mApplicableLabel);
        add(mApplicable);
        mApplicableLabel.setBounds(5,250,150,35);
        mApplicable.setBounds(155,250,600,35);

        JLabel mComplexityLabel=new JLabel("请选择难易程度：");
        mComplexityBox=new JComboBox<String>();
        mComplexityBox.addItem("A");
        mComplexityBox.addItem("B");
        mComplexityBox.addItem("C");
        mComplexityBox.addItem("D");
        add(mComplexityLabel);
        add(mComplexityBox);
        mComplexityLabel.setBounds(5, 290, 150, 35);
        mComplexityBox.setBounds(160, 290, 150, 35);

        JLabel answerLabel=new JLabel("请选择判断题题答案：");
        buttonGroup=new ButtonGroup();
        radioButtonTrue=new JRadioButton("正确");
        radioButtonFalse=new JRadioButton("错误");
        buttonGroup.add(radioButtonTrue);
        buttonGroup.add(radioButtonFalse);
        radioButtonTrue.setSelected(true);
        answerLabel.setBounds(5, 330, 150, 35);
        radioButtonTrue.setBounds(160, 330, 60, 35);
        radioButtonFalse.setBounds(230, 330, 60, 35);
        add(answerLabel);
        add(radioButtonTrue);
        add(radioButtonFalse);

        JLabel mButtonLabel=new JLabel("提交：");
        add(mButtonLabel);
        add(submitButton);
        mButtonLabel.setBounds(5,370,150,35);
        submitButton.setBounds(155,370,150,35);
        mHandle=new AddAndUpdateJudgmentQuestionHandle();
        mHandle.setView(this);
        submitButton.addActionListener(mHandle);



    }

}
