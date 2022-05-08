package org.foo

class testBar implements Serializable{
    private double length
    private double width
    private double height

    public testBar(double x, double y, double z) { 
        length = x
        width = y
        height = z
    }
    
    public getHeight() {
        return height
    }
    
    public setHeight(double z) {
        height = z
    }
    
    public getWidth() {
        return width
    }

    public setWidth(double y) {
        width = y
    }

    public getLength() {
        return length
    }

    public setLength(double x) {
        length = x
    }

    public double volume(){
        return length * width * height
    }

    public double surfaceArea(){
        return 2 * (length * width + height * length + height * width)
    }
}
