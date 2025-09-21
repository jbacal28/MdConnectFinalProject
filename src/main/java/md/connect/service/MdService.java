package md.connect.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import md.connect.controller.model.DoctorData;
import md.connect.controller.model.PatientData;
import md.connect.controller.model.SpecialtiesData;
import md.connect.dao.DoctorDao;
import md.connect.dao.PatientDao;
import md.connect.dao.SpecialtiesDao;
import md.connect.entity.Doctor;
import md.connect.entity.Patient;
import md.connect.entity.Specialties;

@Service

public class MdService {
  
  @Autowired
  private SpecialtiesDao specialtiesDao;
  
  @Transactional(readOnly = false)
  public SpecialtiesData saveSpecialties(SpecialtiesData specialtiesData) {
    Specialties specialties = specialtiesData.toSpecialties();   
    Specialties dbSpecialties = specialtiesDao.save(specialties);
    
    return new SpecialtiesData(dbSpecialties);
  }

  @Transactional(readOnly = true)
  public SpecialtiesData retrieveLocationsById(Long licenseId) {
   Specialties specialties = findSpecialtiesById(licenseId);
   return new SpecialtiesData(specialties);
  }

  private Specialties findSpecialtiesById(Long licenseId) {
    return specialtiesDao.findById(licenseId)
        .orElseThrow(() -> new NoSuchElementException("Specialties with ID=" + licenseId + "was not found."));
  }

  @Transactional(readOnly = true)
  public List<SpecialtiesData> retrieveAllSpecialties() {
   // List<Specialties>specialtiesEntities = specialtiesDao.findAll();
   // List<SpecialtiesData> specialtiesDtos = new LinkedList<>();
   
    //for (Specialties specialties : specialtiesEntities) {
     // SpecialtiesData specialtyData = new SpecialtiesData(specialties);
      //specialtiesDtos.add(specialtyData);
      
   // }
    
    //return specialtiesDtos;
   
    //@formatter:off
    return specialtiesDao.findAll()
        .stream()
       // .sorted((specialty1, specialty2) -> 
   // specialty1.getBusinessName().compareTo(specialty1.getBusinessName()))
        .map(SpecialtiesData::new)
        .toList();
        
    //@formatter:on    
  }

  @Transactional(readOnly = false)
  public void deleteSpecialties(Long licenseId) {
    Specialties specialties = findSpecialtiesById(licenseId);
    specialtiesDao.delete(specialties);
  }

  @Autowired
  private PatientDao patientDao;
  
  @Transactional(readOnly = false)
  public PatientData savePatient(PatientData patientData) {
    Patient patient = patientData.toPatient();   
    Patient dbPatient = patientDao.save(patient);
    
    return new PatientData(dbPatient);
  }

  @Autowired
  private DoctorDao doctorDao;
  
  @Transactional(readOnly = false)
  public DoctorData saveDoctor(DoctorData doctorData, Long licenseId) {
    Specialties specialties  = findSpecialtiesById(licenseId);
    Doctor doctor = doctorData.toDoctor();
    copyDoctorFields(doctor,doctorData);
    doctor.setSpecialties(specialties);
    specialties.getDoctors().add(doctor);
    Doctor dbDoctor = doctorDao.save(doctor);
    
    return new DoctorData(dbDoctor);
  
  }

  private void copyDoctorFields(Doctor doctor, DoctorData doctorData) {
    doctor.setDoctorFirstName(doctorData.getDoctorFirstName());
    doctor.setDoctorLastName(doctorData.getDoctorLastName());
    doctor.setGender(doctorData.getGender());
    doctor.setAge(doctorData.getAge());
    
    
  }
  @Transactional(readOnly = false)
  public void joiningPatientDoctor(Long doctorId, Long patientId) {
    Doctor doctor = findDoctorById(doctorId);
    Patient patient = findPatientById(patientId);
    
    doctor.getPatients().add(patient);
    patient.getDoctors().add(doctor);
    
    
  }

  private Patient findPatientById(Long patientId) {
    Patient patient = patientDao.findById(patientId)
        .orElseThrow(() -> new NoSuchElementException("Patient with ID=" + patientId + " was not found."));
    
    return patient;
  }

  private Doctor findDoctorById(Long doctorId) {
    Doctor doctor = doctorDao.findById(doctorId)
        .orElseThrow(() -> new NoSuchElementException("Doctor with ID=" + doctorId + " was not found."));
    
    return doctor;
  }
}