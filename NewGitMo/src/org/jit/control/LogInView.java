package org.jit.control;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class LogInView extends JPanel implements ActionListener{
    JPasswordField mPasswordField;
    JLabel mLabel;
    JButton mSubmitBN;
    private String passWord="123456";
    public void InitView()
    {
        setVisible(true);
        setLayout(null);
        mPasswordField=new JPasswordField();
        mLabel=new JLabel("请输入管理员密码：");
        mSubmitBN=new JButton("登录");
        add(mLabel);
        add(mPasswordField);
        add(mSubmitBN);
        mLabel.setBounds(50, 100, 130, 35);
        mPasswordField.setBounds(190, 100, 200, 35);
        mSubmitBN.setBounds(410,100, 60, 35);
        mSubmitBN.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(new String(mPasswordField.getPassword()).equals(passWord))
        {
            mPasswordField.setText("");
            QuestionsManagementView questionsManagementView=new QuestionsManagementView();
        }else
        {
            mPasswordField.setText("");
            JOptionPane.showMessageDialog(null,"输入密码错误！请重新输入！","",JOptionPane.ERROR_MESSAGE);
        }

    }


}

