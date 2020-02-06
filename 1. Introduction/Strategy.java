package com.github.kimhun456;

public class Strategy {

    public static void main(String[] args) {
        Character knight = new Knight();
        knight.attack();
        knight.setWeapon(new AxeWeapon());
        knight.attack();

        Character warrior = new Warrior();
        warrior.attack();
    }
}

abstract class Character {
    Weapon mWeapon;

    public void attack() {
        mWeapon.attack();
    }

    public void setWeapon(Weapon weapon) {
        mWeapon = weapon;
    }
}

class Warrior extends Character {

    Warrior() {
        mWeapon = new AxeWeapon();
    }

}

class Knight extends Character {

    Knight() {
        mWeapon = new SwordWeapon();
    }

}

interface Weapon {
    void attack();
}

class SwordWeapon implements Weapon {
    @Override
    public void attack() {
        System.out.println("CHAP CHAP!!!");
    }
}

class AxeWeapon implements Weapon {
    @Override
    public void attack() {
        System.out.println("Quack!!!!");
    }
}
