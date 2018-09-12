package entity;

import enums.CustomerType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;

@Entity
public class Customer implements Serializable
{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    
    @Enumerated(EnumType.STRING) // before this customerType was 0 for gold, now its GOLD
    private CustomerType customerType;
    
    @ElementCollection()
    private List<String> hobbies = new ArrayList();
    
    @ElementCollection(fetch = FetchType.LAZY)
    @MapKeyColumn(name = "PHONE")
    @Column(name = "Description")
    private Map<String, String> phones = new HashMap();

    public Customer()
    {
    }

    public Customer(String firstName, String lastName, CustomerType customerType)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerType = customerType;
    }

    public CustomerType getCustomerType()
    {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType)
    {
        this.customerType = customerType;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void addHobby(String s)
    {
        hobbies.add(s);
    }

    public String getHobbies()
    {
        return String.join(", ", hobbies);
    }

    public void addPhone(String phoneNo, String description)
    {
        phones.put(phoneNo, description);
    }

    public String getPhoneDescription(String phoneNo)
    {
        return phones.get(phoneNo);
    }

    public static void main(String[] args)
    {
        Customer c1 = new Customer();
        c1.addHobby("Football");
        c1.addHobby("Swinging");
        c1.addHobby("Chess");
        System.out.println(c1.getHobbies());
    }
}
