

abstract class Shape {
    public ShapeIngredientFactory ingredientFactory;

    public void setIngredientFactory(ShapeIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    void prepare() {
        Angle angle = ingredientFactory.createAngle();
        Line line = ingredientFactory.createLine();
        System.out.println(angle.getClass().toString() + " / " + line.getClass().toString());
    }
}

class Circle extends Shape {

}

class Triangle extends Shape {

}


interface ShapeIngredientFactory {
    public Line createLine();

    public Angle createAngle();
}

class CircleShapeIngredientFactory implements ShapeIngredientFactory {
    @Override
    public Line createLine() {
        return new CircularLine();
    }

    @Override
    public Angle createAngle() {
        return new ZeroAngle();
    }
}

abstract class Line {

}

class CircularLine extends Line {

}


abstract class Angle {

}

class ZeroAngle extends Angle {

}

