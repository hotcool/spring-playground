package com.example.model;

public class ClosedLineSegments {

    private String type;
    private int radius;
    private int width;
    private int height;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ClosedLineSegments(String type, int radius, int width, int height) {
        this.type = type;
        this.radius = radius;
        this.width = width;
        this.height = height;
    }

    public ClosedLineSegments() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClosedLineSegments that = (ClosedLineSegments) o;

        if (radius != that.radius) return false;
        if (width != that.width) return false;
        if (height != that.height) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + radius;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    @Override
    public String toString() {
        return "ClosedLineSegments{" +
                "type='" + type + '\'' +
                ", radius=" + radius +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
