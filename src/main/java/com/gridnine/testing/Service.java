package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Service {

    static LocalDateTime dateTimeNow = LocalDateTime.now();

    //получаем список результатов обработки тестового набора перелётов
    static List<Flight> outputTestSet() {
       return FlightBuilder.createFlights()
                .stream().toList();
    }

    //выводим  список, в котором вылет до текущего момента времени
    static List<Flight> outputFlightWhereDepartureToTheCurrentPointInTime(List<Flight>allFls) {
        return allFls.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate()
                                .isBefore(dateTimeNow)))
                .collect(Collectors.toList());
    }

    //выводим список, в котором сегменты с датой прилёта раньше даты вылета
    static List<Flight> outputFlightWhereArrivalDateBeforeDepartureDate(List<Flight>allFls) {
        return allFls.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate()
                                .isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }


    //выводим список, перелетов, где общее время, проведённое на земле, превышает два часа
    static List<Flight> outputFlightWhereTotalTimeSpentOnEarthExceedsTwoHours(List<Flight>allFls) {
        List<Flight> twoHoursAtEarth = new ArrayList<>();
        for(allFls.listIterator().forEachRemaining(Flight::getSegments)) {
//            List<Segment> seg = flight.getSegments();
        List<Segment>seg = allFls.stream()
                .map(flight -> flight.getSegments().stream()).toList();
            for (int i = 0; i < seg.size()-1; i++) {
                LocalDateTime arrivalDateTime = seg.get(i).getArrivalDate();
                LocalDateTime departureDateTime=seg.get(i+1).getDepartureDate();
                if(departureDateTime.isAfter(arrivalDateTime.plusHours(2))){
                    twoHoursAtEarth.add(flight );
                }
            }
//        }

        return twoHoursAtEarth;
    }


}
