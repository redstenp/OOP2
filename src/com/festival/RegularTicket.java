package com.festival;

public final class RegularTicket extends Ticket {
    final String seatNumber;

    public RegularTicket(int ticketId, String attendeeName, String seatNumber) {
        super(ticketId, attendeeName);
        this.seatNumber = seatNumber;
    }
}