package com.example.huuduc.intership_project.data.model;

public class Rating {

    private String one;
    private String two;
    private String three;
    private String four;
    private String five;

    public Rating() {
    }

    public Rating(String one_star, String two_star, String three_star, String four_star, String five_star) {
        this.one = one_star;
        this.two = two_star;
        this.three = three_star;
        this.four = four_star;
        this.five = five_star;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }
}
