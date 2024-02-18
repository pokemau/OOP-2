package Generics;

public class Cat {
    String name;

    public Cat(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) { return true; }

        return obj.equals(name);
    }

    @Override
    public String toString() {
        return name;
    }

}
