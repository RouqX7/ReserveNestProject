package com.example.ReserveNestProject.services;

import com.example.ReserveNestProject.models.Room;
import com.example.ReserveNestProject.repo.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }
    public void saveOrUpdate(Room room) {
        roomRepository.save(room); // Save or update the customer
    }


    public Room getRoomById(String roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room updateRoom(String roomId, Room roomDetails) {
        return roomRepository.findById(roomId)
                .map(room -> {
                    room.setName(roomDetails.getName());
                    room.setMaxCount(roomDetails.getMaxCount());
                    room.setPhoneNumber(roomDetails.getPhoneNumber());
                    room.setRentPerDay(roomDetails.getRentPerDay());
                    room.setDescription(roomDetails.getDescription());
                    // Assuming you want to update current bookings as well
                    room.setCurrentBookings(roomDetails.getCurrentBookings());
                    room.setType(roomDetails.getType());
                    room.setPrimaryImageUrl(roomDetails.getPrimaryImageUrl());
                    room.setExpandedImageUrls(roomDetails.getExpandedImageUrls());
                    return roomRepository.save(room);
                })
                .orElseGet(() -> {
                    roomDetails.set_id(roomId);
                    return roomRepository.save(roomDetails);
                });
    }

    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }

}
