package joe.chawol.cathaybkforpratice.Service;

import joe.chawol.cathaybkforpratice.Enity.Employee;
import joe.chawol.cathaybkforpratice.QueryCond.EmployeeQueryCond;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomEmployeeServiceImpl implements CustomEmployeeService {
    @Autowired
    private EntityManager em;

    private CriteriaQuery<Employee> toPredicate(EmployeeQueryCond qc) {
        CriteriaBuilder cb = em.getCriteriaBuilder(


        );
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        Root<Employee> emp = cq.from(Employee.class);
        List<Predicate> predicates = new ArrayList<>();
        if (qc.getEmpid() != null) {
            predicates.add(cb.equal(emp.get("empid"), qc.getEmpid()));
        }
        if (qc.getName() != null) {
            predicates.add(cb.like(cb.lower(emp.get("name")), "%" + qc.getName().toLowerCase(Locale.ROOT) + "%"));
        }
        if (qc.getDepid() != null) {
            predicates.add(cb.equal(emp.get("depid"), qc.getDepid()));
        }
        if (qc.getAge() != 0) {
            predicates.add(cb.equal(emp.get("age"), qc.getAge()));
        }
        cq.where(predicates.toArray(new Predicate[0]));
        return cq;
    }

    @Override
    public List<Employee> searchByqc(EmployeeQueryCond qc) {
        return em.createQuery(toPredicate(qc)).getResultList();
    }


    @Override
    public List<Employee> searchByqcAndPage(EmployeeQueryCond qc, Pageable page) {
        TypedQuery<Employee> typedQuery = em.createQuery(toPredicate(qc));
        typedQuery.setFirstResult((page.getPageNumber() - 1) * page.getPageSize());
        typedQuery.setMaxResults(page.getPageSize());
        return typedQuery.getResultList();
    }


}
