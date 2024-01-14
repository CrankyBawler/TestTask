import com.gridnine.testing.Sorting;

public class Main {
    public static void main(String[] args) {
        Sorting sorting = new Sorting();

        System.out.println();
        sorting.findFlightBeforeNow();

        System.out.println();
        sorting.findSegmentsWithArrivalBeforeDeparture();

        System.out.println();
        sorting.findTransferTimeMoreTwoHours();
    }
}