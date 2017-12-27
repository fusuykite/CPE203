import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ExampleMap
{
   public static List<String> highEnrollmentStudents(
      Map<String, List<Course>> courseListsByStudentName, int unitThreshold)
   {
      List<String> overEnrolledStudents = new LinkedList<>();

      /*
         Build a list of the names of students currently enrolled
         in a number of units strictly greater than the unitThreshold.
      */
		Set<String> keys = courseListsByStudentName.keySet();
		for (String key : keys) {
			int units = 0;
			List<Course> courses = courseListsByStudentName.get(key);
			for (int i = 0; i < courses.size(); i++) {
				Course course= courses.get(i);
				units += course.getNumUnits();
			}
			if (units > unitThreshold) {
				overEnrolledStudents.add(key);
			}
		}
      return overEnrolledStudents;      
   }
	
}
