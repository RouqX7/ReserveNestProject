import React, { useState } from 'react';
import axios from 'axios';

const RoomForm = ({  roomData }) => {
    const [room, setRoom] = useState(roomData || {
        name: '',
        maxCount: 0,
        phoneNumber: '',
        rentPerDay: 0,
        description: '',
        available: false,
        primaryImageUrl: '',
        expandedImageUrls: [],
        type: ''
    });
    const [primaryImageFile, setPrimaryImageFile] = useState(null);
    const [additionalImageFiles, setAdditionalImageFiles] = useState([]);

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setRoom({ ...room, [name]: type === 'checkbox' ? checked : value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Upload primary image
        const primaryImageUrl = await handlePrimaryImageUpload();
        if (primaryImageUrl) {
            setRoom((prevRoom) => ({ ...prevRoom, primaryImageUrl }));
        }

        // Upload additional images
        const additionalImageUrls = await handleAdditionalImagesUpload();
        if (additionalImageUrls.length > 0) {
            setRoom((prevRoom) => ({ ...prevRoom, expandedImageUrls: additionalImageUrls }));
        }

        // Save room data with images
        try {
            const updatedRoom = {
                ...room,
                primaryImageUrl,
                expandedImageUrls: additionalImageUrls
            };
            const response = await axios.post('http://localhost:8081/api/rooms/create', updatedRoom);
            console.log('Room saved:', response.data);
            // Additional actions after successful save...
        } catch (error) {
            console.error('Error saving room:', error);
        }
    };

    

    const handleFileUpload = async (event) => {
        const file = event.target.files[0];
        if (!file) return;

        const fileUrl = await uploadFileToS3(file);
        if (fileUrl) {
            console.log('File URL:', fileUrl);
            setRoom({ ...room, primaryImageUrl: fileUrl });
        }
    };

    const requestPresignedUrl = async (fileName) => {
        try {
            const response = await axios.post('http://localhost:8081/api/s3/generate-presigned-url', { fileName });
            return response.data;
        } catch (error) {
            console.error('Error requesting pre-signed URL:', error);
            return null;
        }
    };

    const uploadFileToS3 = async (file) => {
        const preSignedUrl = await requestPresignedUrl(file.name);

        if (!preSignedUrl) {
            console.error('Failed to get pre-signed URL');
            return;
        }

        try {
            const uploadResponse = await fetch(preSignedUrl, {
                method: 'PUT',
                body: file,
            });

            if (uploadResponse.ok) {
                console.log('File uploaded successfully');
                return preSignedUrl.split('?')[0];
            } else {
                console.error('Failed to upload file');
            }
        } catch (error) {
            console.error('Error uploading file to S3:', error);
        }
    };

    
    const handlePrimaryImageUpload = async () => {
        if (!primaryImageFile) return null;
        return await uploadFileToS3(primaryImageFile);
    };

    const handleAdditionalImagesUpload = async () => {
        let urls = [];
        for (const file of additionalImageFiles) {
            const imageUrl = await uploadFileToS3(file);
            if (imageUrl) {
                urls.push(imageUrl);
            }
        }
        return urls;
    };

    const handleAdditionalImagesChange = (event) => {
        setAdditionalImageFiles([...additionalImageFiles, ...Array.from(event.target.files)]);
    };

    

    return (
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Name</label>
                <input 
                    type="text" 
                    className="form-control" 
                    name="name" 
                    value={room.name} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
    <label>Type</label>
    <select
        className="form-control"
        name="type"
        value={room.type}
        onChange={handleChange}
    >
        <option value="">Select Room Type</option>
        <option value="Single">Single</option>
        <option value="Double">Double</option>
        <option value="Queen">Queen</option>
        <option value="King">King</option>
        <option value="Twin">Twin</option>
        <option value="Suite">Suite</option>
        <option value="Studio">Studio</option>
    </select>
    </div>

            <div className="form-group">
                <label>Maximum Count</label>
                <input 
                    type="number" 
                    className="form-control" 
                    name="maxCount" 
                    value={room.maxcount} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Phone Number</label>
                <input 
                    type="text" 
                    className="form-control" 
                    name="phoneNumber" 
                    value={room.phonenumber} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Rent Per Day</label>
                <input 
                    type="number" 
                    className="form-control" 
                    name="rentPerDay" 
                    value={room.rentPerDay} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Description</label>
                <textarea 
                    className="form-control" 
                    name="description" 
                    value={room.description} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group form-check">
                <input 
                    type="checkbox" 
                    className="form-check-input" 
                    name="available" 
                    checked={room.available} 
                    onChange={handleChange} 
                />
                <label className="form-check-label">Available</label>
            </div>

            <div className="form-group">
                <label>Upload Primary Image</label>
                <input 
                    type="file" 
                    className="form-control" 
                    onChange={(e) => setPrimaryImageFile(e.target.files[0])} 
                />
            </div>
            <div className="form-group">
                <label>Upload Additional Images</label>
                <input 
                    type="file" 
                    className="form-control" 
                    multiple 
                    onChange={handleAdditionalImagesChange} // Updated handler
                />
            </div>

            <button type="submit" className="btn btn-primary mt-4">Save Room</button>
        </form>
    );
};

export default RoomForm;
