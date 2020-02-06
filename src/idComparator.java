import java.util.Comparator;

public class idComparator implements Comparator<Participant> {

    @Override
    public int compare(Participant o1, Participant o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
