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
public class eventInfo implements Serializable{
    public static final long serialVersionUID = 121L;
    public String event_name,venueName,startTime,endTime; 
    public int venueId,slotId,eventId,bookingId;
    public String invitationLink;
}
