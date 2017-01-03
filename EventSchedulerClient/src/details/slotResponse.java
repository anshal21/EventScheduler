/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package details;

import java.io.Serializable;

/**
 *
 * @author anshal
 */
public class slotResponse implements Serializable{
     public static final long serialVersionUID = 125L;
    public int slotId;
    public String date;
    public String startingTime,endingTime;
    public boolean dateIncluded;
    
}
