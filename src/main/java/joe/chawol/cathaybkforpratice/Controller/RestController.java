package joe.chawol.cathaybkforpratice.Controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import joe.chawol.cathaybkforpratice.Enity.Dto.EmployeeDto;
import joe.chawol.cathaybkforpratice.Enity.Employee;
import joe.chawol.cathaybkforpratice.QueryCond.EmployeeQueryCond;
import joe.chawol.cathaybkforpratice.Repo.DepartmentREPO;
import joe.chawol.cathaybkforpratice.Repo.EmployeesREPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    DepartmentREPO departmentREPO;
    @Autowired
    EmployeesREPO employeesREPO;

    @GetMapping
    public List<Employee> findAll() {


        return (List<Employee>) employeesREPO.findAll();
    }

    @RequestMapping("/create")
    public Employee Create(@RequestBody String convertToDto) {
        Employee emp = convertToDto(convertToDto);
        emp.setLastupdate(new Timestamp(System.currentTimeMillis()));
        emp.setEstablishtime(new Timestamp(System.currentTimeMillis()));
        return employeesREPO.save(emp);
    }

    @RequestMapping("/update/{id}")
    public Employee Update(@PathVariable String id, @RequestBody String employees) {
        Employee upEmp = convertToDto(employees);
        return employeesREPO.findById(id).map(
                employee1 -> {
                    if (StringUtils.isNotEmpty(upEmp.getDepid()))
                        employee1.setDepid(upEmp.getDepid());
                    if (StringUtils.isNotEmpty(upEmp.getAddress()))
                        employee1.setAddress(upEmp.getAddress());
                    if (StringUtils.isNotEmpty(upEmp.getGender()))
                        employee1.setGender(upEmp.getGender());
                    if (StringUtils.isNotEmpty(upEmp.getName()))
                        employee1.setName(upEmp.getName());
                    if (StringUtils.isNotEmpty(upEmp.getPhonenumber()))
                        employee1.setPhonenumber(upEmp.getPhonenumber());
                    return employeesREPO.save(employee1);
                }
        ).orElseGet(() -> employeesREPO.save(upEmp));
    }

    @RequestMapping("/findby/{page}")
    public List<EmployeeDto> findbyCond(@RequestBody String employees, @PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 10);
        EmployeeQueryCond qc = convertToCond(employees);
        if (StringUtils.isNotEmpty(qc.getDepname()))
            qc.setDepid(departmentREPO.findDepartmentByDEPNAME(qc.getDepname()).getDEPID());
        HashMap<String, String> depList = new HashMap<>();
        departmentREPO.findAll().forEach(d -> {
            depList.put(d.getDEPID(), d.getDEPNAME());
        });
        return employeesREPO.searchByqcAndPage(qc, pageable).stream().map(d -> {
            EmployeeDto dto = new EmployeeDto(d);
            dto.setDepname(depList.get(d.getDepid()));
            return dto;
        }).collect(Collectors.toList());
    }

    @DeleteMapping("/delete/{id}")
    public boolean Delete(@PathVariable String id) {
        try {
            employeesREPO.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    private Employee convertToDto(String employee) {
        Employee emp = new Employee();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            emp = objectMapper.readValue(employee, Employee.class);
            emp.setEstablishtime(new Timestamp(System.currentTimeMillis()));
            emp.setLastupdate(new Timestamp(System.currentTimeMillis()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return emp;
    }

    private EmployeeQueryCond convertToCond(String employee) {
        EmployeeQueryCond emp = new EmployeeQueryCond();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            emp = objectMapper.readValue(employee, EmployeeQueryCond.class);
            emp.setEstablishtime(new Timestamp(System.currentTimeMillis()));
            emp.setLastupdate(new Timestamp(System.currentTimeMillis()));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return emp;
    }
}
