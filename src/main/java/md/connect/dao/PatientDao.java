package md.connect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import md.connect.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
