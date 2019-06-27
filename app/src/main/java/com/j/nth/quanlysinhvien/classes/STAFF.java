package com.j.nth.quanlysinhvien.classes;

public class STAFF {
    int id;
    String name;
    String age;
    String address;
    byte[] image;

    public STAFF(int id, String name, String age, String address, byte[] image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public byte[] getImage() {
        return image;
    }
}
