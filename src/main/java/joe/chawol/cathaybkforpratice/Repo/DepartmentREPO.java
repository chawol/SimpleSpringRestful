package joe.chawol.cathaybkforpratice.Repo;

import joe.chawol.cathaybkforpratice.Enity.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentREPO extends CrudRepository<Department, String> {

    Department findDepartmentByDEPNAME(String depName);
}
