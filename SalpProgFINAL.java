package salpprogfinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalpProgFINAL {
      static Scanner inp = new Scanner(System.in);
      static ArrayList<String> Product = new ArrayList<String>();  
      static ArrayList<Integer> Price = new ArrayList<Integer>();  
      static HashMap<String, Integer> cart = new HashMap<>();
      static File vProd = new File("file\\Inventory.txt");
      static File pricelist = new File("file\\Price.txt");
      static File list;
      static BufferedReader br= null;
      static String line;
      static int total;
      static String mode;
      
      static void waittoEnter(){
          System.out.print("Press Enter to continue...");
          String input = inp.nextLine(); // Wait for input
      }
      
      static void buyprod (int product) throws IOException{
          String productName = Product.get(product);
          int productPrice = Price.get(product);
          System.out.print("Qty: ");
          int qty = inp.nextInt();
          try{
          
          int sum = qty * Price.get(product);
          
          /*list = new File("file\\List.txt");
          FileWriter write = new FileWriter(list,true);
          BufferedWriter bf = new BufferedWriter(write);
          
          bf.write(Product.get(product) +" || "+ qty + " || P" + sum + "\n");
         
          bf.close();
          */
          
          if(cart.containsKey(productName)){
              int currentQty = cart.get(productName);
              cart.put(productName, currentQty + qty);
          }else {
              cart.put(productName, qty);      
          }
          
          
          }catch (Exception e){
                  System.out.println(e);
                  }
          
      }
      
      static void viewProd(){
          if(cart.isEmpty()){
              System.out.println("There is no item yet!");
          }
          else{
            System.out.println("\nMy Cart:");
            System.out.println("Product || Qty || Price");
            int total = 0;
            
            for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String productName = entry.getKey();
            int qty = entry.getValue();
            int productIndex = Product.indexOf(productName);

            if (productIndex != -1) { // Ensure the product exists in the list
                int price = Price.get(productIndex) * qty;
                System.out.println(productName + " || " + qty + " || P" + price);
                total += price;
            } else {
                System.out.println(productName + " not found in product list!");
            }
        }
            
            System.out.println("Total: P" + total);
            
            System.out.println("Place order now? Y/N");
        String confirm = inp.next().toUpperCase();

        if (confirm.equals("Y")) {
            exportOrder(); // Call the method to export the order to a file
            cart.clear(); // Clear the cart after placing the order
            System.out.println("Thank you for your purchase! Have a great day!");
        } else {
            System.out.println("Order canceled.");
        }
          }
      }
      
      static void removeProduct() throws IOException {
        System.out.println("\nList of Products:");

      for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String productName = entry.getKey();
            int qty = entry.getValue();
            int productIndex = Product.indexOf(productName);
            
            
            if (productIndex != -1) { // Ensure the product exists in the list
                int price = Price.get(productIndex) * qty;
                System.out.println(productName + " || " + qty + " || P" + price);
                total += price;
            } else {
                System.out.println(productName + " not found in product list!");
            }
            
            
        }
      
      System.out.println("Choose the product to remove from your cart: "); 
      inp.nextLine();
      String productToRemove = inp.nextLine().trim();
      System.out.print("Are you Sure? Type \"Y\" to remove or Press enter to Cancel.\n");
      String opt = inp.nextLine().toUpperCase();
                
      switch(opt){
          case "Y":
    if (cart.containsKey(productToRemove)) {
        cart.remove(productToRemove);
        System.out.println(productToRemove + " has been removed from your cart.");
    } else {
        System.out.println("Invalid selection or product not in the cart!");
    }
    break;
    
          default:
              System.out.println("You have cancel the process. ");
              waittoEnter();
          break;
    
            }
      }
      
    static void exportOrder() {
    if (cart.isEmpty()) {
        System.out.println("\nCart is empty! Nothing to export.");
        return;
    }

    File orderFile = new File("file\\OrderSummary.txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFile))) {
        writer.write("Order Summary:\n");
        writer.write("Product || Qty || Price\n");
        int total = 0;

        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String productName = entry.getKey();
            int qty = entry.getValue();
            int productIndex = Product.indexOf(productName);

            if (productIndex != -1) { // Ensure product exists in inventory
                int price = Price.get(productIndex) * qty;
                writer.write(productName + " || " + qty + " || P" + price + "\n");
                total += price;
            }
        }
        writer.write("Total: P" + total + "\n");
        writer.write("Thank you for your purchase!");

        System.out.println("\nOrder successfully exported to OrderSummary.txt!");
        mode = "5";
    } catch (IOException e) {
        System.out.println("Error exporting order: " + e.getMessage());
    }
}
      
    public static void main(String[] args) {
        
        try{
      
      FileReader read = new FileReader(vProd);
          br = new BufferedReader(read);
 
          while( (line = br.readLine()) != null){
              Product.add(line);
          }
          br.close();
                 read = new FileReader(pricelist);
          br = new BufferedReader(read);
 
          while( (line = br.readLine()) != null){
              Price.add(Integer.parseInt(line));
          }
      
    }catch(Exception e){
    System.out.println(e);
}
      boolean istrue = true;
      while(istrue){
      try{ 
         mode = null;
      do{
      
         System.out.print("Welcome to Sari-Sari Store: \n"
                        + "1. view product \n"
                        + "2. add Product  \n"
                        + "3. View Cart  \n"
                        + "4. Remove product \n"
                        + "5. Exit \n"
                        + "Choose: ");
          mode = inp.next();
         switch(mode) {
             case "1":
                 System.out.println("List:");
             for (int i = 0; i < Product.size(); i++){
             System.out.println(i + ". " + Product.get(i) + "  - P " + Price.get(i));
             }
             waittoEnter();
             break;
             
             case "2":
                 System.out.println("List:");
             for (int i = 0; i < Product.size(); i++){
             System.out.println(i + ". " + Product.get(i) + "  - P " + Price.get(i));
             }
             System.out.print("Choose product to buy: ");
             int prod = inp.nextInt();
              if (prod >= 0 && prod < Product.size()) {
                            buyprod(prod);
                        } else {
                            System.out.println("Invalid product selection.");
                        }
                        break;
             
             case "3":
           /*     FileReader read = new FileReader(list);
                br = new BufferedReader(read);
                if((line = br.readLine()) != null ){
                    System.out.println("My Transaction: ");
                    System.out.println("Product || Qty || Price");
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
           */
                 viewProd();
             break;
                 
             case "4":
                 removeProduct();
             break;
             
             
             case "5":
                 System.out.println("Confirmation? Y/N");
                    String conf = inp.next().toUpperCase();
                    switch(conf){
                        case "Y":
                        mode = "5";
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
      
      } while(mode != "5");
      istrue = false;
      } catch(Exception e){
          System.out.println(e);
          continue;
      }
      }
    }
    
}
