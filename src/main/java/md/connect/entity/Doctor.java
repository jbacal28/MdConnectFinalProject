package md.connect.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long doctorId;
  
  @EqualsAndHashCode.Exclude
  private String doctorFirstName;
  @EqualsAndHashCode.Exclude
  private String doctorLastName;
  @EqualsAndHashCode.Exclude
  private int age;
  @EqualsAndHashCode.Exclude
  private String gender;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne
  @JoinColumn(name = "license_id", nullable = false)
  private Specialties specialties;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "doctor_patient",
      joinColumns = @JoinColumn(name = "doctor_id"),
      inverseJoinColumns = @JoinColumn(name = "patient_id")
      )
  private Set<Patient> patients = new HashSet<>();
  
}
