package com.itproger.kinopoisk;

import android.widget.TextView;

public class FilmModel {

    private int id;
    private String nameRu;
    private String year;
    private String img;
    private String description;
    private String genre;
    private String country;

    public FilmModel() {
    }

    public FilmModel(int id, String nameRu, String year, String img) {
        this.id = id;
        this.nameRu = nameRu;
        this.year = year;
        this.img = img;
    }

    public FilmModel(int id, String nameRu, String year, String img, String description, String genre, String country) {
        this.id = id;
        this.nameRu = nameRu;
        this.year = year;
        this.img = img;
        this.description = description;
        this.genre = genre;
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
