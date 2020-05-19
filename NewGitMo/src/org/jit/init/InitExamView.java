package org.jit.init;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
public class InitExamView extends JPanel{

    JComboBox<String> mApplicable;//适用工程
    JButton startExamBN;//开始考试按钮

    String[] mApplicableType;
    int[] mQuestionAmount;

    Scanner scanner;
    //适用工程文本路径
    String filePathApplicableType="./试题管理目录/适用工程.txt";
    //试题数量文本路径
    String filePathQuestionAmount="./试题管理目录/试题数量.txt";
    String examType=null;
    InitExamHandle mHandle;
    public InitExamView()
    {
        setLayout(null);
        setVisible(true);

        mHandle=new InitExamHandle();
        mHandle.setInitExamView(this);

        mApplicableType=new String[8];

        getApplicableType();
        JLabel mApplicableLabel=new JLabel("请选择试题类型：");
        mApplicable=new JComboBox<String>();
        for(int i=0;i<mApplicableType.length;i++)
        {
            mApplicable.addItem(mApplicableType[i]);
        }
        add(mApplicableLabel);
        add(mApplicable);
        mApplicableLabel.setBounds(50, 100, 130, 35);
        mApplicable.setBounds(190, 100, 150, 35);

        startExamBN=new JButton("开始考试");
        add(startExamBN);
        startExamBN.setBounds(370, 100, 150, 35);
        startExamBN.addActionListener(mHandle);

    }
    //得到试用工程名称
    public void getApplicableType()
    {
        try {
            scanner=new Scanner(new File(filePathApplicableType));
            scanner.useDelimiter("[\n]+");
            for(int i=0;i<mApplicableType.length;i++)
            {
                mApplicableType[i]=scanner.next();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

}

