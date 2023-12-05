import React from 'react';

const RoomList = ({ rooms, onEdit, onDelete }) => {
    return (
        <table className="table">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Max Count</th>
                    <th>Phone Number</th>
                    <th>Rent Per Day</th>
                    <th>Description</th>
                    <th>Available</th>
                    <th>Actions</th>
                    <th>Type</th>
                </tr>
            </thead>
            <tbody>
                {rooms.map((room) => (
                    <tr key={room.id}>
                        {/* Room Data */}
                        <td>{room.name}</td>
                        <td>{room.maxCount}</td>
                        <td>{room.phoneNumber}</td>
                        <td>{room.rentPerDay}</td>
                        <td>{room.description}</td>
                        <td>{room.primaryImageUrl}</td>
                        <td>{room.expandedImageUrls}</td>
                        <td>{room.type}</td>
                        <td>{room.available ? 'Yes' : 'No'}</td>
                        <td>
                            <button onClick={() => onEdit(room)}>Edit</button>
                            <button onClick={() => onDelete(room.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default RoomList;
