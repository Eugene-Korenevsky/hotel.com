package com.company.model.order;

import java.sql.Timestamp;

import com.company.model.room.Room;
import com.company.model.user.User;

public class Reserve {
     private String totalPrice;
     private Timestamp dateIn;
     private Timestamp dateOut;
     private long id;
     private long userId;
     private long roomId;
     private Room room;
     private User user;

    public String getTotalPrice() {
        return totalPrice;
    }

    public User getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    public long getRoomId() {
        return roomId;
    }

    public Timestamp getDateIn() {
        return dateIn;
    }

    public Timestamp getDateOut() {
        return dateOut;
    }

    public long getId() {
        return this.id;
    }

    public long getUserId() {
        return userId;
    }

    public void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    public void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
