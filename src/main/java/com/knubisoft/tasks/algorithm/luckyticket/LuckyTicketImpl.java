package com.knubisoft.tasks.algorithm.luckyticket;

public class LuckyTicketImpl implements LuckyTicket {
    @Override
    public boolean checkIsLuckyTicket(String ticket) {

        return stringValidation(ticket);
    }

    private boolean stringValidation(String ticket) {
        if (ticket == null || ticket.equals("") || ticket.length() == 1) {
            return false;
        }

        String str = new String(compareFirstElementWithLast(ticket));

        return str.equals(ticket);
    }

    public byte[] compareFirstElementWithLast(String ticket) {
        byte[] bytesString = ticket.getBytes();
        byte[] result = new byte[ticket.length()];

        int count = 1;
        for (int i = 0; i < bytesString.length; i++) {
            if (bytesString[i] == bytesString[bytesString.length - count]) {
                result[i] = bytesString[bytesString.length - count];
                count++;
            }
        }

        return result;
    }

}
