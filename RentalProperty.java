/*
- Author: Jack Adams
- Author Id: S0201412
- Date Created: 29 Aug 2019
- Use: Child (sub) class for constructing/persisting properties for rent
 */
package assignment2;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "properties")
public class RentalProperty extends Properties implements Serializable {

    private double rentalPrice;
    
    //Constructor
    public RentalProperty()
    {
        
    }
    public RentalProperty(String propertyAddress, int noRooms, int noBathrooms, String desc, String propertyType, double price)
    {
        super(propertyAddress, noRooms, noBathrooms, desc, propertyType);
        rentalPrice = price;
    }
    
    public void setRentalPrice(double price)
    {
        rentalPrice = price;
    }
    
    public double getRentalPrice()
    {
        return rentalPrice;
    }
}