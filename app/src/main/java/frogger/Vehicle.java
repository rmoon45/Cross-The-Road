package frogger;

public class Vehicle extends AbstractVehicle {
    private int vehicleWidth;
    private int vehicleHeight;
    private float xPosition;
    private float yPosition;
    private float vehicleSpeed;


    public Vehicle(String car, int numVerticalSquares, int squareSize) {
        setVehicleHeight(squareSize);
        setVehicleWidth(car, squareSize);
        setVehicleSpeed(car, squareSize);
        setXPosition(car, squareSize);
        setYPosition(car, squareSize, numVerticalSquares);
    }

    //height is squareSize for all
    public void setVehicleHeight(int squareSize) {
        this.vehicleHeight = squareSize;
    }

    public void setVehicleWidth(String car, int squareSize) {
        if (car.equals("car1")) {
            this.vehicleWidth = squareSize;

        } else if (car.equals("car2")) {
            this.vehicleWidth = 2 * squareSize;

        } else if (car.equals("car3")) {
            this.vehicleWidth = 3 * squareSize;
        } else {
            this.vehicleWidth = 4 * squareSize;
        }
    }

    public void setXPosition(String car, float xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(String car, int squareSize, int numVerticalSquares) {
        if (car.equals("car1")) {
            this.yPosition = squareSize * (numVerticalSquares - 2) - squareSize;
        } else if (car.equals("car2")) {
            this.yPosition = squareSize * (numVerticalSquares - 2) - (2 * squareSize);
        } else if (car.equals("car3")) {
            this.yPosition = squareSize * (numVerticalSquares - 2) - (3 * squareSize);
        } else {
            this.yPosition = squareSize * (numVerticalSquares - 2) - (4 * squareSize);
        }
    }

    public void setVehicleSpeed(String car, float vehicleSpeed) {
        if (car.equals("car1") || car.equals("car2") || car.equals("car4")) {
            this.vehicleSpeed = vehicleSpeed;

        } else {
            this.vehicleSpeed = vehicleSpeed * 2;
        }
    }

    public int getVehicleWidth() {
        return vehicleWidth;
    }

    public int getVehicleHeight() {
        return vehicleHeight;
    }

    public float getxPosition() {
        return xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public float getVehicleSpeed() {
        return vehicleSpeed;
    }
}
