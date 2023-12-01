package com.example.ReserveNestProject.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.ReserveNestProject.models.Room;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {
     List<Room> findByAvailable(boolean available);
}
