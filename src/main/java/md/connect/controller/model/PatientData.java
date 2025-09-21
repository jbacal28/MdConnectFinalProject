package md.connect.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import md.connect.entity.Patient;

@Data
@NoArgsConstructor
public class PatientData {
  private Long patientId;
  private String patientFirstName;
  private String patientLastName;
  private String dateOfBirth;
  private String phoneNumber;
  private String emailAddress;
  
  public PatientData(Patient patient) {
    this.patientId = patient.getPatientId();
    this.patientFirstName = patient.getPatientFirstName();
    this.patientLastName = patient.getPatientLastName();
    this.dateOfBirth = patient.getDateOfBirth();
    this.phoneNumber = patient.getPhoneNumber();
    this.emailAddress = patient.getEmailAddress();
  }

  public Patient toPatient() {
   Patient patient = new Patient();
   
   patient.setPatientId(patientId);
   patient.setPatientFirstName(patientFirstName);
   patient.setPatientLastName(patientLastName);
   patient.setDateOfBirth(dateOfBirth);
   patient.setPhoneNumber(phoneNumber);
   patient.setEmailAddress(emailAddress);
   
   return patient;
  }
  
  
}
