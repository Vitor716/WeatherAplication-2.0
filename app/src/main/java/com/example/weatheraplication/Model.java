package com.example.weatheraplication;




public class Model {

    private  int id;
    private String title;

    public Model(){
        this.id = id;
        this.title = title;
    }

    public Model(int customID,  String customTitle){
        this.id = customID;
        this.title = customTitle;
    }


    @Override
    public String toString() {
        return "Model{" +
                ", id=" + id +
                ", title ='" + title + '\'' +
                '}';
    }

    //Getters and Setters

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }


    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }
}