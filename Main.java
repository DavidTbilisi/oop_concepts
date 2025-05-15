import java.util.*;

enum Status {
    IN_SERVICE,
    IN_MAINTENANCE,
    GROUNDED
}

enum ClassType {
    FIRST_CLASS,
    BUSINESS_CLASS,
    TOURIST_CLASS
}

class Seat {
    private String seatNumber;
    private ClassType classType;
    private boolean isAvailable;

    public Seat(String seatNumber, ClassType classType, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.classType = classType;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}

class AirplaneModel {
    private String registrationNumber;
    private Status status;
    private String modelName;
    private List<Seat> seats = new ArrayList<>();

    public AirplaneModel(String registrationNumber, Status status, String modelName) {
        this.registrationNumber = registrationNumber;
        this.status = status;
        this.modelName = modelName;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public List<Seat> getSeats() {
        return seats;
    }
}

class FlightNumber {
    private String departureAirport;
    private String destinationAirport;
    private String departureTime;
    private String airline;
    private AirplaneModel airplaneModel;

    public FlightNumber(String departureAirport, String destinationAirport, String departureTime, String airline, AirplaneModel airplaneModel) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.airline = airline;
        this.airplaneModel = airplaneModel;
    }
}

class Flight {
    private FlightNumber flightNumber;
    private Date departureDate;
    private String boardingTime;
    private String gate;

    public Flight(FlightNumber flightNumber, Date departureDate, String boardingTime, String gate) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.boardingTime = boardingTime;
        this.gate = gate;
    }
}

class FrequentFlierCard {
    private String ffcNumber;
    private int milesFlown;

    public FrequentFlierCard(String ffcNumber) {
        this.ffcNumber = ffcNumber;
        this.milesFlown = 0;
    }

    public void addMiles(int miles) {
        this.milesFlown += miles;
    }
}

class Passenger {
    private String name;
    private Date birthdate;
    private List<FrequentFlierCard> cards = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();

    public Passenger(String name, Date birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public void addFrequentFlierCard(FrequentFlierCard card) {
        cards.add(card);
    }

    public void buyTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}

class Luggage {
    private String luggageNumber;
    private float weight;

    public Luggage(String luggageNumber, float weight) {
        this.luggageNumber = luggageNumber;
        this.weight = weight;
    }
}

class Coupon {
    private Flight flight;
    private Seat seat;
    private ClassType classType;
    private List<Luggage> luggageList = new ArrayList<>();

    public Coupon(Flight flight, Seat seat, ClassType classType) {
        this.flight = flight;
        this.seat = seat;
        this.classType = classType;
    }

    public void addLuggage(Luggage luggage) {
        luggageList.add(luggage);
    }
}

class Ticket {
    private List<Coupon> coupons = new ArrayList<>();

    public void addCoupon(Coupon coupon) {
        coupons.add(coupon);
    }
}




public class Main {
    public static void main(String[] args) {
        AirplaneModel model = new AirplaneModel("ABC123", Status.IN_SERVICE, "Boeing 777");
        model.addSeat(new Seat("12A", ClassType.FIRST_CLASS, true));
        model.addSeat(new Seat("12B", ClassType.BUSINESS_CLASS, true));

        FlightNumber fn = new FlightNumber("JFK", "LAX", "09:00", "Delta Airlines", model);
        Flight flight = new Flight(fn, new Date(), "08:30", "G5");

        Passenger passenger = new Passenger("John Doe", new GregorianCalendar(1990, Calendar.JANUARY, 1).getTime());
        FrequentFlierCard card = new FrequentFlierCard("FFC123456");
        passenger.addFrequentFlierCard(card);

        Ticket ticket = new Ticket();
        Coupon coupon = new Coupon(flight, model.getSeats().get(0), ClassType.FIRST_CLASS);
        ticket.addCoupon(coupon);
        passenger.buyTicket(ticket);

        Luggage luggage = new Luggage("LUG001", 15.5f);
        coupon.addLuggage(luggage);

        System.out.println("Passenger " + passenger + " booked flight with ticket and luggage!");
    }
}
