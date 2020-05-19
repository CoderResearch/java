package org.jit.control;

import java.awt.event.*;
public class ExamTypeHandle implements ActionListener{

    InitExamView mView;
    String type=null;
    public void getInitExamView(InitExamView view)
    {
        this.mView=view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取到用户选择的工程类型
        type=mView.mApplicable.getSelectedItem().toString();


    }

}
