package com.company.model.room;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.company.model.order.Reserve;

public class SearchRoom {
    private List<Room> rooms = new ArrayList<>();
    private List<Reserve> reserves = new ArrayList<>();
    private List<Room> semiResult = new ArrayList<>();
    private List<Room> result = new ArrayList<>();
    private Timestamp dateIn;
    private Timestamp dateOut;
    private double price;
    private int sits;

    public SearchRoom(List<Room> rooms,List<Reserve> reserves,Timestamp dateIn,Timestamp dateOut,
		double price,int sits) {
        this.rooms = rooms;
	    this.reserves = reserves;
	    this.dateIn = dateIn;
	    this.dateOut = dateOut;
	    this.sits = sits;
	    this.price = price;
	}
	
    public List<Room> doSearch(){
        for(Room room:rooms) {
        if(room.getSits() == sits) {
              semiResult.add(room);
        }
	}
        for(Room room : semiResult) {
            TotalPrice totalPrice = new TotalPrice(dateIn,dateOut,room.getPrice());
            long res = totalPrice.longTotalPrice();
            if((price*100) >= res) {
                  long inLong = dateIn.getTime();
                  long outLong = dateOut.getTime();
                     for(Reserve reserve : reserves) {
                         if(reserve.getRoomId() == room.getId()) {
                            long in1 = reserve.getDateIn().getTime();
                            long out1 = reserve.getDateOut().getTime();
                            if((inLong > in1 && inLong < out1)||(outLong > in1 && outLong < out1  )) {
                            break;
                         }else {
                             result.add(room);
                             break;
                         }
                         }
                      }
            }
         }
		
      return result;
    }

}
