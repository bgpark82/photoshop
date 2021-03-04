package com.bgpark.photoshop.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookingDto {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Req {

        private Passenger passenger;
        private Payment payment;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AcknowledgeReq {

        private String status;
        private double totalFare;
        private String pnrNo;
        private Passenger passenger;
    }
}
