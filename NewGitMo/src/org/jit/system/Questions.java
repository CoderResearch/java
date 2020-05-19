package org.jit.system;

public class Questions {
    boolean isChoice;//是否为单选题
    boolean isMultipleChoice;//是否为多选题
    boolean isJudgement;//是否为判断题
    String mContext="";//试题内容
    String mChoiceAContext="";//A选项内容
    String mChoiceBContext="";//B选项内容
    String mChoiceCContext="";//C选项内容
    String mChoiceDContext="";//D选项内容
    String mImageName="";//图片名称
    String mCurrectAnswer="";//正确答案
    String mUserAnswer="";//用户答案
    String mApplicable="";//适用工程
    String mType="";//试题类型
    int mID;//初始化数组编号

    //设置是否为单选题
    public void setIsChoice(boolean isChoice)
    {
        this.isChoice=isChoice;
    }
    //获取是否为单选题
    public boolean getIsChoice()
    {
        return this.isChoice;
    }
    //设置是否为多选题
    public void setIsMultipleChoice(boolean isMultipleChoice)
    {
        this.isMultipleChoice=isMultipleChoice;
    }
    //获取是否为单选题
    public boolean getIsMultipleChoice()
    {
        return this.isMultipleChoice;
    }
    //设置是否为判断选题
    public void setIsJudgement(boolean isJudgement)
    {
        this.isJudgement=isJudgement;
    }
    //得到是否为判断选题
    public boolean getIsJudgement()
    {
        return this.isJudgement;
    }
    //设置题目内容
    public void setContext(String context)
    {
        this.mContext=context;
    }
    //得到题目内容
    public String getContext()
    {
        return this.mContext;
    }
    //设置图片名字
    public void setImageName(String imageName)
    {
        this.mImageName=imageName;
    }
    //得到图片名字
    public String getImageName()
    {
        return this.mImageName;
    }
    //设置a选项内容
    public void setChoiceAContext(String ChoiceAContext)
    {
        this.mChoiceAContext=ChoiceAContext;
    }
    //得到a选项内容
    public String getChoiceAContext()
    {
        return this.mChoiceAContext;
    }
    //b选项内容
    public void setChoiceBContext(String ChoiceBContext)
    {
        this.mChoiceBContext=ChoiceBContext;
    }
    //得到b选项内容
    public String getChoiceBContext()
    {
        return this.mChoiceBContext;
    }
    //c选项内容
    public void setChoiceCContext(String ChoiceCContext)
    {
        this.mChoiceCContext=ChoiceCContext;
    }
    //得到c选项内容
    public String getChoiceCContext()
    {
        return this.mChoiceCContext;
    }
    //d选项内容
    public void setChoiceDContext(String ChoiceDContext)
    {
        this.mChoiceDContext=ChoiceDContext;
    }
    //得到d选项内容
    public String getChoiceDContext()
    {
        return this.mChoiceDContext;
    }
    //设置正确答案
    public void setCurrectAnswer(String CurrectAnswer)
    {
        this.mCurrectAnswer=CurrectAnswer;
    }
    //得到正确答案
    public String getCurrectAnswer()
    {
        return this.mCurrectAnswer;
    }
    //设置用户答案
    public void setUserAnswer(String userAnswer)
    {
        this.mUserAnswer=userAnswer;
    }
    //得到用户答案
    public String getUserAnswer()
    {
        return this.mUserAnswer;
    }
    //设置适用工程
    public void setApplicable(String applicable)
    {
        this.mApplicable=applicable;
    }
    //得到适用工程
    public String getApplicable()
    {
        return this.mApplicable;
    }
    //设置题型
    public void setType(String type)
    {
        this.mType=type;
    }
    //得到题型
    public String getType()
    {
        return this.mType;
    }
    //设置题目序号

    public void setNumber(int number)
    {
        this.mID=number;
    }
    //得到题目序号
    public int getNumber()
    {
        return this.mID;
    }
}
