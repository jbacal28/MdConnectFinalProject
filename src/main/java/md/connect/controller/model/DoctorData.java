package md.connect.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import md.connect.entity.Doctor;

@Data
@NoArgsConstructor
public class DoctorData{
  private Long doctorId;
  private String doctorFirstName;
  private String doctorLastName;
  private int age;
  private String gender;

  
  public DoctorData(Doctor doctor) {
    this.doctorId = doctor.getDoctorId();
    this.doctorFirstName = doctor.getDoctorFirstName();
    this.doctorLastName = doctor.getDoctorLastName();
    this.age = doctor.getAge();
    this.gender = doctor.getGender();
    
    
  }
  
  public Doctor toDoctor() {
    Doctor doctor = new Doctor();
    
    doctor.setDoctorId(doctorId);
    doctor.setDoctorFirstName(doctorFirstName);
    doctor.setDoctorLastName(doctorLastName);
    doctor.setAge(age);
    doctor.setGender(gender);
    
    return doctor;
  }
}
