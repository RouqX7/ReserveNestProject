import React, { useState } from "react";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Carousel from "react-bootstrap/Carousel";
import "../../components/rooms/room.css";
import { Link } from "react-router-dom";

function Room({ room, fromDate,toDate }) {
  // Check if the room object exists and has the necessary properties

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);

  return (
    <div className="row bs">
      <div className="col-md-4">
        <img src={room.primaryImageUrl} className="smallimg" alt={room.name} />
      </div>
      <div className="col-md-7">
    <h1>{room.name}</h1>
    <p>Max Count : {room.maxCount}</p>
    <p>PhoneNumber: {room.phoneNumber}</p>
    <p>Type : {room.type}</p>
    <div className="buttons">
        <Link to={`/book/${room._id}/${fromDate}/${toDate}`}>
            <button className="btn">Book Now</button>
        </Link>
        <button className="btn" onClick={handleShow}>
            View Details
        </button>
    </div>
</div>        
      <Modal show={show} onHide={handleClose} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>{room.name}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Carousel prevLabel= '' nextLabel= ''>
            {room.expandedImageUrls?.map((url, index) => (
              <Carousel.Item key={index}>
                <img
                  className="d-block w-100"
                  src={url}
                  alt={`Slide ${index}`}
                />
              </Carousel.Item>
            )) ?? []}
          </Carousel>
          <p className="mt-3">{room.description}</p>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default Room;
