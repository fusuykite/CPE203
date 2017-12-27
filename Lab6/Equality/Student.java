import java.util.List;
import java.util.Objects;
class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
                  final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

 
    public boolean equals(Object other) {

      if (other != null) {
         if (getClass() == other.getClass()) {
            boolean surnameEq = Objects.equals(this.surname, ((Student)other).surname);
            boolean givenNameEq = Objects.equals(this.givenName, ((Student)other).givenName);
            boolean ageEq = Objects.equals(this.age, ((Student)other).age);
            boolean currentCoursesEq = Objects.equals(this.currentCourses, ((Student)other).currentCourses);
            return surnameEq && givenNameEq && ageEq && currentCoursesEq;
         }
      }
      return false;
   }


   public int hashCode() {
      return (Objects.hash(surname, givenName, age, currentCourses));
   }
}
