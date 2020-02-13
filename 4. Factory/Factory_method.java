
interface ShapeFactory {
    public Shape createShape();
}

class CircleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Circle();
    }
}

class TriangleFactory implements ShapeFactory {
    @Override
    public Shape createShape() {
        return new Triangle();
    }
}

abstract class Shape {

}

class Circle extends Shape {

}

class Triangle extends Shape {

}