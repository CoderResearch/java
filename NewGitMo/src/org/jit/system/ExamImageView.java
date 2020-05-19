package org.jit.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ExamImageView extends JPanel implements MouseListener{

    String Path="./试题图片/";//试题图片父目录
    JLabel showImageJLabel;//显示图片所用标签
    ImageIcon icon;//将图片转换为图标
    Image temp;
    String imagePath;//图片完整路径
    String name;//图片名称
    //设置图片名称
    public void setImageNmae(String name)
    {
        //将图片路径补充完整
        imagePath=Path+name;
    }
    //显示图片
    public void showImage()
    {
        //这里将图片转换为图标的目的是为了使图片大小更容易控制，而且重绘方便
        icon=new ImageIcon(imagePath);
        temp=icon.getImage().getScaledInstance(showImageJLabel.getWidth(), showImageJLabel.getHeight(), icon.getImage().SCALE_DEFAULT);
        icon=new ImageIcon(temp);
        //将图片绘制到标签上
        showImageJLabel.setIcon(icon);
        add(showImageJLabel);
        //监听鼠标点击事件，在点击后放大图片
        addMouseListener(this);
    }
    ExamImageView()
    {
        showImageJLabel=new JLabel();
        showImageJLabel.setBounds(0, 0, 300,200 );
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //showImageFrame窗口负责放大图片
        ShowImageFrame showImageFrame=new ShowImageFrame(imagePath);
    }
    @Override
    public void mouseClicked(MouseEvent e) {	}
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}


}
