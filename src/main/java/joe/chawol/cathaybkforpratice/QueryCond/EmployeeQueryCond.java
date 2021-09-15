package joe.chawol.cathaybkforpratice.QueryCond;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmployeeQueryCond {
    private String empid;
    private String name;
    private String depid;
    private String gender;
    private String phonenumber;
    private String address;
    private String depname;
    private int age;
    private Timestamp establishtime;
    private Timestamp lastupdate;
}
