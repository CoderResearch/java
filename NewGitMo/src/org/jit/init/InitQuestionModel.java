package org.jit.init;

import org.jit.delete.PreQuery;
import org.jit.control.QuestionModel;
import org.jit.control.Questions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
//初始化试题模型，这里需要实现随机抽题，方法是用链表来模拟随机抽题
public class InitQuestionModel {

    QuestionModel mQuestionModel;
    Questions[] questions=null;
    String[][] record;//数据库读取的记录

    Connection con;
    PreparedStatement ppStatement;
    PreQuery query;

    int mNumber=0;//试题数量
    LinkedList<Integer> list;//随机抽题所用链表

    String SQL="select * from Question where adaptEngineering = ";
    public QuestionModel getQuestionModel(String adaptEngineering,int QuestionNumbers)
    {
        //获取对应工程下所有试题
        SQL=SQL+"'"+adaptEngineering+"'";
        query.setDatabaseName("Examination");
        query.setSQL(SQL);
        query.startQuery();
        record=query.getRecord();
        //获取全部试题数量
        int recordLength=record.length;
        //判断试题数量是否足够考试要求数量
        if(QuestionNumbers>recordLength)
        {
            JOptionPane.showMessageDialog(null,"题库试题数量少于要求数量请重新设定考试题数量！","消息对话框", JOptionPane.WARNING_MESSAGE);
            return null;
        }else {
            //使节点数量和总题量相同
            for(int i=0;i<recordLength;i++)
            {
                list.add(i);
            }
            //随机数
            Random random=new Random();
            //问题数量与考试要求数量相同
            questions=new Questions[QuestionNumbers];
            for(int i=0;i<QuestionNumbers;i++)
            {
                //模拟随机抽题产生随机数，要注意随机数的上限要跟随链表长度变化
                int mRandom=random.nextInt(list.size());
                //抽到则从表中剔除
                int index=list.remove(mRandom);
                //为试题添加相应内容
                questions[i]=new Questions();
                questions[i].setNumber(i);
                if(record[index][3].equals("1"))
                {
                    questions[i].setType("单项选择");
                }else if(record[index][3].equals("2"))
                {
                    questions[i].setType("多项选择");
                }
                else if(record[index][3].equals("3"))
                {
                    questions[i].setType("判断题");
                }
                questions[i].setContext(record[index][4]);
                questions[i].setImageName(record[index][5]);
                questions[i].setChoiceAContext(record[index][6]);
                questions[i].setChoiceBContext(record[index][7]);
                questions[i].setChoiceCContext(record[index][8]);
                questions[i].setChoiceDContext(record[index][9]);
                questions[i].setCurrectAnswer(record[index][10]);
                //判断试题类型
                if(record[index][3].equals("1"))
                {
                    questions[i].setIsChoice(true);
                    questions[i].setIsMultipleChoice(false);
                    questions[i].setIsJudgement(false);
                }
                else if(record[index][3].equals("2"))
                {
                    questions[i].setIsChoice(false);
                    questions[i].setIsMultipleChoice(true);
                    questions[i].setIsJudgement(false);
                }else if(record[index][3].equals("3"))
                {
                    questions[i].setIsChoice(false);
                    questions[i].setIsMultipleChoice(false);
                    questions[i].setIsJudgement(true);

                }
            }
            mQuestionModel=new QuestionModel();
            mQuestionModel.setQuestions(questions);
            return this.mQuestionModel;
        }

    }
    InitQuestionModel()
    {
        query=new PreQuery();
        list=new LinkedList<Integer>();
    }

}
