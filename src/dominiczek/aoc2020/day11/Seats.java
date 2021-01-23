package dominiczek.aoc2020.day11;

import java.util.Arrays;

public class Seats {
    private Character[][] seats;

    public Seats(Character[][] seats) {
        this.seats = seats;
    }

    private boolean isEmpty(Character seat) {
        return seat.equals('L');
    }

    private boolean isOccupied(Character seat) {
        return seat.equals('#');
    }

    private int countOccupiedNeighbours(int x,int y) {
        int result = 0;
        for(int i=x-1;i<=x+1;i++) {
            for(int j=y-1;j<=y+1;j++) {
                if(i!=x || j!=y) {
                    if(isOccupied(seats[i][j])) {
                        result++;
                    }
                }
            }
        }
        return result;
    }

    public int countFirstOccupiedSeats(int x, int y) {
        int result = 0;
        if(hasOccupiedSeatE(x, y)) {
            result++;
        }
        if(hasOccupiedSeatW(x, y)) {
            result++;
        }
        if(hasOccupiedSeatN(x, y)) {
            result++;
        }
        if(hasOccupiedSeatS(x, y)) {
            result++;
        }
        if(hasOccupiedSeatEN(x, y)) {
            result++;
        }
        if(hasOccupiedSeatES(x, y)) {
            result++;
        }
        if(hasOccupiedSeatWN(x, y)) {
            result++;
        }
        if(hasOccupiedSeatWS(x, y)) {
            result++;
        }

        return result;
    }

    private boolean hasOccupiedSeatWN(int x, int y) {
        int j = y-1;
        for(int i=x-1;i>=0 && j>=0;i--,j--) {
            Character seat = seats[i][j];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatWS(int x, int y) {
        int j = y+1;
        for(int i=x-1;i>=0 && j<seats[0].length;i--, j++) {
            Character seat = seats[i][j];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatEN(int x, int y) {
        int j = y - 1;
        for (int i = x + 1; i < seats.length && j >= 0; i++, j--) {
            Character seat = seats[i][j];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatES(int x, int y) {
        int j = y+1;
        for(int i=x+1;i<seats.length && j<seats[0].length;i++,j++) {
            Character seat = seats[i][j];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatN(int x, int y) {
        for(int i=y-1;i>=0;i--) {
            Character seat = seats[x][i];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatS(int x, int y) {
        for(int i=y+1;i<seats[0].length;i++) {
            Character seat = seats[x][i];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatW(int x, int y) {
        for(int i=x-1;i>=0;i--) {
            Character seat = seats[i][y];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    private boolean hasOccupiedSeatE(int x, int y) {
        for(int i=x+1;i<seats.length;i++) {
            Character seat = seats[i][y];
            if(isOccupied(seat)) {
                return true;
            } else if(isEmpty(seat)) {
                return false;
            }
        }
        return false;
    }

    public int countOccupied() {
        int result = 0;
        for(int i=0;i<seats.length;i++) {
            for(int j=0;j<seats[0].length;j++) {
                if(isOccupied(seats[i][j])) {
                    result++;
                }
            }

        }
        return result;
    }

    public Seats move1() {
        Character[][] newArray = new Character[seats.length][seats[0].length];

        for(int i=0;i<seats.length;i++) {
            for(int j=0;j<seats[0].length;j++) {
                newArray[i][j] = seats[i][j];
            }
        }

        for(int i=1;i<seats.length-1;i++) {
            for(int j=1;j<seats[0].length-1;j++) {
                Character seat = seats[i][j];
                if(isEmpty(seat) && countOccupiedNeighbours(i, j)==0) {
                    newArray[i][j] = '#';
                } else if(isOccupied(seat) && countOccupiedNeighbours(i, j)>=4) {
                    newArray[i][j] = 'L';
                }
            }

        }

        return new Seats(newArray);
    }

    public Seats move2() {
        Character[][] newArray = new Character[seats.length][seats[0].length];

        for(int i=0;i<seats.length;i++) {
            for(int j=0;j<seats[0].length;j++) {
                newArray[i][j] = seats[i][j];
            }
        }

        for(int i=1;i<seats.length-1;i++) {
            for(int j=1;j<seats[0].length-1;j++) {
                Character seat = seats[i][j];
                if(isEmpty(seat) && countFirstOccupiedSeats(i, j)==0) {
                    newArray[i][j] = '#';
                } else if(isOccupied(seat) && countFirstOccupiedSeats(i, j)>=5) {
                    newArray[i][j] = 'L';
                }
            }

        }

        return new Seats(newArray);
    }


    public boolean isEqual(Seats other) {
        return Arrays.deepEquals(seats, other.seats);
    }
}
