
public abstract class Beverage {
    String description = "nothing";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract double cost();
}

public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}

public class Decaffeine extends Beverage {
    public Decaffeine() {
        description = "Decaffeine";
    }

    @Override
    public double cost() {
        return 2.99;
    }
}


public abstract class Condiment extends Beverage {
    public abstract String getDescription();
}

public class Mocha extends Condiment {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", mocha";
    }

    @Override
    public double cost() {
        return .20 + beverage.cost();
    }
}
