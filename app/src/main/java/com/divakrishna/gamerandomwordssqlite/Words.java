package com.divakrishna.gamerandomwordssqlite;

public class Words {
    private long id;
    private String question;
    private String answer;

    public Words(){

    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getQuestion(){
        return question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getAnswer(){
        return answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public String toString(){
        return question+" - "+answer;
    }
}
