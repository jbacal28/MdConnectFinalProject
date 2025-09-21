package md.connect.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import md.connect.controller.model.SpecialtiesData;
import md.connect.entity.Specialties;

public class MdServiceTestSupport {
  
  private static final String SPECIALTIES_TABLE = "specialties";

  //formatter:off
  private SpecialtiesData insertSpecialty1 = new SpecialtiesData (
      1L,
      "GI Doctor"
      );
  
  private SpecialtiesData insertSpecialty2 = new SpecialtiesData (
      2L,
      "Internal Medicine"
      );
  
  //formatted:on
  
  @Autowired
  private JdbcTemplate jdbcTemplate;
  
  @Autowired
  private MdController mdController;
  
  protected SpecialtiesData buildInsertSpecialties(int which) {
   
    return which == 1 ? insertSpecialty1 : insertSpecialty2;
  }
  
  protected int rowsInSpecialtiesTable() {
    return JdbcTestUtils.countRowsInTable(jdbcTemplate, SPECIALTIES_TABLE);
  }
  
  
  
  protected SpecialtiesData retrieveSpecialties(Long licenseId) {
    return mdController.retrieveSpecialties(licenseId);
  }
 

  protected SpecialtiesData insertSpecialties(SpecialtiesData specialtiesData) {
    Specialties specialties = specialtiesData.toSpecialties();
    SpecialtiesData clone = new SpecialtiesData(specialties);
    
    clone.setLicenseId(null);
    return mdController.createSpecialties(clone);
  }
  
  protected List<SpecialtiesData> insertTwoSpecialties() {
    SpecialtiesData  specialties1 = insertSpecialties(buildInsertSpecialties(1));
    SpecialtiesData  specialties2 = insertSpecialties(buildInsertSpecialties(2));
    
    return List.of(specialties1, specialties2);
  }

  protected List<SpecialtiesData> retrieveAllSpecialties() {
    // TODO Auto-generated method stub
    return null;
}
}
