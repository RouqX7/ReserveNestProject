// src/pages/AdminRooms.js
import React, { useState, useEffect } from "react";
import axios from "axios";
import RoomForm from "../../components/rooms/admin/RoomForm";
import RoomList from "../../components/rooms/admin/RoomList";

const AdminRooms = () => {
  const [rooms, setRooms] = useState([]);
  const [editingRoom, setEditingRoom] = useState(null);

  useEffect(() => {
    loadRooms();
  }, []);

  const loadRooms = async () => {
    try {
      const response = await axios.get("http://localhost:8081/api/rooms/all");
      setRooms(response.data);
    } catch (error) {
      console.error("Failed to load rooms:", error);
    }
  };

  const handleCreateOrUpdateRoom = async (room) => {
    if (editingRoom) {
      await updateRoom(room);
    } else {
      await createRoom(room);
    }
  };

  const createRoom = async (room) => {
    try {
      await axios.post("http://localhost:8081/api/rooms/create", room);
      alert("Room Created Successfully");
      loadRooms(); // Reload the room list
    } catch (err) {
      alert("Room Creation Failed: " + err.message);
    }
  };

  const updateRoom = async (room) => {
    try {
      await axios.put(`http://localhost:8081/api/rooms/update/${room.id}`, room);
      alert("Room Updated Successfully");
      loadRooms(); // Reload the room list
    } catch (err) {
      alert("Room Update Failed: " + err.message);
    }
  };

  const deleteRoom = async (roomId) => {
    try {
      await axios.delete(`http://localhost:8081/api/rooms/delete/${roomId}`);
      alert("Room Deleted Successfully");
      loadRooms(); // Reload the room list
    } catch (err) {
      alert("Room Deletion Failed: " + err.message);
    }
  };

  const handleEditClick = (room) => {
    setEditingRoom(room);
  };

  const handleDeleteClick = (roomId) => {
    deleteRoom(roomId);
  };

  return (
    <div>
      <h1>Room Management</h1>
      <RoomForm onSave={handleCreateOrUpdateRoom} roomData={editingRoom} />
      <RoomList rooms={rooms} onEdit={handleEditClick} onDelete={handleDeleteClick} />
    </div>
  );
};

export default AdminRooms;
