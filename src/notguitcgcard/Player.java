package notguitcgcard;

public class Player {
    private int health;
    private int level;

    public Player(int health, int level) {
        this.health = health;
        this.level = level;
    }
    public void setHealth(int hp){
        this.health = hp;
    }
    public void decreaseHealth(int amount) { // 피 닳음
        health -= amount;
        if (health < 0) {
            health = 0; // 피가 넘쳐서 달아도 0으로 침
        }
    }

    public void increaseHealth(int amount) { // 피가 찬다!
        health += amount;
    }
    public int getHealth(){
        return health;
    }
}
