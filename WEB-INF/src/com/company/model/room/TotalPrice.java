package com.company.model.room;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;

public class TotalPrice {
      private Timestamp dateIn;
      private Timestamp dateOut;
      private double pricePerDay;

    public TotalPrice(Timestamp dateIn, Timestamp dateOut, double pricePerDay) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.pricePerDay = pricePerDay;
    }

    public Timestamp getDateIn() {
        return dateIn;
    }

    public Timestamp getDateOut() {
        return dateOut;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setDateIn(Timestamp dateIn) {
        this.dateIn = dateIn;
    }

    public void setDateOut(Timestamp dateOut) {
        this.dateOut = dateOut;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String totalPrice() {
        Locale locale = new Locale("en", "US");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        String timeIn = dateIn.toString();
        String timeOut = dateOut.toString();
        int index = timeIn.lastIndexOf(" ");
        timeIn = timeIn.substring(0,index);
        index = timeOut.lastIndexOf(" ");
        timeOut = timeOut.substring(0,index);
        Date inDate = Date.valueOf(timeIn);
        Date outDate = Date.valueOf(timeOut);
        long in = inDate.getTime();
        long out = outDate.getTime();
        long days = (out - in) / 86400000;
        if(days<=1) {
        	return numberFormat.format(1*pricePerDay);
        }else {
        	return numberFormat.format(days * pricePerDay);
        }
        
    } 
    
    public long longTotalPrice() {
    	long price = (long) (pricePerDay*100);
    	 String timeIn = dateIn.toString();
	        String timeOut = dateOut.toString();
	        int index = timeIn.lastIndexOf(" ");
	        timeIn = timeIn.substring(0,index);
	        index = timeOut.lastIndexOf(" ");
	        timeOut = timeOut.substring(0,index);
	        Date inDate = Date.valueOf(timeIn);
	        Date outDate = Date.valueOf(timeOut);
	        long in = inDate.getTime();
	        long out = outDate.getTime();
	        long days = (out - in) / 86400000;
	        if(days<=1) {
	        	return  (1*price);
	        }else {
	        	return  (days * price);
	        }
    	
    }

}
