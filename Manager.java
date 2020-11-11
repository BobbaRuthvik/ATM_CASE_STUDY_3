package com.com_spidey;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Since it's manager class made sure all methods are private.

class Manager{
    Storage str = new Storage();
    protected final String password = "abc123";                // a PROTECTED password.
    Scanner input = new Scanner(System.in);

    FileWriter customerData;                                   // FILE HANDLING USED.

    {
        try {
            customerData = new FileWriter("CustomerData.txt");          // file name CustomerData.txt
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {                                                                   // Only encrypted pin is displayed
            customerData.write("S.no. Customer name    Account number  Account type    Balance     Mobile Number  Encrypted Pin");
            customerData.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void system(){                                                            // Acts as interface of manager system.
        System.out.println("/**** MANAGER'S COMPUTER ****/");
        int i ;
        for(i=0; i<5; i++){
            System.out.println("Username: ABC Bank Manager");
            System.out.print("Password: ");
            String passwordCheck = input.next();
            if(passwordCheck.equals(password)){
                encryption();
                System.out.println("Enter 1 to add accounts, 2 to get customer details and 3 for emergency pin enquiry");
                int managerOptedOptions = input.nextInt();
                if(managerOptedOptions==1)
                    addAccount();
                else if(managerOptedOptions==2)
                    customerAccountDetails();
                else if(managerOptedOptions==3){
                    System.out.print("Enter account number: ");
                    int accountNumber = input.nextInt();
                    for(int j=0; j<100; j++){
                        if(Storage.accountNumber[j]==accountNumber){
                            System.out.println("User PIN: "+str.decryptPin(accountNumber));
                        }
                    }
                }
                else
                    System.out.println("Invalid option chosen.");
                break;
            }
            else
                System.out.println("Try again, " + (4-i) + " chances left.");
        }
        if(i==5)
            System.out.println("Sorry, please try again later.");
    }
    private void addAccount(){                                          // To add new Customers
        int n = Storage.customerCount;
        System.out.print("Enter customer's full name: ");
        input.nextLine();
        String name = input.nextLine();
        Storage.customerNames[n]=name;
        System.out.print("Enter customer's account number: ");
        int accountNumber = input.nextInt();
        Storage.accountNumber[n]=accountNumber;
        System.out.println("Enter account type: ");
        System.out.println("1. Current");
        System.out.println("2. Savings");
        int accountType = input.nextInt();
        Storage.accountType[n]=accountType;
        System.out.print("Enter balance: ");
        int balance = input.nextInt();
        Storage.balance[n]=balance;
        System.out.print("Enter mobile number: ");
        String phoneNumber = input.next();
        Storage.phoneNumber[n] = phoneNumber;
        Storage.bankName[n]=1;
        Storage.customerCount++;
    }
    private void customerAccountDetails(){                                  // To get current ATM status.
        int n = Storage.customerCount;
        System.out.println();
        System.out.println("Saved in file named CustomerData.txt");
        System.out.println();
        for(int i=0; i<n; i++){
            String r = String.valueOf(Storage.accountNumber[i]);
            String s = String.valueOf(Storage.balance[i]);
            int t = Storage.accountType[i];
            String tp;
            if(t==1)
                tp = "Current";
            else
                tp = "Savings";
            try {
                customerData.append(String.valueOf(i+1));
                customerData.append("     ");
                customerData.append(Storage.customerNames[i]);
                customerData.append("     ");
                customerData.append(r);
                customerData.append("           ");
                customerData.append(tp);
                customerData.append("         ");
                customerData.append(s);
                customerData.append("     ");
                customerData.append(Storage.phoneNumber[i]);
                customerData.append("     ");
                customerData.append(Storage.encrypt[i]);
                customerData.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            customerData.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void encryption(){                                           // Encrypts PIN.
        for(int i=0; i<Storage.customerCount; i++)
            str.encryptPin(Storage.accountNumber[i]);
    }
}