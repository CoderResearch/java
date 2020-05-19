package org.jit.control;

import org.jit.control.ControlView;
import org.jit.control.QuestionModel;
import org.jit.control.Questions;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
//倒计时显示类,这个类要处理耗时任务所以要在新线程中执行
public class TimeView extends JPanel{
    private JFrame frame;
    private JLabel hourLabel;
    private JLabel minuteLabel;
    private JLabel secondsLabel;
    private JTextField minuteField;
    long time;
    long hour;
    long minute;
    long seconds;
    ControlView mView;
    QuestionModel mQuestionModel;
    Questions[] mAllQuestions;
    int count=0;
    boolean isOK=false;

    public void setControlView(ControlView view)
    {
        this.mView=view;
    }
    public void setQuestionModel(QuestionModel questionModel)
    {
        this.mQuestionModel=questionModel;
    }
    //获取倒计时时间，在新线程中执行否则会阻塞主线程
    public void getTime(final long minuteInput) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                time= minuteInput*60; // 自定义倒计时时间
                hour= 0;
                minute= 0;
                seconds = 0;
                while (time >= 0) {
                    //判断是否用户点击了交卷
                    if(isOK==false)
                    {
                        hour = time / 3600;
                        minute = (time - hour * 3600) / 60;
                        seconds = time - hour * 3600 - minute * 60;
                        hourLabel.setText(hour + "小时");
                        minuteLabel.setText(minute + "分钟");
                        secondsLabel.setText(seconds + "秒");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        time--;
                    }else
                    {
                        break;
                    }
                }
                if(time==0)
                {
                    mView.submitBN.setEnabled(false);
                    mAllQuestions=mQuestionModel.getAllQuestions();
                    for(int i=0;i<mAllQuestions.length;i++)
                    {
                        if(mAllQuestions[i].getUserAnswer().equals(mAllQuestions[i].getCurrectAnswer()))
                        {
                            count++;
                        }
                    }
                    JOptionPane.showMessageDialog(null,"您答对了："+count+"道题！","成绩",JOptionPane.OK_OPTION);
                }
            }
        }).start();
    }
    private void init() {
        hourLabel = new JLabel("");
        hourLabel.setForeground(Color.RED);
        hourLabel.setFont(new Font("", 1, 30));
        minuteLabel = new JLabel("");
        minuteLabel.setForeground(Color.RED);
        minuteLabel.setFont(new Font("", 1, 30));
        secondsLabel = new JLabel("");
        secondsLabel.setForeground(Color.RED);
        secondsLabel.setFont(new Font("", 1, 30));
        add(hourLabel);
        add(minuteLabel);
        add(secondsLabel);
    }
    public TimeView(){

        init();
        setVisible(true);
    }

}
