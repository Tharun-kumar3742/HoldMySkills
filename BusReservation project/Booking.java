import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Booking {
    String passangerName;
    int busno;
    Date date;
    Booking()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Name of the passenger:");
        passangerName = sc.next();
        System.out.println("Enter Bus no:");
        busno = sc.nextInt();
        System.out.println("Enter Date: dd-mm-yyyy");
        String dateInput = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            date = dateFormat.parse(dateInput);
        } catch (ParseException ex) {
        }
    }
    public boolean isAvailable(ArrayList<Booking>bookings,ArrayList<Bus>buses)
    {
        int capacity=0;
        for(Bus bus:buses)
        {
            if(bus.getBusNo()==busno)
            {
                capacity = bus.getCapacity();
            }
        }
        int booked=0;
        for(Booking b:bookings)
        {
            if(b.busno==busno && b.date.equals(date))
            {
                booked++;
            }
        }
        return booked<capacity?true:false;
    }
}
