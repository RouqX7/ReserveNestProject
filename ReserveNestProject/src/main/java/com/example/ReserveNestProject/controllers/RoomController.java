package com.example.ReserveNestProject.controllers;

import com.example.ReserveNestProject.models.Room;
import com.example.ReserveNestProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {


    @Autowired
    private RoomService roomService;



    @PostMapping("/create")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable String id) {
        Room room = roomService.getRoomById(id);
        return ResponseEntity.ok(room);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(roomService.getAllRooms());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable String id, @RequestBody Room room) {
        Room updatedRoom = roomService.updateRoom(id, room);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

    @PostMapping(value ="/save")
    public String saveRoom(@RequestBody Room room){
        roomService.saveOrUpdate(room);
        return  room.get_id();
    }

    @GetMapping("/search/{id}")
    public Room getRooms(@PathVariable(name = "id")String roomId){

        return  roomService.getRoomById(roomId);
    }


    // Additional endpoints as necessary...
}
