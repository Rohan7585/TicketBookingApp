package org.ticketBooking.app.services;

import org.ticketBooking.app.entity.Train;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class TrainService {
    public List<Train> searchTrains(String source, String dest{
        return trainList.stream().filter(train -> validTrain(train, source, dest)).collect(Collectors.toList());
    }
}
