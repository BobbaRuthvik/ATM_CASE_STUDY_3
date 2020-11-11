package com.com_spidey;

class Storage {
    private final int KEY = (int) Math.floor(Math.random()*100);
    /*
    Bank types:
    1. ABC
    2. SBI
    3. ICIC
    4. HDFC
    5. BANK OF AMERICA
     */
    static int customerCount;                                // Data mostly stored as static to get access in other classes without re-setting each time.
    static String[] customerNames = new String[100];
    static int[] accountNumber = new int[100];
    static int[] pinNumber = new int[100];
    static int[] accountType = new int[100];
    static double[] balance = new double[100];
    static int[] bankName = new int[100];
    static int[] transactionCount = new int[100];
    static String[] ifsc = new String[100];
    static String[] phoneNumber = new String[100];
    static String[] encrypt = new String[100];

    public void dataTransfer(){                                     // Beginning customer details.
        customerNames[0] = "BILLY STUMPS";
        accountNumber[0] = 12345;
        pinNumber[0] = 5432;
        accountType[0] = 2;
        balance[0] = 50000;
        bankName[0] = 1;
        ifsc[0] = "ABCIN502205";
        phoneNumber[0] = "7923639264";


        customerNames[1] = "BOOM McCONDOR";
        accountNumber[1] = 67890;
        pinNumber[1] = 1023;
        accountType[1] = 2;
        balance[1] = 40000;
        bankName[1] = 2;
        ifsc[1] = "SBIIN523145";
        phoneNumber[1] = "8900622945";

        customerNames[2] = "BOBBA RUTHVIK";
        accountNumber[2] = 11111;
        pinNumber[2] = 2222;
        accountType[2] = 1;
        balance[2] = 80000;
        bankName[2] = 3;
        ifsc[2] = "ICIIN545756";
        phoneNumber[2] = "9167235478";

        customerNames[3] = "ROCK CALLAHAN";
        accountNumber[3] = 10001;
        pinNumber[3] = 1001;
        accountType[3] = 2;
        balance[3] = 60000;
        bankName[3] = 5;
        ifsc[3] = "BOAUS102201";
        phoneNumber[3] = "6272340134";

        customerCount = 4;
    }

    public void encryptPin(int accountNumber){                           // Encrypts all PINs at a time, to give only encrypted data to Manager
        String encryptedPin = "";
        for(int i=0; i<100; i++){
            if(Storage.accountNumber[i]==accountNumber){
                String pin = Integer.toString(Storage.pinNumber[i]);
                char[] ch = pin.toCharArray();
                for(int j=0; j<pin.length(); j++){
                    ch[j] = (char) (ch[j] + KEY);
                    encryptedPin += ch[j];
                }
                Storage.encrypt[i] = encryptedPin;
            }
        }
    }

    public int decryptPin(int accountNumber){                                    // Decrypts PIN based on account number.
        String pin = "";
        int decryptedPin = 0;
        for(int i=0; i<100; i++){
            if(Storage.accountNumber[i]==accountNumber){
                String encryptedPin = Storage.encrypt[i];
                char[] ch = encryptedPin.toCharArray();
                for(int j=0; j<encryptedPin.length(); j++){
                    ch[j] = (char) (ch[j] - KEY);
                    pin += ch[j];
                }
                decryptedPin = Integer.parseInt(pin);
            }
        }
        return decryptedPin;
    }
}
