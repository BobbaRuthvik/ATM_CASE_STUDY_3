package com.com_spidey;

import java.util.Scanner;

class Screen{
    Storage st = new Storage();
    Security customer = new Security();
    PrimaryATMOptions op = new Banking();
    Scanner input = new Scanner(System.in);
    public void run(){                                   // Acts as ATM interface.
        st.dataTransfer();
        while (true) {
            System.out.println("Enter 1 if you are a customer, 2 for manager system any other to exit");
            int optedOption = input.nextInt();
            int checkAccountNumber;
            if (optedOption == 1) {
                do {
                    System.out.println();
                    System.out.println("Welcome to ABC ATM services");
                    System.out.print("Please enter your account number: ");
                    int accountNumber = input.nextInt();
                    checkAccountNumber = customer.validate(accountNumber);
                } while (checkAccountNumber == 0);
                int optionChoosenByCustomer = 0;
                if (checkAccountNumber == 1) {
                    boolean run2 = true;
                    while (run2) {
                        System.out.println("PLEASE CHOOSE \"BANKING\" FOR CASH \"WITHDRAWALS\"");
                        System.out.println("1. BANKING");
                        System.out.println("2. BALANCE INQ");
                        System.out.println("3. TRANSFER");
                        System.out.println("4. PIN GENERATION");
                        optionChoosenByCustomer = input.nextInt();
                        if (optionChoosenByCustomer > 0 && optionChoosenByCustomer < 5)
                            run2 = false;
                        else
                            System.out.println("Enter input from 1 to 4");
                    }
                    switch (optionChoosenByCustomer) {
                        case 1:
                            op.banking();
                            break;
                        case 2:
                            op.balanceINQ();
                            break;
                        case 3:
                            op.transfer();
                            break;
                        case 4:
                            op.pinGeneration();
                            break;
                        default:
                            System.out.println("Invalid");
                    }
                }
            }
            else if (optedOption == 2) {
                Manager mn = new Manager();
                mn.system();
            }
            else
                break;
        }
    }
}