import java.util.Comparator;

public class fNameLNameComparator implements Comparator<Participant> {

    @Override
    public int compare(Participant o1, Participant o2) {
        int val = o1.getFirstName().compareTo(o2.getFirstName());
        if (val == 0) {
            return o1.getLastName().compareTo(o2.getLastName());
        }
        return val;
    }
}
