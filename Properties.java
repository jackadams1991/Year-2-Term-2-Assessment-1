/*
- Author: Jack Adams
- Author Id: S0201412
- Date Created: 29 Aug 2019
- Use: Parent (Super) class for abstracting common attributes of child (sub) classes
 */
package assignment2;

//Import statements
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity(name = "PROPERTIES")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "properties")
@NamedQueries
    ({  //Named queries for Properties entity
        @NamedQuery(name = "displayAllProperties", query = "SELECT p FROM PROPERTIES p"), //Query Displays all persisted properties
        @NamedQuery(name = "displaySelectProperty", query = "SELECT p FROM PROPERTIES p WHERE p.addressDetails LIKE :addressConCat") //Query displays a given property for the user
    })
public abstract class Properties implements Serializable {
    
    //Properties Attributes
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "Property_ID")
    private Long id;
    private String addressDetails;
    private double numRooms;
    private double numBathrooms;
    private String propertyDesc;
    private String type;
    @Column(insertable = false, updatable = false)
    private String dtype;
    
    //Constructor
    public Properties()
    {
        
    }
    public Properties(String propertyAddress, int noRooms, int noBathrooms, String desc, String propertyType)
    {
        addressDetails = propertyAddress;
        numRooms = noRooms;
        numBathrooms = noBathrooms;
        propertyDesc = desc;
        type = propertyType;
    }
    
    //Setter methods
    public void setId(Long propertyId)
    {
        id = propertyId;
    }
    public void setAddress(String propertyAddress)
    {
        addressDetails = propertyAddress;
    }
    public void setNumRooms(double noRooms)
    {
        numRooms = noRooms;
    }
    public void setNumBathrooms(double noBathrooms)
    {
        numBathrooms = noBathrooms;
    }
    public void setDescription(String desc)
    {
        propertyDesc = desc;
    }
    public void setType(String propertyType)
    {
        type = propertyType;
    }
    
    //Getter methods
    public Long getId()
    {
        return id;
    }
    public String getAddress()
    {
        return addressDetails;
    }
    public double getNumRooms()
    {
        return numRooms;
    }
    public double getNumBathrooms()
    {
        return numBathrooms;
    }
    public String getDescription()
    {
        return propertyDesc;
    }
    public String getType()
    {
        return type;
    }
}