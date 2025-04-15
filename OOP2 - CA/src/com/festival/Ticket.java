package com.festival;

public sealed abstract class Ticket permits RegularTicket, VIPTicket {
    protected final int ticketId;
    protected final String attendeeName;

    public Ticket(int ticketId, String attendeeName) {
        this.ticketId = ticketId;
        this.attendeeName = attendeeName;
    }
}