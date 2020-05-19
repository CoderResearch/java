package org.jit.delete;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import javax.swing.*;
public class DeleteAndSerachChoiceQuestionView extends JPanel{
    JTextField mQuestionID;
    JTextField mApplicable;
    JComboBox<String> mComplexityBox;
    JButton searchBN;
    JButton deleteBN;
    JButton accurateSearchBN;
    JRadioButton[] mComplexityBN;
    JCheckBox mCheckBoxA;
    JCheckBox mCheckBoxB;
    JCheckBox mCheckBoxC;
    JCheckBox mCheckBoxD;
    JCheckBox mCheckBoxAll;

    CheckboxGroup mCheckboxGroup;
    JTable table;

    String [] ziduan={};
    String [][] record={};
    JScrollPane js;//滚动视图
    DeleteAndSearchChoiceHandle mHandle;
    MultiselectClickListener msultiselectListener;//点击全选时的事件监听

    DeleteAndSerachChoiceQuestionView()
    {

        setLayout(null);
        setVisible(true);

        JLabel helpJLabel=new JLabel("提示：可以单独通过试题号进行精确查询,也可以通过适用工程和难易程度进行模糊查询。");
        helpJLabel.setBounds(5, 5, 600, 35);
        add(helpJLabel);
        mQuestionID=new JTextField(10);
        JLabel mIdLabel =new JLabel("试题号：");
        accurateSearchBN=new JButton("精确查询");
        add(accurateSearchBN);
        add(mIdLabel);
        add(mQuestionID);
        accurateSearchBN.setBounds(685, 45, 100, 35);
        mIdLabel.setBounds(5,45,165,35);
        mQuestionID.setBounds(155,45,500,35);

        mApplicable=new JTextField(10);
        JLabel mApplicableLabel=new JLabel("适用工程：");
        add(mApplicableLabel);
        add(mApplicable);
        mApplicableLabel.setBounds(5,85,150,35);
        mApplicable.setBounds(155,85,500,35);

        JLabel mComplexityLabel=new JLabel("请选择难易程度：");
        mComplexityBN=new JRadioButton[5];
        mComplexityBN[0]=new JRadioButton("A");
        mComplexityBN[1]=new JRadioButton("B");
        mComplexityBN[2]=new JRadioButton("C");
        mComplexityBN[3]=new JRadioButton("D");
        mComplexityBN[4]=new JRadioButton("全选");
        msultiselectListener=new MultiselectClickListener();
        msultiselectListener.setView(this);
        mComplexityBN[4].addActionListener(msultiselectListener);
        for(int i=0;i<5;i++)
        {
            add(mComplexityBN[i]);
            mComplexityBN[i].setBounds(165+i*70,125, 60, 35);
        }
        add(mComplexityLabel);
        mComplexityLabel.setBounds(5, 125, 150, 35);

        searchBN=new JButton("模糊查询");
        searchBN.setBounds(300, 165, 100, 35);

        deleteBN=new JButton("删除记录");
        deleteBN.setBounds(420, 165, 100, 35);
        add(searchBN);
        add(deleteBN);

        table = new JTable(record,ziduan);
        js=new JScrollPane(table);
        js.setBounds(5, 205, 800, 400);
        js.setVisible(true);
        add(js);

        mHandle=new DeleteAndSearchChoiceHandle();
        mHandle.setView(this);
        accurateSearchBN.addActionListener(mHandle);
        searchBN.addActionListener(mHandle);
        deleteBN.addActionListener(mHandle);

    }

}
