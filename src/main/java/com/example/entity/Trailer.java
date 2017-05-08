package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Trailer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trailerId;
    private Date releaseDate;
    private String rating;
    private String title;

    public Trailer(Date releaseDate, String rating, String title) {
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.title = title;
    }

    public Trailer() {
    }

    public Long getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(Long trailerId) {
        this.trailerId = trailerId;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
