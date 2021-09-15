package joe.chawol.cathaybkforpratice.Enity.Dto;

import joe.chawol.cathaybkforpratice.Enity.Employee;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmployeeDto {
    private String empid;
    private String name;
    private String depname;
    private String gender;
    private String phonenumber;
    private String address;
    private int age;
    private Timestamp establishtime;
    private Timestamp lastupdate;

    public EmployeeDto(Employee emp) {
        this.empid = emp.getEmpid();
        this.name = emp.getName();
        this.gender = emp.getGender();
        this.phonenumber = emp.getPhonenumber();
        this.address = emp.getAddress();
        this.age = emp.getAge();
        this.establishtime = emp.getEstablishtime();
        this.lastupdate = emp.getLastupdate();
    }

    public EmployeeDto(String empid, String name, String depname, String gender, String phonenumber, String address, int age, Timestamp establishtime, Timestamp lastupdate) {
        this.empid = empid;
        this.name = name;
        this.depname = depname;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.age = age;
        this.establishtime = establishtime;
        this.lastupdate = lastupdate;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "empid='" + empid + '\'' +
                ", name='" + name + '\'' +
                ", depname='" + depname + '\'' +
                ", gender='" + gender + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", establishtime=" + establishtime +
                ", lastupdate=" + lastupdate +
                '}';
    }

    public EmployeeDto() {
    }
}
