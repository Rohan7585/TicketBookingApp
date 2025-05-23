package org.ticketBooking.app.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.PrimitiveValue;
import org.ticketBooking.app.entity.Train;
import org.ticketBooking.app.entity.User;
import org.ticketBooking.app.util.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {
    private User user;

    private List<User> userList;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String USER_PATH = "../localDB/users.json";

    //Constructor for user
    public UserBookingService(User user) throws IOException {
        this.user = user;
        laodUsers();
    }

    public UserBookingService() throws IOException{
        laodUsers();
    }

    public List<User> laodUsers() throws IOException{
        File users = new File(USER_PATH);
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }


    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassowrd(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch(IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException{
        File userFile = new File(USER_PATH);
        objectMapper.writeValue(userFile, userList);
    }

    public void fetchBooking(){
        user.printTickets();
    }

    public Boolean cancelBooking(String ticketId){
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String dest) throws IOException {
        TrainService trainService = new TrainService();
        return trainService.searchTrains(source, dest);
    }
}
