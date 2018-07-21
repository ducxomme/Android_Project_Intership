package com.example.huuduc.intership_project.data.model;

public class Rating {

    private String one_star;
    private String two_star;
    private String three_star;
    private String four_star;
    private String five_star;

    public Rating() {
    }

    public Rating(String one_star, String two_star, String three_star, String four_star, String five_star) {
        this.one_star = one_star;
        this.two_star = two_star;
        this.three_star = three_star;
        this.four_star = four_star;
        this.five_star = five_star;
    }

    public String getOne_star() {
        return one_star;
    }

    public void setOne_star(String one_star) {
        this.one_star = one_star;
    }

    public String getTwo_star() {
        return two_star;
    }

    public void setTwo_star(String two_star) {
        this.two_star = two_star;
    }

    public String getThree_star() {
        return three_star;
    }

    public void setThree_star(String three_star) {
        this.three_star = three_star;
    }

    public String getFour_star() {
        return four_star;
    }

    public void setFour_star(String four_star) {
        this.four_star = four_star;
    }

    public String getFive_star() {
        return five_star;
    }

    public void setFive_star(String five_star) {
        this.five_star = five_star;
    }
}
