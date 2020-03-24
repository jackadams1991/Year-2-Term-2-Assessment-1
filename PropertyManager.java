/*
- Author: Jack Adams
- Author Id: S0201412
- Date Created: 29 Aug 2019
- Use: Class for creating/persisting Property Manager objects
 */

package assignment2;

//Import statements
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.CascadeType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@Entity(name = "PROPERTY_MANAGER")
@Table(name = "property_manager")
@NamedQueries
    ({  //Named queries for the PropertyManager entity
        @NamedQuery(name = "displayAllManagers", query = "SELECT pm FROM PROPERTY_MANAGER pm"), //Query displays all persisted managers
        @NamedQuery(name = "displaySelectManager", query = "SELECT pm FROM PROPERTY_MANAGER pm WHERE pm.firstName = :fName AND pm.lastName = :lName") //Query displays given manager for user
    })
public class PropertyManager implements Serializable {
    
    //Property Manager Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MANAGER_ID")
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobilePhoneNumber;
    private String landLinePhoneNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "PROPERTY_MANAGER_FK")
    private List<Properties> properties;
    
    //Contstructors
    public PropertyManager()
    {
        
    }
    public PropertyManager(String fName, String lName, String email, String mobile, String phoneNum)
    {
        firstName = fName;
        lastName = lName;
        emailAddress = email;
        mobilePhoneNumber = mobile;
        landLinePhoneNumber = phoneNum;
    }
    
    //Setter Methods
    public void setId(Long managerId)
    {
        id = managerId;
    }
    public void setFirstName(String fName)
    {
        firstName = fName;
    }
    public void setLastName(String lName)
    {
        lastName = lName;
    }
    public void setEmailAddress(String email)
    {
        emailAddress = email;
    }
    public void setMobileNumber(String mobileNum)
    {
        mobilePhoneNumber = mobileNum;
    }
    public void setLandLinePhoneNumber(String phoneNum)
    {
        landLinePhoneNumber = phoneNum;
    }
    public void setProperties(List<Properties> properties)
    {
        this.properties = properties;
    }
    
    //Getter Methods
    public Long getId()
    {
        return id;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getEmailAddress()
    {
        return emailAddress;
    }
    public String getMobileNumber()
    {
        return mobilePhoneNumber;
    }
    public String getLandLineNumber()
    {
        return landLinePhoneNumber;
    }
    public List<Properties> getProperties()
    {
        return properties;
    }
}