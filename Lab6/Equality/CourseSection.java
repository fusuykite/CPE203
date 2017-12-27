import java.time.LocalTime;
import java.util.Optional;

class CourseSection
{
    private final String prefix;
    private final String number;
    private final int enrollment;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public CourseSection(final String prefix, final String number,
                         final int enrollment, final LocalTime startTime, final LocalTime endTime)
    {
        this.prefix = prefix;
        this.number = number;
        this.enrollment = enrollment;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // additional likely methods not defined since they are not needed for testing
    public boolean equals(Object other) {
        if (other != null) {
            if (getClass() == other.getClass()) {
                boolean prefixEq = (prefix.equals(((CourseSection) other).prefix));
                boolean numberEq = (number.equals(((CourseSection) other).number));
                boolean enrollmentEq = (enrollment == ((CourseSection) other).enrollment);
                boolean startTimeEq = (startTime.equals(((CourseSection) other).startTime));
                boolean endTimeEq = (endTime.equals(((CourseSection) other).endTime));
                return prefixEq && numberEq && enrollmentEq && startTimeEq && endTimeEq;
            }
        }
        return false;
    }

    public int hashCode() {
        int prefixHash = 0;
        int numberHash = 0;
        int enrollHash = 0;
        int startHash = 0;
        int endHash = 0;
        if (prefix != null) {
            prefixHash = (prefix.hashCode() * 2);
        }
        if (number != null) {
            numberHash = (number.hashCode() * 1);
        }
        if ((Integer)enrollment != null) {
            enrollHash = (((Integer)enrollment).hashCode() * 3);
        }
        if (startTime != null) {
            startHash = (startTime.hashCode() * 4);
        }
        if (endTime != null) {
            endHash = (endTime.hashCode() * 5);
        }
        return prefixHash + numberHash + enrollHash + startHash + endHash;
    }
}
