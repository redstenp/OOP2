package com.festival;

public final class VIPTicket extends Ticket {
    final String loungeAccess;

    public VIPTicket(int ticketId, String attendeeName, String loungeAccess) {
        super(ticketId, attendeeName);
        this.loungeAccess = loungeAccess;
    }
}
