package org.jit.system;

//试题肯定不止一道因此就需要一个试题数组来存放所有试题，
// 因此我们还需要一个模型来管理所有试题
public class QuestionModel {
    private Questions[] mQuestions=null;
    int mIndex=0;
    //设置全部试题
    public void setQuestions(Questions[] Questions)
    {
        this.mQuestions=Questions;
    }
    //返回所有试题
    public Questions[] getAllQuestions()
    {
        if(mQuestions==null)
        {
            return null;
        }else if(mQuestions.length==0)
        {
            return null;
        }
        return this.mQuestions;
    }
    //返回对应索引试题
    public Questions getQuestions(int index)
    {
        if(mQuestions==null)
        {
            return null;
        }else if(mQuestions.length==0)
        {
            return null;
        }else if(index>=mQuestions.length||index<0)
        {
            return null;
        }

        return this.mQuestions[index];
    }
    //获取下一题
    public Questions getNextQuestion()
    {
        mIndex++;
        if(mQuestions==null)
        {
            return null;
        }else if(mQuestions.length==0)
        {
            return null;
        }else if(mIndex==mQuestions.length)
        {
            mIndex=0;
        }
        return this.mQuestions[mIndex];

    }
    //获取上一题
    public Questions getPreviousQuestion()
    {
        mIndex--;
        if(mQuestions==null)
        {
            return null;
        }else if(mQuestions.length==0)
        {
            return null;
        }else if(mIndex<0)
        {
            mIndex=mQuestions.length-1;
        }
        return this.mQuestions[mIndex];

    }
}
