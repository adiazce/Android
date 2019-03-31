package com.example.test;

public class FizzBuzz {


    // mensaje para los nùmeros  mùltiplos de 3
    public static final String FIZZ = "FIZZ";
    // mensaje para los nùmeros mùltiplos de 5
    public static final String BUZZ = "BUZZ";


    public String execute(int number){
        String msn = "";

        if(multiplo3(number)){
            msn += FIZZ;
        }
        if(multiplo5(number)){
            msn += BUZZ;
        }
        if(msn.isEmpty()){
            msn = "" + number;
        }
        return msn;


    }

    private boolean multiplo5(int number) {
        return  (number % 5 == 0) ;
    }

    private boolean multiplo3(int number) {
        return  ( number % 3 == 0 );

    }


}
