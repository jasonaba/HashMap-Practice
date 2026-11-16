package HashMapLab;

public class Pokemon {
    private String name;
    private int hp;
    private String type; //ex: {grass, poison}
    

    public Pokemon(String name, int hp, String type) {
        this.name = name;
        this.hp = hp;
        this.type = type;
    }

    public String toString() {
        return name + " | HP: " + hp + " | Type: " + type;
    }
}