package com.com_spidey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

class Security {

    public int validate(int enteredNumber) {               // Validates account number
        int i;
        int n = Storage.customerCount;
        for(i=0; i<n; i++){
            if(Storage.accountNumber[i]==enteredNumber){
                System.out.println();
                System.out.println("Hello " + Storage.customerNames[i]);
                break;
            }
        }
        if(i<n){
            return 1;
        }
        else if(i==n){
            System.out.println("Invalid account number, Please try again.");
        }
        return 0;
    }
    public int validate(String enteredPIN){                       // POLYMORPHISM
        int enteredNumberPIN = Integer.parseInt(enteredPIN);      // Validates PIN
        int i;
        int n = Storage.customerCount;
        for(i=0; i<n; i++){
            if(Storage.pinNumber[i]==enteredNumberPIN){
                System.out.println("ABC Bank");
                break;
            }
        }
        if(i<n){
            return 1;
        }
        else if(i==n){
            System.out.println();
            System.out.println("Invalid PIN, Please try again.");
            System.out.println();
        }
        return 0;
    }

    public int otpGenerator(){
        return (int) Math.floor(Math.random()*10000);
    }          // A random OTP Generator

    public String currentDateTime(){                                                     // Gives time at any particular instance
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("hh:mm:ss aa");
        return dateformat.format(c.getTime());
    }
}