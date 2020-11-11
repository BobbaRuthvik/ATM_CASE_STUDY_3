package com.com_spidey;

/*
Individual Constraints Achieved:
1. Defined denominations to notes.
2. Set Limit to transactions.
3. Set ATM cash limit.
4. Different Bank card charge processing fee(Only Bank no. 1 is excluded from charges, for more clarity refer Storage.java).
5. In Transfer section added International money transfer including taxes.

Common Constraints Achieved:
1. Encryption and Decryption of password for Manager class using random generated key method.
2. 2 Step verification using OTP for Banking Option.
3. Account to account transfer with IFSC code verification(Made Case Sensitive Deliberately since it's a ATM)
4. A PIN change option included in PIN Generation option.
5. Displays information using FILE HANDLING.
*/

public class Main {

    public static void main(String[] args) {           // Tried to maintain as less data exposure to avoid hackers.
        Screen obj = new Screen();
        obj.run();
    }
}










