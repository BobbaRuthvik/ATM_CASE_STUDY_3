package com.com_spidey;

import java.util.Scanner;

interface PrimaryATMOptions {
    void banking();
    void balanceINQ();
    void transfer();
    void pinGeneration();
}

class Banking implements PrimaryATMOptions {
    private int TotalBalance = 200000;                        // Total Balance at beginning of each run.
    private int pin;
    Security db = new Security();
    Scanner input = new Scanner(System.in);
    public void banking() {                                   // Operations of BANKING options.
        if (entryStatements() == 1) {
            System.out.println("1. FROM CURRENT");
            System.out.println("2. FROM SAVING");
            int postSecondaryOption = input.nextInt();
            System.out.print("Please enter amount: ");
            int amount = input.nextInt();
            System.out.println("1. YES");
            System.out.println("2. NO");
            int finalConformation = input.nextInt();
            if (finalConformation == 1) {
                for (int i = 0; i < 100; i++) {
                    if (Storage.pinNumber[i]==(pin)) {
                        if(Storage.accountType[i]==postSecondaryOption){
                            double remainingAmount = Storage.balance[i] - amount;
                            if (amount <= 40000) {
                                if (TotalBalance > amount) {
                                    if (remainingAmount >= 0) {
                                        if(amount%500==0){
                                            if(Storage.transactionCount[i]<2){
                                                if(amount>=10000){
                                                    boolean run = true;
                                                    while (run){
                                                        int otp = db.otpGenerator();
                                                        System.out.println("/**** "+Storage.customerNames[i]+"'s MOBILE INTERFACE ****/");
                                                        System.out.println("Mobile number: "+Storage.phoneNumber[i]);
                                                        System.out.println("OTP: "+otp);
                                                        System.out.println("Message sent on: "+db.currentDateTime());
                                                        System.out.println("Check OTP on Your Registered Mobile No. If Received, Press Continue.");
                                                        System.out.println("1. CONTINUE");
                                                        System.out.println("2. RESEND OTP");
                                                        System.out.println("3. CANCEL");
                                                        int response = input.nextInt();
                                                        if(response==1){
                                                            System.out.println("Please Enter 4 Digits OTP Received Over SMS: ");
                                                            int otpEntered = input.nextInt();
                                                            System.out.println("1. CONTINUE");
                                                            System.out.println("2. CANCEL");
                                                            int response1 = input.nextInt();
                                                            if(response1==1){
                                                                if(otpEntered==otp){
                                                                    if(Storage.bankName[i]!=1){
                                                                        Storage.transactionCount[i]++;
                                                                        notesIssued(amount);
                                                                        System.out.println("Remaining balance: Rs."+remainingAmount+" - Rs.10.0(service tax) = Rs."+(remainingAmount-10));
                                                                        Storage.balance[i] = remainingAmount-10;
                                                                        System.out.println("THANK YOU FOR USING ABC BANK.");
                                                                        System.out.println("TRANSACTION COMPLETE.");
                                                                        TotalBalance = TotalBalance - amount;
                                                                    }
                                                                    else {
                                                                        Storage.transactionCount[i]++;
                                                                        notesIssued(amount);
                                                                        System.out.println("Remaining balance: Rs."+remainingAmount);
                                                                        Storage.balance[i] = remainingAmount;
                                                                        System.out.println("TRANSACTION COMPLETE.");
                                                                        TotalBalance = TotalBalance - amount;
                                                                    }
                                                                    run=false;
                                                                }
                                                                else{
                                                                    System.out.println("INVALID OTP ENTERED.");
                                                                    System.out.println("TRANSACTION FAILED.");
                                                                }
                                                            }
                                                            else {
                                                                System.out.println("TRANSACTION CANCELED.");
                                                                run = false;
                                                            }
                                                        }
                                                        else if (response==2){
                                                            System.out.println("RESENDING OTP.....");
                                                        }
                                                        else {
                                                            System.out.println("TRANSACTION CANCELED.");
                                                            run=false;
                                                        }
                                                    }
                                                }
                                                else{
                                                    // Fill for < 10000
                                                    if(Storage.bankName[i]!=1){
                                                        Storage.transactionCount[i]++;
                                                        notesIssued(amount);
                                                        System.out.println("Remaining balance: Rs."+remainingAmount+" - Rs.10(service tax) = Rs."+(remainingAmount-10));
                                                        Storage.balance[i] = remainingAmount-10;
                                                        System.out.println("THANK YOU FOR USING ABC BANK.");
                                                        System.out.println("TRANSACTION COMPLETE.");
                                                        TotalBalance = TotalBalance - amount;
                                                    }
                                                    else {
                                                        Storage.transactionCount[i]++;
                                                        notesIssued(amount);
                                                        System.out.println("Remaining balance: Rs."+remainingAmount);
                                                        Storage.balance[i] = remainingAmount;
                                                        System.out.println("TRANSACTION COMPLETE.");
                                                        TotalBalance = TotalBalance - amount;
                                                    }
                                                }
                                            }
                                            else{
                                                System.out.println("TWO TRANSACTIONS PER RUN ONLY.");
                                                System.out.println("TRANSACTION FAILED.");
                                            }
                                        }
                                        else {
                                            System.out.println("Enter in denominations of 500");
                                            System.out.println("TRANSACTION FAILED");
                                        }
                                    } else {
                                        System.out.println("INSUFFICIENT BALANCE.");
                                        System.out.println("TRANSACTION FAILED.");
                                    }
                                    System.out.println();
                                } else {
                                    System.out.println("INSUFFICIENT CASH IN ATM, PLEASE COME AGAIN SOME OTHER TIME.");
                                }
                            }
                            else {
                                System.out.println("EXCEEDING TRANSACTION LIMIT(<= 40000)");
                                System.out.println();
                            }
                        }
                        else {
                            System.out.println("INVALID ACCOUNT TYPE.");
                            System.out.println();
                        }
                    }
                }
            }
            else
                System.out.println("THANK YOU FOR OPTING ABC BANK.");
        }
    }
    public void balanceINQ(){                                      // Operations of Balance enquiry.
        if(entryStatements()==1){
            System.out.println("1. CREDIT");
            System.out.println("2. SAVINGS");
            int typeOfAccount = input.nextInt();
            for (int i = 0; i < 100; i++) {
                if (Storage.pinNumber[i]==(pin)) {
                    if (Storage.accountType[i] == typeOfAccount) {
                        System.out.println("AVAILABLE BALANCE: " + Storage.balance[i]);
                    }
                    else{
                        System.out.println("INVALID ACCOUNT TYPE.");
                        System.out.println();
                    }
                    break;
                }
            }
        }
    }
    public void transfer(){                                           // Money Transfer Operations
        if(entryStatements()==1){
            System.out.println("1. CREDIT");
            System.out.println("2. SAVINGS");
            int typeOfAccount = input.nextInt();
            for (int i = 0; i < 100; i++) {
                if (Storage.pinNumber[i]==(pin)) {
                    if (Storage.accountType[i] == typeOfAccount) {
                        System.out.println("ENTER ACCOUNT NUMBER TO TRANSFER");
                        int accountNumber1 = input.nextInt();
                        System.out.println("RE-ENTER ACCOUNT NUMBER TO TRANSFER");
                        int accountNumber2 = input.nextInt();
                        if (accountNumber1 == accountNumber2) {
                            for(int j=0; j<100; j++){
                                if(Storage.accountNumber[j]==accountNumber1){
                                    if(Storage.accountType[i]==Storage.accountType[j]){
                                        System.out.println("ENTER IFSC CODE");
                                        String ifscCodeEntered = input.next();
                                        if(ifscCodeEntered.equals(Storage.ifsc[j])){
                                            if(Storage.bankName[j]==5){
                                                System.out.println("NOTE: INTERNATIONAL TRANSFER, CHARGES INVOLVED");
                                                System.out.println("$1 = Rs.75");
                                                System.out.print("ENTER AMOUNT TO TRANSFER($): ");
                                                int inDollars = input.nextInt();
                                                double transferAmount = inDollars*75;
                                                double serviceTax = (inDollars*0.0075)*75;
                                                transferAmount = transferAmount + serviceTax;
                                                double isSufficientBalance = Storage.balance[i]-transferAmount;
                                                if(isSufficientBalance>=0){
                                                    Storage.balance[j]=Storage.balance[j]+transferAmount;
                                                    Storage.balance[i]=Storage.balance[i]-transferAmount;
                                                    System.out.println("INTERNATIONAL SERVICE CHARGES: Rs."+serviceTax);
                                                    System.out.println("TRANSFER SUCCESSFUL.");
                                                }
                                                else {
                                                    System.out.println("BALANCE INSUFFICIENT.");
                                                    System.out.println("TRANSFER FAILED.");
                                                }
                                            }
                                            else{
                                                System.out.print("ENTER AMOUNT TO TRANSFER: ");
                                                int transferAmount = input.nextInt();
                                                double isSufficientBalance = Storage.balance[i]-transferAmount;
                                                if(isSufficientBalance>=0){
                                                    Storage.balance[j]=Storage.balance[j]+transferAmount;
                                                    Storage.balance[i]=Storage.balance[i]-transferAmount;
                                                    System.out.println("TRANSFER SUCCESSFUL");
                                                }
                                                else {
                                                    System.out.println("BALANCE INSUFFICIENT.");
                                                    System.out.println("TRANSFER FAILED.");
                                                }
                                            }
                                        }
                                        else{
                                            System.out.println("INVALID IFSC CODE.");
                                            System.out.println("TRANSFER FAILED.");
                                        }
                                    }
                                    else {
                                        System.out.println("RECEIVER MUST HAVE SAME ACCOUNT TYPE");
                                        System.out.println();
                                        break;
                                    }
                                }
                            }
                        }
                        else {
                            System.out.println("PLEASE ENTER SAME ACCOUNT NUMBER");
                            System.out.println();
                            break;
                        }
                    }
                    else{
                        System.out.println("INVALID ACCOUNT TYPE CHOSEN");
                        System.out.println();
                    }
                    break;
                }
            }
        }
    }
    public void pinGeneration(){                                                // Pin Generation Options.
        System.out.print("ENTER ACCOUNT NUMBER: ");
        int accountNumber = input.nextInt();
        for(int i=0; i<100; i++){
            if(accountNumber==Storage.accountNumber[i]){
                if(Storage.pinNumber[i]==0){
                    System.out.println("Dear Customer, ABC Bank appreciates, you for the Green Initiative. PIN shall be delivered to your registered mobile number.");
                    System.out.println("1. CONFIRM");
                    System.out.println("2. CANCEL");
                    int optionOpted = input.nextInt();
                    if (optionOpted==1){
                        int randomPIN = (int)Math.floor(Math.random()*10000);
                        Storage.pinNumber[i]=(randomPIN);
                        System.out.println();
                        System.out.println("PIN generation is successful");
                        System.out.println("You will receive you PIN shortly through registered mobile number.");
                        System.out.println();
                        System.out.println("Please change your PIN without which you will not be allowed to do any transaction.");
                        System.out.println();
                        System.out.println("/**** "+ Storage.customerNames[i] +"'s MOBILE INTERFACE ****/");
                        System.out.println("Mobile number: "+Storage.phoneNumber[i]);
                        System.out.println("ABC Banks PIN Changed to "+Storage.pinNumber[i]+" for Account Number "+Storage.accountNumber[i]+ ".");
                        System.out.println("Message sent at: "+db.currentDateTime());
                        System.out.println();
                    }
                    else{
                        System.out.println("THANK YOU FOR OPTING ABC BANK.");         // check its functioning
                        break;
                    }
                }
                else if(Storage.accountNumber[i]==accountNumber){
                    System.out.println("Dear Customer, Would you like to change your PIN? PIN shall be delivered to your registered mobile number.");
                    System.out.println("1. CONFIRM");
                    System.out.println("2. CANCEL");
                    int optionOpted = input.nextInt();
                    if (optionOpted==1){
                        System.out.print("Enter new PIN: ");
                        int PINchanged = input.nextInt();
                        System.out.print("Re-enter new PIN: ");
                        int PINchanged1 = input.nextInt();
                        if(PINchanged==(PINchanged1)){
                            Storage.pinNumber[i]=PINchanged;
                            System.out.println();
                            System.out.println("PIN generation is successful");
                            System.out.println("You will receive you PIN shortly through registered mobile number.");
                            System.out.println();
                            System.out.println("/**** "+ Storage.customerNames[i] +"'s MOBILE INTERFACE ****/");
                            System.out.println("Mobile number: "+Storage.phoneNumber[i]);
                            System.out.println("ABC Banks PIN Changed to "+Storage.pinNumber[i]+" for Account Number "+Storage.accountNumber[i]+ ".");
                            System.out.println("Message sent at: "+db.currentDateTime());
                            System.out.println();
                        }
                    }
                }
            }
        }
    }
    private int entryStatements(){                               // Covers all common interface statements.
        while (true){
            System.out.println("ENTER ANY NUMBER BETWEEN 10 AND 99");
            System.out.println("For eg. \"25\"");
            int checkNumber = input.nextInt();
            if(checkNumber>=10&&checkNumber<=99){
                break;
            }
            else {
                System.out.println();
                System.out.println("PLEASE ENSURE THE NUMBER IS FROM 10 TO 99");
                System.out.println();
            }
        }
        int getUserConformation;
        while (true){
            System.out.println("IS THE NUMBER DISPLAYED ON THE SCREEN ?");
            System.out.println("1. YES");
            System.out.println("2. NO");
            getUserConformation = input.nextInt();
            if(getUserConformation==1||getUserConformation==2)
                break;
            else{
                System.out.println();
                System.out.println("PLEASE SELECT EITHER 1 OR 2");
                System.out.println();
            }
        }
        if (getUserConformation == 1) {
            System.out.println("ABC Bank");
            System.out.print("Please enter your PIN: ");
            pin = input.nextInt();
            return db.validate(Integer.toString(pin));
        }
        else{
            System.out.println("ATM MACHINE IS NOT WORKING, PLEASE COME AGAIN LATER.");
            System.out.println();
        }
        return 0;
    }

    private void notesIssued(int amount){                                  // Gives Denominations.
        int notes2000 = amount/2000;
        int remaining = 10500%2000;
        int notes500 = remaining/500;
        System.out.println("ISSUED AMOUNT: "+"("+notes2000+")"+" * Rs.2000 + "+"("+notes500+")"+" * Rs.500 = "+"Rs."+amount);
    }
}