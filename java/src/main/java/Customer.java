import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String _name;
    private List<Rental> _rentals = new ArrayList<Rental>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        String result = "";

        // header
        result += "Rental Record for " + getName() + "\n";

        // middle
        result += getStringAsMiddle();

        // add footer lines
        result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
        result += "You earned " + String.valueOf(getTotalPoints(_rentals)) + " frequent renter points";

        return result;
    }

    private double getTotalAmount() {
        double totalAmount = 0;
        for (Rental aRental : _rentals) {
            totalAmount += amountFor(aRental.getMovie(), aRental.getDaysRented());
        }
        return totalAmount;
    }



    private String getStringAsMiddle() {
        String result = "";
        for (Rental aRental : _rentals) {
            result += "\t" + aRental.getMovie().getTitle() + "\t" + String.valueOf(amountFor(aRental.getMovie(), aRental.getDaysRented())) + "\n";
        }
        return result;
    }



    private double amountFor(Movie target, int _daysRented) {
        return target.getCharge(_daysRented);
    }

    public int getTotalPoints(List<Rental> rentalList) {
        int frequentRenterPoints = 0;
        for (Rental aRental : rentalList) {
            frequentRenterPoints += aRental.getMovie().rentalPointsFor(aRental.getDaysRented());
        }
        return frequentRenterPoints;
    }
}