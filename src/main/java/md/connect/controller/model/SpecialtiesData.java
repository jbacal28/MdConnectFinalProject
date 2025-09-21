package md.connect.controller.model;

import java.util.HashSet;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import md.connect.entity.Doctor;
import md.connect.entity.Specialties;

@Data
@NoArgsConstructor
public class SpecialtiesData {
  private Long licenseId;
  private String specialtyName;
  private Set<DoctorData> doctors = new HashSet<>();
  
  public SpecialtiesData(Specialties specialties) {
    this.licenseId = specialties.getLicenseId();
    this.specialtyName = specialties.getSpecialtyName();
    
    for(Doctor doctor : specialties.getDoctors()) {
      this.doctors.add(new DoctorData(doctor));
    }
  }
  
  public SpecialtiesData(Long licenseId,String specialtyName) {
   this.licenseId = licenseId;
   this.specialtyName = specialtyName;
  }
  
  public Specialties toSpecialties() {
    Specialties specialties = new Specialties();
    
    specialties.setLicenseId(licenseId);
    specialties.setSpecialtyName(specialtyName);
    
    for (DoctorData doctorData : doctors) {
      specialties.getDoctors().add(doctorData.toDoctor());
    }
    
    return specialties;
  }  
  
}

