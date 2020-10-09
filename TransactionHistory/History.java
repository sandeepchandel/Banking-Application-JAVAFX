/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TransactionHistory;

public class History {
     private String name;
     private String amount;
     private String generic;
     private String date;
     private String time;

    public History(String name, String amount, String generic, String date, String time) {
        this.name = name;
        this.amount = amount;
        this.generic = generic;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getGeneric() {
        return generic;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
     
}
