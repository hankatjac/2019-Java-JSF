package mybeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

// @ApplicationScoped will create this bean at the beginning of lunching this application

@ManagedBean
@ApplicationScoped
public class StudentDataUtil {

	private List<Student> studentsArrayList;

	public StudentDataUtil() {
		loadSampleData();
	}

	public List<Student> getStudentsArrayList() {
		return studentsArrayList;
	}

	public void loadSampleData() {
		studentsArrayList = new ArrayList<>();

		studentsArrayList.add(new Student("Masoud", "Bozorgi", "masoud@google.com"));
		studentsArrayList.add(new Student("John", "Doe", "john@google.com"));
		studentsArrayList.add(new Student("Nihan", "Ni", "nihan@google.com"));
	}
}
