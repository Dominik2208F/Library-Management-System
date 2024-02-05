package org.example.LibraryManager;

public class Genre implements Comparable<Genre> {
    private String name;

    public Genre(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Genre o) {
        return name.compareTo(o.name);
    }
}
