package Simulation.Sim_10_Design_Bus_Seat_Booking_System;

import java.util.HashMap;
import java.util.HashSet;

public class BusBooking {

    boolean[][] mat;
    HashSet<String> seats;
    HashMap<String, Integer> Seatcost;

    int time = 0;

    public BusBooking(int n) {
        seats = new HashSet<>();
        Seatcost = new HashMap<>();
        mat = new boolean[n + 1][4];
    }

    public void toggle(String seat) {

        int c = seat.charAt(0) - 'A';
        int num = Integer.parseInt(seat.substring(1));
        if(seats.contains(seat)){

            mat[num][c] = false;
            int cost = Seatcost.get(seat);
            seats.remove(seat);
            time -= cost;
        }
        else{
            mat[num][c] = true;
            if(c == 0){
                if(mat[num][1]){
                    Seatcost.put(seat, 3);
                    time += 3;
                }
                else{
                    Seatcost.put(seat, 1);
                    time += 1;
                }
            }
            else if(c == 3){
                if(mat[num][2]){
                    Seatcost.put(seat, 3);
                    time += 3;
                }
                else{
                    Seatcost.put(seat, 1);
                    time += 1;
                }
            }
            else{
                Seatcost.put(seat, 1);
                time += 1;
            }
            seats.add(seat);

        }
    }

    public int getTotalTime() {
        return time;
    }

    public int getMinTime() {
        return seats.size();
    }
}

/**
 * Your BusBooking object will be instantiated and called as such:
 * BusBooking obj = new BusBooking(n);
 * obj.toggle(seat);
 * int param_2 = obj.getTotalTime();
 * int param_3 = obj.getMinTime();
 */