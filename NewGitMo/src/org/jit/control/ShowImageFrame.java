package org.jit.control;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowImageFrame extends JFrame{
    String imageName;
    String imagePath="D:/GitProject/NewGitMo试题图片/";
    Image mImage;
    Toolkit mToolkit;
    JLabel showImageJLabel;
    //这里的显示原理与ExamImageView类中图片显示原理相同只是窗口中仅添加了一个标签组件
    ShowImageFrame(String name)
    {

        setVisible(true);
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        imageName=name;
        showImageJLabel=new JLabel();
        add(showImageJLabel);
        showImageJLabel.setSize(getBounds().width,getBounds().height );
        ImageIcon icon=new ImageIcon(imageName);
        Image temp=icon.getImage().getScaledInstance(showImageJLabel.getWidth(), showImageJLabel.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon=new ImageIcon(temp);
        showImageJLabel.setIcon(icon);

    }
}

