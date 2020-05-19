package org.jit.init;
import org.jit.control.ExamWorkView;
import org.jit.control.QuestionModel;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

//初始化考试
public class InitExamHandle implements ActionListener{

    InitExamView mView;
    String examType;
    String filePathQuestionAmount="./试题管理目录/试题数量.txt";
    String filePathExamTimes="./试题管理目录/考试时间(单位为分钟).txt";
    int[] mExamTimes;
    int[] mQuestionAmount;
    Scanner scanner;
    public void setInitExamView(InitExamView view)
    {
        this.mView=view;
    }
    //得到每个工程所需考试题数量
    public int getQuestionAmount(int index)
    {
        mQuestionAmount=new int[8];
        try {
            //考试题数量存在文本文件中，每次进入程序每次重新获取
            scanner=new Scanner(new File(filePathQuestionAmount));
            //试题数量以换行符隔开
            scanner.useDelimiter("[\n]+");
            for(int i=0;i<mQuestionAmount.length;i++)
            {
                mQuestionAmount[i]=Integer.parseInt(scanner.next());
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return mQuestionAmount[index];
    }
    //得到考试时间
    public int getExamTime(int index)
    {
        mExamTimes=new int[8];
        try {
            scanner=new Scanner(new File(filePathExamTimes));
            scanner.useDelimiter("[\n]+");
            for(int i=0;i<mExamTimes.length;i++)
            {
                mExamTimes[i]=Integer.parseInt(scanner.next());
            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        return mExamTimes[index];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int id=mView.mApplicable.getSelectedIndex();
        examType=mView.mApplicable.getSelectedItem().toString();
        int mQuestionNumber=getQuestionAmount(id);
        int mExamTime=getExamTime(id);
        InitQuestionModel mInitQuestionModel=new InitQuestionModel();
        QuestionModel mQuestionModel= mInitQuestionModel.getQuestionModel(examType,mQuestionNumber);

        if(mQuestionModel!=null)
        {
            //如果成功获取试题模型则显示考试界面
            ExamWorkView examWorkView =new ExamWorkView("考试窗口",mQuestionModel,mQuestionNumber,mExamTime);
        }else {
            JOptionPane.showMessageDialog(null,"考试窗口初始化失败！请检查试用工程是否在题库中存在或试题数量是否超过上限！", "提示", JOptionPane.WARNING_MESSAGE);
        }

    }

}

