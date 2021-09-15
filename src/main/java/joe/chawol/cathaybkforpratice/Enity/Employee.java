package joe.chawol.cathaybkforpratice.Enity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "EMPLOYEES")
@Getter
@Setter
//@JsonIgnoreProperties(value = {"empid", "establishtime", "lastupdate"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String empid;
    private String name;
    private String depid;
    private String gender;
    private String phonenumber;
    private String address;
    private int age;
    private Timestamp establishtime;
    private Timestamp lastupdate;

    public Employee(String name, String depid, String gender, String phonenumber, String address, int age, Timestamp establishtime, Timestamp lastupdate) {
        this.empid = empid;
        this.name = name;
        this.depid = depid;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.age = age;
        this.establishtime = establishtime;
        this.lastupdate = lastupdate;
    }

    public Employee(String name, String depid, String gender, String phonenumber, String address, int age) {
        this.name = name;
        this.depid = depid;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.address = address;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empid='" + empid + '\'' +
                ", name='" + name + '\'' +
                ", depid='" + depid + '\'' +
                ", gender='" + gender + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", establishtime=" + establishtime +
                ", lastupdate=" + lastupdate +
                '}';
    }

    public Employee() {
    }
}
