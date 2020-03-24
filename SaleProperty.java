/*
- Author: Jack Adams
- Author Id: S0201412
- Date Created: 29 Aug 2019
- Use: Child (sub) class for constructing/persisting properties for sale
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
public class SaleProperty extends Properties implements Serializable {

    private double salePrice;
    
    //Constructor
    public SaleProperty()
    {
        
    }
    public SaleProperty(String propertyAddress, int noRooms, int noBathrooms, String desc, String propertyType, double price)
    {
        super(propertyAddress, noRooms, noBathrooms, desc, propertyType);
        salePrice = price;
    }
    
    public void setSalePrice(double price)
    {
        salePrice = price;
    }
    
    public double getSalePrice()
    {
        return salePrice;
    }
    
}