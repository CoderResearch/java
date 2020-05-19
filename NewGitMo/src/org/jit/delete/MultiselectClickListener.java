package org.jit.delete;

import java.awt.event.*;

public class MultiselectClickListener implements ActionListener{

    DeleteAndSerachChoiceQuestionView mView;
    int count=1;
    public void setView(DeleteAndSerachChoiceQuestionView view) {
        mView=view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(count%2==1)
        {
            for(int i=0;i<5;i++)
            {
                mView.mComplexityBN[i].setSelected(true);
            }
            count++;
        }else if(count%2==0)
        {
            for(int i=0;i<5;i++)
            {
                mView.mComplexityBN[i].setSelected(false);
            }
            count++;

        }
    }

}