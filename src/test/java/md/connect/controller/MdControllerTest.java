package md.connect.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import md.connect.MdConnectApplication;
import md.connect.controller.model.SpecialtiesData;


@SpringBootTest(webEnvironment = WebEnvironment.NONE, 
classes = MdConnectApplication.class)

@ActiveProfiles("test")
@Sql(scripts = {"classpath.schema.sql", "classpath.data.sql" })
@SqlConfig(encoding = "utf-8")
class MdControllerTest extends MdServiceTestSupport {

  @BeforeEach
  void setUp() throws Exception {}

  @Test
  void testInsertSpecialties() {
    //Given: Specialty Request
    SpecialtiesData request = buildInsertSpecialties(1);
    SpecialtiesData expected =  buildInsertSpecialties(1);
    
    //When: Specialty is added to the specialties table
    SpecialtiesData actual = insertSpecialties(request);
    
    //Then: specialties returned is what is expected
    assertThat(actual).isEqualTo(expected);
    
    
    //And: there is one row in the specialty table
    assertThat(rowsInSpecialtiesTable()).isOne();
  }
  
  @Test
  void testRetrieveLocation() {
  //Given a specialty
    SpecialtiesData specialties = insertSpecialties(buildInsertSpecialties(1));
    SpecialtiesData expected = buildInsertSpecialties(1);
    
  //When: 
    SpecialtiesData actual = retrieveSpecialties(specialties.getLicenseId());
    
  //Then: 
    assertThat(actual).isEqualTo(expected);
 
  }

  @Test
  void testRetrieveAllSpecialties() {
    //Given: 
    List<SpecialtiesData> expected = insertTwoSpecialties();
    
    //When:
    List<SpecialtiesData> actual = retrieveAllSpecialties();
    
    //Then:
    assertThat(actual).isEqualTo(expected);
    
  }

  
  }

