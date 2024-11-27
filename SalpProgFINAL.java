package salpprogfinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SalpProgFINAL {
      static Scanner inp = new Scanner(System.in);
      static ArrayList<String> Product = new ArrayList<String>();  
      static ArrayList<Integer> Price = new ArrayList<Integer>();  
      static File vProd = new File("file\\Inventory.txt");
      static File pricelist = new File("file\\Price.txt");
      static File list;
      static BufferedReader br= null;
      static String line;
      static int total;
      
      
      static void buyprod (int product) throws IOException{
          System.out.print("Qty: ");
          int qty = inp.nextInt();
          try{
          FileReader read = new FileReader(pricelist);
          br = new BufferedReader(read);
 
          while( (line = br.readLine()) != null){
              Price.add(Integer.parseInt(line));
          }
          int sum = qty * Price.get(product);
          
          list = new File("file\\List.txt");
          FileWriter write = new FileWriter(list,true);
          BufferedWriter bf = new BufferedWriter(write);
          
          bf.write(Product.get(product) + " " + sum + "\n");
          
          bf.close();
          
          }catch (Exception e){
                  System.out.println(e);
                  }
          
      }
      
    public static void main(String[] args) {
        
        try{
       list = new File("file\\List.txt");
      FileWriter write = new FileWriter(list);
      
      FileReader read = new FileReader(vProd);
          br = new BufferedReader(read);
 
          while( (line = br.readLine()) != null){
              Product.add(line);
          }
          br.close();
      
    }catch(Exception e){
    System.out.println(e);
}
      boolean istrue = true;
      while(istrue){
      try{ 
        String mode = null;
      do{
      
         System.out.print("Welcome to Sari-Sari Store: \n"
                        + "1. View Product  \n"
                        + "2. View Cart  \n"
                        + "3. Exit \n"
                        + "Choose: ");
          mode = inp.next();
         switch(mode) {
             case "1":
                 System.out.println("List:");
             for (int i = 0; i < Product.size(); i++){
             System.out.println(i + ". " + Product.get(i));
             }
             System.out.print("Choose:");
             int prod = inp.nextInt();
             buyprod(prod);
             break;
             
             
             case "2":
                FileReader read = new FileReader(list);
                br = new BufferedReader(read);
                if((line = br.readLine()) != null ){
                    System.out.println("My Transaction: ");  
                    br.close();
                    read = new FileReader(list);
                    br = new BufferedReader(read);
                while ((line = br.readLine()) != null){
                    System.out.println(line);
               }
                System.out.println("Place Order now? Y/N");
                    String conf = inp.next().toUpperCase();
                    switch(conf){
                        case "Y":
                        mode = "3";
                        System.out.println("Thank You for purchasing our product!!");
                        System.out.println("Have a Good Day!!!");
                        break;
                        case "N":
                        break;
                        default:
                        System.out.println("Invalid!! Please try again!");
                        continue;
                    }
                } else{
                    System.out.println("No Item YET!!");
                }
             br.close();
             break;
             
             
             case "3":
                 System.out.println("Confirmation? Y/N");
                    String conf = inp.next().toUpperCase();
                    switch(conf){
                        case "Y":
                        mode = "3";
                        System.out.print("Thank You Have a nice day!!");
                        break;
                        case "N":
                        break;
                        default:
                        System.out.println("Invalid!! Please try again!");
                        continue;
                    }
             break;
             
             default:
             System.out.print("Incorrect options");
             break;
         }
      
      } while(mode != "3");
      istrue = false;
      } catch(Exception e){
          System.out.println(e);
          continue;
      }
      }
    }
    
}
