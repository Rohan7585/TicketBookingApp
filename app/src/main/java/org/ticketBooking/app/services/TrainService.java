package org.ticketBooking.app.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ticketBooking.app.entity.Train;
import org.ticketBooking.app.entity.User;
import org.ticketBooking.app.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TrainService {

    private Train trainlist;

    private List<Train> userList;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TrainService() throws IOException{
        userList = loadTrains();
    }

    public List<Train> loadTrains() throws IOException{
        File users = new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<Train>>() {});
    }



    private static final String USER_PATH = "../localDB/train.json";
    File file = new File(USER_PATH);
    //trainList = objectMapper.readValue(file, Train.class);
    public List<Train> searchTrains(String source, String dest){
        return trainList.stream().filter(train -> validTrain(train, source, dest)).collect(Collectors.toList());
    }
}
