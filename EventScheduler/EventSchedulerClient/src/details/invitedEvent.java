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
public class invitedEvent implements Serializable{
     public static final long serialVersionUID = 122L;
    public int hostId,evenId,status;
    public String  hostName,eventName,invitationLink;
}
