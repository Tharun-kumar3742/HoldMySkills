import java.util.*;
public class BusDemo {
    public static void main(String[] args) {
       
        //creating an list of data through arraylist
        
        ArrayList<Bus>buses = new ArrayList<>(); 
        ArrayList<Booking>bookings = new ArrayList<>();

        //Adding the info of buses
        buses.add(new Bus(1,true,2));
        buses.add(new Bus(2,false,45));
        buses.add(new Bus(3,true,45));
       
        //displaying details 
        for(Bus b:buses)
        {
            b.DisplayBusInfo();
        }
        
        //want to register
        int userOpt=1;
        Scanner sc = new Scanner(System.in);
        while(userOpt==1)
       {
        System.out.println("Enter 1 to Booking or 2 to Exit");
        userOpt = sc.nextInt();
        if(userOpt==1)
        {
            Booking booking = new Booking();
            if(booking.isAvailable(bookings,buses))
            {
                bookings.add(booking);
                System.out.println("Your Booking is Confirmed");
            }
            else{
                System.out.println("Sorry Bus is full.Try another bus or date.");
            }
        }
        
       } 
    }
    
}
