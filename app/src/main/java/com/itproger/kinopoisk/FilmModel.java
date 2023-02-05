package com.itproger.kinopoisk;

public class FilmModel {

    private int id;
    private String nameRu;
    private String year;
    private String img;

    public FilmModel() {
    }

    public FilmModel(int id, String nameRu, String year, String img) {
        this.id = id;
        this.nameRu = nameRu;
        this.year = year;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
