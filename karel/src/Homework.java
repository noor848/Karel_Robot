import stanford.karel.SuperKarel;

public class Homework extends SuperKarel {
    /* You fill the code here */
    private int steps = 0;
    private int beepers = 0;
    private int column = 1;
    private int row = 1;
    public void run() {
        beepers = 0;
        steps = 0;
        int beepersNumber = 1000;
        setBeepersInBag(beepersNumber);
        countRowAndColumn(); // count the row and column
        System.out.println("Row = " + row);
        System.out.println("Column = " + column);
        if (column != 0 && row != 0 && (column * row) > 1) {
            if (row < 4  || column < 4 ){
                if(row <= 7 && column <= 7){
                    if (column > row && isEven(column)) divideEvenHorizontalDimensionShape(row, column);
                    if (column >= row && isOdd(column)) divideOddHorizontalDimensionShape(row, column);
                    if (column < row && isOdd(row)) divideOddVerticalDimension(column, row);
                    if (column < row && isEven(row)) divideEvenVerticalDimension(row, column);
                }
                else {
                    if (column > row) divideHorizontalDimensionShape(row, column);
                    else divideVerticalDimensionShape(column, row);
                }
            }
            else {   /// case row and col >= 4
                if (isEven(column) && isEven(row) && column == row) divideEvenDimensionsSquareShape();
                else if (isEven(column) && isEven(row) && column != row) divideEvenDimensionsRectangleShape(column, row);
                else if (isOdd(column) && isOdd(row)) divideOddDimensionsShape(column, row);
                else {
                    if (isOdd(column) && isEven(row)) divideOddColumnEvenRowShape(column, row);
                    if (isEven(column) && isOdd(row)) divideEvenColumnOddRowShape(column, row);
                }
            }
        }
        System.out.println("Steps = " + steps);
        System.out.println("beepers = " + beepers);
    }
    private void divideEvenColumnOddRowShape(int col, int row) {
        int colSteps = col /2;
        int rowSteps = row /2;
        turnLeft();
        while(colSteps > 0){
            moveOneStep();
            colSteps --;
        }
        addBeeper();
        turnLeftAndMoveOneStep();
        addLineOfBeepers();
        addBeeper();
        turnLeftAndMoveOneStep();
        moveLeftAndAddLineOfBeepers();
        addBeeperAndTurnRight();
        moveStepAndTurnRight();
        turnLeft();
        goForwardTillCantgoMore();
        turnRight();
        while(rowSteps > 0 ){
            moveOneStep();
            rowSteps --;
        }
        turnRightAndAddBeeper();
        moveForwardAndAddBeepers();
    }
    private void divideOddColumnEvenRowShape(int col, int row) {
        int colSteps = col /2;
        int rowSteps = row /2;
        turnLeft();
        while(colSteps > 0 ){
            moveOneStep();
            colSteps --;
        }
        addBeeper();
        turnLeftAndMoveOneStep();
        addLineOfBeepers();
        addBeeper();
        turnLeftAndMoveOneStep();
        goForwardTillCantgoMore();
        turnLeft();
        while(rowSteps > 0 ){
            moveOneStep();
            rowSteps --;
        }
        turnLeftAndAddBeeper();
        moveForwardAndAddBeepers();
        turnLeftAndMoveOneStep();
        addBeeperAndTurnLeft();
        moveForwardAndAddBeepers();
    }
    private void divideOddDimensionsShape(int col, int row) {
        int colSteps = col /2;
        int rowSteps = row /2;

        turnLeft();
        while(colSteps > 0 ){
            moveOneStep();
            colSteps --;
        }
        addBeeper();
        turnLeftAndMoveOneStep();
        addLineOfBeepers();
        addBeeper();
        turnLeftAndMoveOneStep();
        goForwardTillCantgoMore();
        turnLeft();
        while(rowSteps > 0 ){
            moveOneStep();
            rowSteps --;
        }
        turnLeftAndAddBeeper();
        moveForwardAndAddBeepers();
    }
    private void divideEvenDimensionsRectangleShape(int col, int row) {
        int colSteps = col /2;
        int rowSteps = row /2;
        turnLeft();
        while(colSteps > 0 ){
            moveOneStep();
            colSteps --;
        }
        addBeeper();
        turnLeftAndMoveOneStep();
        addLineOfBeepers();
        addBeeper();
        turnLeftAndMoveOneStep();///
        moveLeftAndAddLineOfBeepers();
        addBeeperAndTurnRight();
        moveOneStep();
        goForwardTillCantgoMore();
        turnRight();
        while(rowSteps > 0 ){
            moveOneStep();
            rowSteps --;
        }
        turnRightAndAddBeeper();
        moveForwardAndAddBeepers();
        turnRightAndMoveOneStep();
        addBeeperAndTurnRight();
        moveForwardAndAddBeepers();
    }
    private void divideEvenDimensionsSquareShape() {
        turnAround();
        addBeeper();
        moveOneStep();//
        turnRightAndMoveAddBeeperTurnLeftAndMove();///
        turnRightAndMoveOneStep();///
        addBeeperAndTurnRight();
        moveOneStep();
        turnLeft();
        turnRight();
        goForwardTillCantgoMore();
        turnRightAndAddBeeper();
        moveOneStep();
        turnRightAndMoveAddBeeperTurnLeftAndMove();
        turnRightAndMoveOneStep();
        addBeeper();
    }
    private void divideVerticalDimensionShape(int col, int row) {
        if (isEven(row)) {
            int columnSteps = col == 1 ? 1 : col / 2;
            int steps = columnSteps;
            while (columnSteps >= steps) {
                int midPoint = row / 2;
                int sidesAroundMidPoint = row / 2 - 1;
                turnAround();
                moveToMidPoint(midPoint);
                addBeeperAndTurnRight();
                addLineOfBeepers();
                addBeeperAndTurnLeft();
                moveOneStep();
                moveLeftAndAddLineOfBeepers();
                addBeeperAndTurnRight();
                if (isEven(sidesAroundMidPoint)) {
                    midPoint = row / 2;
                    int leftSideMid = midPoint / 2;
                    while (leftSideMid > 0) {
                        moveOneStep();
                        leftSideMid --;
                    }
                    addBeeperAndTurnRight();
                    addLineOfBeepers();
                    addBeeperAndTurnLeft();
                    moveOneStep();
                    moveLeftAndAddLineOfBeepers();
                    addBeeperAndTurnLeft();
                    int secondLine = getSecondLineCaculateFromMidPointOfEvenSide(midPoint, leftSideMid);
                    moveToMidPoint(secondLine);
                    addBeeperAndTurnLeft();
                    addLineOfBeepers();
                    addBeeperAndTurnRight();
                    moveStepAndTurnRight();
                    addLineOfBeepers();
                    addBeeper();
                } else {
                    midPoint = row / 2;
                    int leftSideMid = midPoint / 2;
                    while (leftSideMid > 0) {
                        moveOneStep();
                        leftSideMid--;
                    }
                    addBeeperAndTurnRight();
                    moveOneStep();
                    addLineOfBeepers();
                    addBeeperAndTurnRight();
                    int rightSideMid = getRightSideMidCalclatefromMidPointOfOddSide(row, midPoint);
                    moveToMidPoint(rightSideMid);
                    addBeeperAndTurnRight();
                    addLineOfBeepers();
                    addBeeper();
                }
                columnSteps--;
            }
        }
        else {
            int columnSteps = col == 1 ? 1 : col / 2;
            int steps = columnSteps;
            while (columnSteps >= steps) {
                int midPoint = (row / 2) + 1;
                int sidesAroundMidPoint = row / 2;
                turnAround();
                while (midPoint > 1) {
                    moveForward();
                    midPoint --;
                }
                addBeeperAndTurnRight();
                moveForwardAndAddBeepers();
                turnLeft();
                if (isEven(sidesAroundMidPoint)) {
                    midPoint = row / 2;
                    int leftSideMid = midPoint / 2;
                    moveToMidPoint(leftSideMid);
                    moveOneStep();
                    addBeeperAndTurnRight();
                    turnLeftAndAddBeeper();
                    addBeeperAndTurnLeft();
                    moveForwardAndAddBeepers();
                    turnRightAndMoveOneStep();
                    addBeeperAndTurnRight();
                    moveForwardAndAddBeepers();
                    turnRight();
                    int secondLine = getRightSideMidCalclatefromMidPointOfOddSide(row, midPoint);
                    moveToMidPoint(secondLine);
                    addBeeperAndTurnRight();
                    moveForwardAndAddBeepers();
                    turnLeftAndMoveOneStep();
                    addBeeperAndTurnLeft();
                    moveForwardAndAddBeepers();
                }
                else {
                    midPoint = row / 2;
                    int leftSideMid = midPoint / 2;
                    while (leftSideMid >= 0) {
                        moveOneStep();
                        leftSideMid --;
                    }
                    moveLeftAndAddLineOfBeepers();
                    turnLeftAndAddBeeper();
                    int rightSideMid = getRightSideMidCalclatefromMidPointOfOddSide(row, midPoint);
                    moveToMidPoint(rightSideMid);
                    moveLeftAndAddLineOfBeepers();
                    addBeeper();
                }
                columnSteps --;
            }
        }
    }
    private void moveToMidPoint(int midPoint) {
        while (midPoint > 1) {
            moveOneStep();
            midPoint--;
        }
    }
    private void divideHorizontalDimensionShape(int row, int col) {
        if (isEven(col)) {
            int rowSteps = row == 1 ? 1 : row / 2;
            int steps = rowSteps;
            while (rowSteps >= steps) {
                int midPoint = col / 2;
                int sidesAroundMidPoint = col / 2 - 1;
                turnLeft();
                while (midPoint > 1) {

                    moveForward();
                    midPoint--;
                }
                addBeeperAndTurnLeft();
                addLineOfBeepers();
                addBeeperAndTurnRight();
                moveForward();
                turnRightTheAddBeepersWhileWalking();
                addBeeperAndTurnLeft();
                if (isEven(sidesAroundMidPoint)) {
                    midPoint = col / 2;
                    int leftSideMid = midPoint / 2;
                    while (leftSideMid > 0) {
                        moveOneStep();
                        leftSideMid--;
                    }
                    addBeeperAndTurnLeft();
                    addLineOfBeepers();
                    addBeeperAndTurnRight();
                    moveForward();
                    turnRightTheAddBeepersWhileWalking();
                    addBeeperAndTurnRight();
                    int rightSideMid = getSecondLineCaculateFromMidPointOfEvenSide(midPoint, leftSideMid);
                    moveToMidPoint(rightSideMid);
                    addBeeperAndTurnRight();
                    addLineOfBeepers();
                    addBeeperAndTurnLeft();
                    moveForward();
                    moveLeftAndAddLineOfBeepers();
                    addBeeper();
                } else {
                    midPoint = col / 2;
                    int leftSideMid = midPoint / 2;
                    while (leftSideMid > 0) {
                        moveOneStep();
                        leftSideMid--;
                    }
                    addBeeperAndTurnLeft();
                    moveForward();
                    addLineOfBeepers();
                    addBeeperAndTurnLeft();
                    int rightSideMid = getRightSideMidCalclatefromMidPointOfOddSide(col, midPoint);
                    moveToMidPoint(rightSideMid);
                    addBeeperAndTurnLeft();
                    addLineOfBeepers();
                    addBeeper();
                }
                rowSteps--;
            }
        }
        else {
            int rowSteps = row == 1 ? 1 : row / 2;
            int steps = rowSteps;
            while (rowSteps >= steps) {
                int midPoint = (col / 2) +1;
                int sidesAroundMidPoint = col / 2;
                turnLeft();
                while (midPoint > 1) {
                    moveForward();
                    midPoint--;
                }
                addBeeperAndTurnLeft();
                moveForwardAndAddBeepers();
                turnRight();


                if (isEven(sidesAroundMidPoint)) {
                    midPoint = col / 2;
                    int EvenMid = midPoint / 2;
                    moveToMidPoint(EvenMid);
                    moveOneStep();
                    addBeeperAndTurnLeft();
                    turnRightAndAddBeeper();
                    addBeeperAndTurnRight();
                    moveForwardAndAddBeepers();
                    turnLeftAndMoveOneStep();
                    addBeeperAndTurnLeft();
                    moveForwardAndAddBeepers();
                    turnLeft();

                    int secondEvenMid = getRightSideMidCalclatefromMidPointOfOddSide(col, midPoint);
                    moveToMidPoint(secondEvenMid);
                    addBeeperAndTurnLeft();
                    moveForwardAndAddBeepers();
                    turnRightAndMoveOneStep();
                    addBeeperAndTurnRight();
                    moveForwardAndAddBeepers();

                }
                else {
                    midPoint = col / 2;
                    int EvenMid = midPoint / 2;
                    while (EvenMid >= 0) {
                        moveOneStep();
                        EvenMid--;
                    }
                    turnRightTheAddBeepersWhileWalking();
                    turnRightAndAddBeeper();

                    int rightSideMid = getRightSideMidCalclatefromMidPointOfOddSide(col, midPoint);
                    moveToMidPoint(rightSideMid);
                    turnRightTheAddBeepersWhileWalking();
                    addBeeper();
                }
                rowSteps--;
            }
        }
    }
    private void divideEvenVerticalDimension(int row, int col) {
        int r = row /4;
        int columnSteps;
        turnLeft();
        while (r >= 0){
            columnSteps = col;
            setBeepersAndMove(columnSteps);
            if (r == 0 ) break;
            turnLeftAndMoveOneStep();
            moveForward();
            turnLeft();
            setBeepersAndMove(columnSteps);
            r--;
            if (r == 0  && row == 4) break;
            turnRightAndMoveOneStep();
            moveForward();
            turnRight();
        }
    }
    private void divideOddVerticalDimension(int col, int row) {
        int columnLines;
        int rowSteps = row / 2;
        turnAround();
        while (rowSteps >= 0)   {
            columnLines = col;
            moveForward();
            turnRight();
            setBeepersAndMove(columnLines);
            rowSteps--;
            if ((rowSteps  == 0 && row == 3) || (rowSteps  == 1 && row == 7)) break;
            turnLeftAndMoveOneStep();
            moveForward();
            turnLeft();
            setBeepersAndMove(columnLines);
            if (rowSteps == 1) break;
            turnRightAndAddBeeper();
            moveForward();
        }
    }
    private void divideOddHorizontalDimensionShape(int row, int col) {
        int rowLines;
        int columnSteps = col / 2;
        turnLeft();
        while (columnSteps >= 0)   {
            rowLines = row;
            moveForward();
            turnLeft();
            setBeepersAndMove(rowLines);
            columnSteps--;
            if ((columnSteps  == 0 && col == 3) || (columnSteps  == 1 && col == 7)) break;
            turnRightAndMoveOneStep();
            moveForward();
            turnRight();
            setBeepersAndMove(rowLines);
            if (columnSteps == 1 ) break;
            turnLeftAndAddBeeper();
            moveForward();
        }
    }
    private void divideEvenHorizontalDimensionShape(int row, int col) {
        int rowLines;
        int columnSteps = col / 4;
        turnAround();
        while (columnSteps >= 0)   {
            rowLines = row;
            setBeepersAndMove(rowLines);
            if (columnSteps == 0 ) break;
            turnRightAndMoveOneStep();
            moveForward();
            turnRight();
            rowLines = row;
            setBeepersAndMove(rowLines);
            columnSteps--;
            if (columnSteps == 0  && col == 4) break;
            turnLeftAndMoveOneStep();
            moveForward();
            turnLeft();
        }
    }
    public void countRowAndColumn() {
        column = 1;
        row = 1;
        while (frontIsClear()) {
            moveOneStep();
            column++;
        }
        turnLeft();
        while (frontIsClear()) {
            moveOneStep();
            row++;
        }
    }
    private void turnRightAndAddBeeper() {
        turnRight();
        addBeeper();
    }
    private void goForwardTillCantgoMore() {
        while (frontIsClear()) {
            moveOneStep();
        }
    }
    private void addBeeperAndTurnRight() {
        addBeeper();
        turnRight();
    }
    private void addLineOfBeepers() {
        while (frontIsClear()){
            addBeeper();
            moveOneStep();
        }
    }
    private void moveForwardAndAddBeepers() {
        while (frontIsClear()){
            moveOneStep();
            addBeeper();
        }
    }
    private void turnLeftAndMoveOneStep() {
        turnLeft();
        moveOneStep();
    }
    private void addBeeperAndTurnLeft() {
        addBeeper();
        turnLeft();
    }
    private void turnLeftAndAddBeeper() {
        turnLeft();
        addBeeper();
    }
    private void turnRightAndMoveOneStep() {
        turnRight();
        moveOneStep();
    }
    private void turnRightAndMoveAddBeeperTurnLeftAndMove() {
        while (frontIsClear()) {
            turnRightAndMoveOneStep();
            addBeeper();
            turnLeftAndMoveOneStep();
        }
    }
    private static int getRightSideMidCalclatefromMidPointOfOddSide(int row, int midPoint) {
        return midPoint / 2 + ((row + 1) / 4) + 2;
    }
    private static int getSecondLineCaculateFromMidPointOfEvenSide(int midPoint, int leftSideMid) {
        return ((midPoint + 1) / 2) * 2 + leftSideMid + 1;
    }
    private void moveStepAndTurnRight() {
        moveOneStep();
        turnRight();
    }
    private void moveLeftAndAddLineOfBeepers() {
        turnLeft();
        addLineOfBeepers();
    }
    public void moveOneStep() {
        if (frontIsClear()) {
            move();
            steps++;
        }
    }
    public void addBeeper() {
        if (!beepersPresent()) {
            putBeeper();
            beepers++;
        }
    }
    private void turnRightTheAddBeepersWhileWalking() {
        turnRight();
        addLineOfBeepers();
    }
    private void setBeepersAndMove(int steps) {
        while (steps >= 1) {
            addBeeper();
            moveForward();
            steps--;
        }
    }
    private void moveForward() {
        moveOneStep();
    }
    public boolean isEven(int input){
        return input % 2 == 0;
    }
    public boolean isOdd(int input){
        return input % 2 != 0;
    }
}