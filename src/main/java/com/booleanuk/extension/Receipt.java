package com.booleanuk.extension;

import com.booleanuk.core.Basket;
import com.booleanuk.core.Item;
import com.booleanuk.core.Order;

import java.util.HashMap;
// The receipt class can generate receipts for the items bought, and display the discount if discountShow=true
public class Receipt {

    Receipt(){

    }

    public void printReceipt(Order order, boolean discountShow){

        Basket basket=new Basket(26);
        double[] discountPrices=new double[4];
        basket.add(order);
        if(discountShow){
            Discount discount= new Discount();
            discountPrices=discount.discPrice(order);
            order.setTotal(order.getTotal()- discountPrices[3]);
            basket.adjustTotal(order.getTotal());
        }

        System.out.println("~~~ Bob's Bagels ~~~");
        System.out.println("");
        System.out.println("2021-03-16 21:38:44");
        System.out.println("");
        System.out.println("----------------------------");
        System.out.println("");

        HashMap<String, Integer> number=basket.getItems();
        HashMap<String, Double> prices=basket.getPrices();
        int L=6;
        for (String item: number.keySet()){
            String str=item+"  "+number.get(item)+ " £"+String.format("%.2f",prices.get(item)*number.get(item));
            System.out.println(item+"  "+number.get(item)+ " £"+String.format("%.2f",prices.get(item)*number.get(item)));
        }

        if(discountShow){
            double fullDiscount=0;
            if (discountPrices[0]>0) {
                System.out.println("Discount due to buying flavored Bagels in bulk: -£"+String.format("%.2f",discountPrices[0]));
                fullDiscount+=discountPrices[0];

            }
            if(discountPrices[1]>0){
                System.out.println("Discount due to buying Plain Bagels in bulk: -£"+String.format("%.2f",discountPrices[1]));
                fullDiscount+=discountPrices[1];
            }
            if(discountPrices[2]>0){
                System.out.println("Discount due to buying Bagel/Coffee in bulk: -£"+String.format("%.2f",discountPrices[2]));
                fullDiscount+=discountPrices[2];
            }

            System.out.println("You saved a total of: £"+String.format("%.2f",fullDiscount)+" on this trip.");

        }

        System.out.println("");
        System.out.println("----------------------------");
        System.out.println("Total"+ " £"+ basket.getTotal());
        System.out.println("");
        System.out.println("Thank you for your order!");


    }
}
