package dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Food implements Comparable<Food>, Serializable {

    private int id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private LocalDate expiredDate;

    public Food(int id, String name, double weight, String type, String place, LocalDate expiredDate) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("invalid id");
        }
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid weight");
        }
        if (type == null || type.equals("")) {
            throw new IllegalArgumentException("Invalid type");
        }
        if (place == null || place.equals("")) {
            throw new IllegalArgumentException("Invalid place");
        }
        if (expiredDate == null) {
            throw new IllegalArgumentException("Invalid expired date");
        }

        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    public Food(int id, String name, double weight, String type, String place, String expiredDate) throws IllegalArgumentException {
        this(id, name, weight, type, place, LocalDate.parse(expiredDate));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid Id");
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Invalid name");
        }
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) throws IllegalArgumentException {
        if (weight <= 0) {
            throw new IllegalArgumentException("Invalid weight");
        }
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) throws IllegalArgumentException {
        if (type == null || type.equals("")) {
            throw new IllegalArgumentException("Invalid type");
        }
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) throws IllegalArgumentException {
        if (place == null || place.equals("")) {
            throw new IllegalArgumentException("Invalid place");
        }
        this.place = place;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) throws IllegalArgumentException {
        if (expiredDate == null) {
            throw new IllegalArgumentException("Invalid expired date");
        }
        this.expiredDate = expiredDate;
    }

    public void setExpiredDate(String expiredDate) throws IllegalArgumentException {
        if (expiredDate == null) {
            throw new IllegalArgumentException("Invalid date input format");
        }
        try {
            LocalDate formattedExpiredDate = LocalDate.parse(expiredDate);
            this.expiredDate = formattedExpiredDate;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date input format");
        }
    }

    @Override
    public String toString() {
        return this.id + " | " + this.name + " | " + this.weight + " | " + this.type + " | " + this.place + " | " + this.expiredDate;
    }

    @Override
    public int compareTo(Food t) {
        if (t.expiredDate.isAfter(this.expiredDate)) {
            return 1;
        } else if (t.expiredDate.isBefore(this.expiredDate)) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Food)) {
            return false;
        }

        Food comparedFood = (Food) obj;

        if (comparedFood.id != this.id) {
            return false;
        }

        return true;
    }
}
