package org.jit.system;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class QuestionNumberView extends JPanel{

    //按试题数量显示按钮数量
    int questionNumber;
    JButton[] buttons;
    QuestionNumberHandle handle;
    QuestionView mView;
    QuestionModel mModel;
    QuestionNumberView(int number)
    {
        questionNumber=number;
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
        buttons=new JButton[questionNumber];
        for(int i=0;i<questionNumber;i++)
        {
            buttons[i]=new JButton(""+(i+1));
            buttons[i].setFont(new Font("", 1, 15));
            buttons[i].setActionCommand(""+i);
            add(buttons[i]);
        }
        handle=new QuestionNumberHandle();
        handle.setQuestionNumberView(this);
        setVisible(true);
    }

    public void setListener()
    {
        for(int i=0;i<questionNumber;i++)
        {
            buttons[i].addActionListener(handle);
        }
    }

}
