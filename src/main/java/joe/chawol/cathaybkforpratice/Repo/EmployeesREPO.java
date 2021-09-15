package joe.chawol.cathaybkforpratice.Repo;

import joe.chawol.cathaybkforpratice.Enity.Employee;
import joe.chawol.cathaybkforpratice.QueryCond.EmployeeQueryCond;
import joe.chawol.cathaybkforpratice.Service.CustomEmployeeService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EmployeesREPO extends PagingAndSortingRepository<Employee, String>, CustomEmployeeService {

    List<Employee> searchByqc(EmployeeQueryCond qc);

    List<Employee> searchByqcAndPage(EmployeeQueryCond qc, Pageable page);

}
