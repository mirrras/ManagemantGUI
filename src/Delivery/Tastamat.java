package Delivery;

public class Tastamat {
    private Integer index;
    private String city;
    private String location;

    public Tastamat(Integer index, String city, String location) {
        this.index = index;
        this.city = city;
        this.location = location;
    }

    // Геттеры и сеттеры для полей класса Tastamat

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Tastamat{" +
                "index=" + index +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}


