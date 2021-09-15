package joe.chawol.cathaybkforpratice.Service;

import joe.chawol.cathaybkforpratice.Enity.Employee;
import joe.chawol.cathaybkforpratice.QueryCond.EmployeeQueryCond;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomEmployeeService {
    List<Employee> searchByqc(EmployeeQueryCond qc);

    List<Employee> searchByqcAndPage(EmployeeQueryCond qc, Pageable page);

}
