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
public class slotFilter implements Serializable{
     public static final long serialVersionUID = 123L;
    public String venueName;
    public String startTime,endingTime;
    public String startingDate,endingDate;
    public boolean includeDate;
}
