package md.connect.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import md.connect.controller.model.SpecialtiesData;
import md.connect.controller.model.DoctorData;
import md.connect.controller.model.PatientData;
import md.connect.service.MdService;

@RestController
@RequestMapping("/md_connect")
@Slf4j
public class MdController {
  
  @Autowired
  private MdService mdService;
  
  //CREATE
  
  @PostMapping("/specialties")
  @ResponseStatus(code = HttpStatus.CREATED)
 public SpecialtiesData createSpecialties(@RequestBody SpecialtiesData specialtiesData) {
   log.info("Creating Specialties {}", specialtiesData);
   return mdService.saveSpecialties(specialtiesData);
 }
  
  @PostMapping("/patient")
  @ResponseStatus(code = HttpStatus.CREATED)
  public PatientData addPatient(@RequestBody PatientData patientData) {
    log.info("Adding Patient {}",patientData);
    
    return mdService.savePatient(patientData);
  }
  
  @PostMapping("/doctor/{licenseId}")
  @ResponseStatus(code = HttpStatus.CREATED)
  public DoctorData addDoctor(@PathVariable Long licenseId, @RequestBody DoctorData doctorData) {
    log.info("Adding Doctor {} to license specialty with ID {}",doctorData,licenseId);
    return mdService.saveDoctor(doctorData,licenseId);
  }
  
  @PostMapping("/doctor/{doctorId}/patient/{patientId}")
  public Map<String, String> joinDoctorPatient(@PathVariable Long doctorId, 
      @PathVariable Long patientId) {
    log.info("Adding patient with ID {} to doctor with ID {}", patientId,doctorId);
    mdService.joiningPatientDoctor(doctorId,patientId);
    return Map.of("message", "Patient with ID" + patientId + "joined with Doctor with ID " + doctorId);
  }
  
  
  //UPDATE
  @PutMapping("/specialties/{licenseId}")
  public SpecialtiesData updateSpecialties(@PathVariable Long licenseId,
      @RequestBody SpecialtiesData specialtiesData) {
    specialtiesData.setLicenseId(licenseId);
    log.info("Updating Specialties {}", specialtiesData);
    return mdService.saveSpecialties(specialtiesData);
  }
  
  //RETRIEVED
  
  @GetMapping("/specialties/{licenseId}")
  public SpecialtiesData retrieveSpecialties(@PathVariable Long licenseId) {
    log.info("Retrieving specialties with ID={}", licenseId);
    return mdService.retrieveLocationsById(licenseId);
  }

  @GetMapping("/specialties")
  public List<SpecialtiesData> retrieveAllSpecialties() {
    log.info("Retrieving all soecialties");
    return mdService.retrieveAllSpecialties();
  }
  
  
  //DELETE
  @DeleteMapping("/specialties/{licenseId}")
  public Map<String, String> deleteSpecialties(@PathVariable Long licenseId){
    log.info("Deleting specialties with ID=" + licenseId + ".");
    mdService.deleteSpecialties(licenseId);
    
    return Map.of("message", "Specialties with ID=" + licenseId + " was successfully deleted.");
  }
}
