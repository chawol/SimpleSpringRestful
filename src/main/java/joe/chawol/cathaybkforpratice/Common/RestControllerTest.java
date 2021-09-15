package joe.chawol.cathaybkforpratice.Common;

import com.fasterxml.jackson.databind.ObjectMapper;
import joe.chawol.cathaybkforpratice.Enity.Dto.EmployeeDto;
import joe.chawol.cathaybkforpratice.Enity.Employee;
import joe.chawol.cathaybkforpratice.Repo.DepartmentREPO;
import joe.chawol.cathaybkforpratice.Repo.EmployeesREPO;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RestControllerTest {
    @Autowired
    private MockMvc mvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    EmployeesREPO employeesREPO;
    @Autowired
    DepartmentREPO departmentREPO;


    @Test
    public void testBasicApi() throws Exception {
        mvc.perform(get("/api").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Employee[] aa = objectMapper.readValue(result.getResponse().getContentAsString(), Employee[].class);
                    assertTrue(5 == aa.length);
                });
        //起始假資料三筆確定正常
    }

    @Test
    public void addNewOne() throws Exception {
        String testData = "{ \"name\":\"阿良良木君\", \"depid\":\"110\", \"gender\":\"MALE\", \"phonenumber\":\"0977777777\", \"address\":\"某個地方\", \"age\":\"55\"}\n";
        mvc.perform(post("/api/create").contentType(MediaType.APPLICATION_JSON)
                        .content(testData)).andExpect(status().isOk())
                .andExpect(result -> {
                    Employee res = objectMapper.readValue(result.getResponse().getContentAsString(), Employee.class);
                    assertTrue(employeesREPO.findById(res.getEmpid()) != null);
                });
        //新增一筆並且有找到
    }

    @Test
    public void findByName() throws Exception {
        String testData = "{ \"name\":\"third\"}";
        mvc.perform(post("/api/findby/1").contentType(MediaType.APPLICATION_JSON)
                        .content(testData)).andExpect(status().isOk())
                .andExpect(result -> {
                    EmployeeDto[] res = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), EmployeeDto[].class);
                    assertTrue(3 == res.length);
                });
    }

    @Test
    public void findByNameAndAge() throws Exception {
        String testData = "{ \"name\":\"third\" ,\"age\":\"20\"}";
        mvc.perform(post("/api/findby/1").contentType(MediaType.APPLICATION_JSON)
                        .content(testData)).andExpect(status().isOk())
                .andExpect(result -> {
                    EmployeeDto[] res = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), EmployeeDto[].class);
                    assertTrue(2 == res.length);
                });
    }

    @Test
    public void findByNameAndDep() throws Exception {
        String testData = "{ \"name\":\"third\" ,\"depname\":\"測試部門120\"}";
        mvc.perform(post("/api/findby/1").contentType(MediaType.APPLICATION_JSON)
                        .content(testData)).andExpect(status().isOk())
                .andExpect(result -> {
                    EmployeeDto[] res = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), EmployeeDto[].class);
                    assertTrue(4 == res.length);
                });
    }

    @Test
    public void findByEmpID() throws Exception {
        String testData = "{ \"empid\":\"1\"}";
        Employee po = employeesREPO.findById("1").get();
        EmployeeDto temp = new EmployeeDto(po);
        temp.setDepname(departmentREPO.findById(po.getDepid()).get().getDEPNAME());


        mvc.perform(post("/api/findby/1").contentType(MediaType.APPLICATION_JSON)
                        .content(testData)).andExpect(status().isOk())
                .andExpect(result -> {
                    EmployeeDto[] res = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), EmployeeDto[].class);
                    assertTrue(temp.toString().equals(res[0].toString()));
                });
        //由於比較時會比記憶體位置 上面我是用new 所以直接比會不同 這邊偷懶用toString 來比
    }

    @Test
    public void updateTest() throws Exception {
        String testData = "{ \"name\":\"阿良良木君U\", \"depid\":\"110\", \"gender\":\"MALE\", \"phonenumber\":\"097112235\", \"address\":\"某個地方666\", \"age\":\"20\"}";
//        Employee po = new Employee("阿良良木君U", "110", "MALE", "097112235", "某個地方666", "20);
//        EmployeeDto temp = new EmployeeDto(po);
//        temp.setDepname(departmentREPO.findById(po.getDepid()).get().getDEPNAME());


        mvc.perform(post("/api/update/4").contentType(MediaType.APPLICATION_JSON)
                        .content(testData)).andExpect(status().isOk())
                .andExpect(result -> {
                    Employee po = employeesREPO.findById("4").get();
                    Employee res = objectMapper.readValue(result.getResponse().getContentAsString(StandardCharsets.UTF_8), Employee.class);
                    assertTrue(po.toString().equals(res.toString()));
                });
        //由於比較時會比記憶體位置 上面我是用new 所以直接比會不同 這邊偷懶用toString 來比
    }


}
