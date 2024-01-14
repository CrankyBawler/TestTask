package com.gridnine.testing;

import com.gridnine.testing.Model.Flight;
import com.gridnine.testing.Model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Sorting {
    private List<Flight> flights = FlightBilder.createFlights();

    public void findFlightBeforeNow() {
        System.out.println("Первая сортировка");
        LocalDateTime now = LocalDateTime.now();

        List<Flight> flightsSortOne = this.flights.stream()
                .filter(flight -> flight.getSegments().get(0).getDeparture().isBefore(now))
                .collect(Collectors.toList());

        System.out.println("Вылеты до текущего момента времени: " + flightsSortOne);

    }
    public void findSegmentsWithArrivalBeforeDeparture() {
        System.out.println("Вторая сортировка");

        System.out.print("Напишите через сколько дней Вам нужно вылетать.");

        Scanner scanner = new Scanner(System.in);
        int dapartureDays = scanner.nextInt();
        scanner.close();

        LocalDateTime departureDate = LocalDateTime.now().plusDays(dapartureDays);

        List<Segment> resultSegments = flights.stream()
                .flatMap(flight -> flight.getSegments().stream())
                .filter(segment -> segment.getArrival().isBefore(departureDate))
                .collect(Collectors.toList());

        System.out.println("Сегменты (перелеты) с датой прилёта раньше даты вылета: " + resultSegments);
    }

    public void findTransferTimeMoreTwoHours() {
        System.out.println("Третья сортировка");

        System.out.println("Все вылеты:" + flights);
        System.out.println();

        List<Flight> segmentsTwoAndMore = flights.stream()
                .filter(flight -> flight.getSegments().size() > 1)
                .collect(Collectors.toList()); //проверяем чтоб в порлете было больше одного сегмента

        List<Flight> flightsMoreTwoSegments = new ArrayList<>(); //список, который в наполнится полетами в которых больше одного сегмента

        /*Теперь в списке полеты хотя бы с одной пересадкой, нужно пройти внешним циклом по перелетам,
        а внутренним по сегментам и вычесть из времени отправления следующего, время отправления предыдущего
        сегмента. Время пересадок просуммировать, и, если условие удовлетворяется, то добавить полет
        в результирующий список.*/
        for (int i = 0; i < flights.size(); i++) {// Проходимся по списку полетов где больше одного сегмента
            long transferTime = 0;

            for (int j = 0; j < flights.get(i).getSegments().size() - 1; j++) {

                LocalDateTime arrival = flights.get(i).getSegments().get(j).getArrival();
                LocalDateTime departure = flights.get(i).getSegments().get(j + 1).getDeparture();
                long limitTransferHours = 2;
                transferTime = transferTime + arrival.until(departure, ChronoUnit.HOURS);

                if (transferTime >= limitTransferHours) {
                    flightsMoreTwoSegments.add(flights.get(i));
                }// проходимся по сегментам и вычитаем из времени отправления следующего, время отправления предыдущего сегмента.
                // Время пересадок суммируем, и если время больше 2х часов то добавляем полет в сортировку.
            }
        }
        System.out.println("Перелеты, где общее время, проведённое на земле, превышает два часа: " + flightsMoreTwoSegments);
    }
}


