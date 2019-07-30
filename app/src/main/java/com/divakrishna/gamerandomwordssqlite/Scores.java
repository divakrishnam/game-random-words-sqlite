package com.divakrishna.gamerandomwordssqlite;

public class Scores {
    private long id;
    private String name;
    private String score;

    public Scores(){

    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getScore(){
        return score;
    }

    public void setScore(String score){
        this.score = score;
    }

    public String toString(){
        return name+" - "+score;
    }

}
