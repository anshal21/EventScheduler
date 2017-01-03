package details;


import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anshal
 */
public class slotInfo  implements Serializable{
      //public static final long serialVersionUID = 124L;
      public int bookingId,slotId,venueId;
      public String date,venueName;
      public String startTime,endTime;
      public int event;
    
}


