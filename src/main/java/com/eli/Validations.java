package com.eli;

public class Validations {
    public static Integer validateNumber(String response){
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
