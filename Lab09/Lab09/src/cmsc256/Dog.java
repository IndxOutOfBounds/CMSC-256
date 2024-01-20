package cmsc256;

import java.util.Objects;

public class Dog implements Comparable<Dog>{
    private String dogName;
    private int count;

    public Dog(String dogName, int count) {
        this.dogName = dogName;
        this.count = count;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int compareTo(Dog o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dog dog = (Dog) o;
        return getCount() == dog.getCount() && Objects.equals(getDogName(), dog.getDogName());
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                ", count=" + count +
                '}';
    }
}
    
    
