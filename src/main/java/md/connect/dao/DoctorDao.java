package md.connect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import md.connect.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

}
