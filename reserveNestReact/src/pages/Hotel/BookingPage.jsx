import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import Loader from "../../components/alerts/Loader";
import Error from "../../components/alerts/Error";
import BookingForm from "../../components/booking/BookingForm";
import BookingList from "../../components/booking/BookingList";
import { useNavigate } from "react-router-dom";

function BookingPage() {
  const [room, setRoom] = useState(null);
  const [totalDays, setTotalDays] = useState(0);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate(); // make sure to import useNavigate from react-router-dom
  const [error, setError] = useState(false);
  const [showConfirmationModal, setShowConfirmationModal] = useState(false);
  const { roomid, fromDate, toDate } = useParams(); // Use the useParams hook to get the room ID
  const [bookingDetails, setBookingDetails] = useState({
    specialRequests: "",
    totalAmount: 0,
    checkInDate: fromDate,
    checkOutDate: toDate,
    totalDays: totalDays,
    status: "booked",
    createdAt: new Date().toISOString(),
    updatedAt: new Date().toISOString(),
    // Add-Ons
    isBreakfastIncluded: false,
    isAirportPickupIncluded: false,
    isRoomUpgrade: false,
    isLateCheckout: false,
    hasSpecialAmenities: false,
    hasReservedParking: false,
    hasSpaAccess: false,
    hasDiningOptions: false,
  });

  const [totalAmount, setTotalAmount] = useState(0);
  const [appliedDiscount, setAppliedDiscount] = useState(null);
  const [discounts, setDiscounts] = useState([]);
  const [addOnPrices, setAddOnPrices] = useState({});
  const calculateTotalDays = (checkInDate, checkOutDate) => {
    const startDate = new Date(checkInDate);
    const endDate = new Date(checkOutDate);
    return Math.ceil((endDate - startDate) / (1000 * 60 * 60 * 24));
  };

  useEffect(() => {
    const fetchAddOnPrices = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8081/api/addons/prices"
        );
        setAddOnPrices(response.data); // Update state with the total add-on price
      } catch (error) {
        console.error("Error fetching add-on prices:", error);
      }
    };

    fetchAddOnPrices();
  }, []);

  useEffect(() => {
    const fetchDiscounts = async () => {
      const storedData = localStorage.getItem("customerDetails");
      if (!storedData) {
        console.error("No customer details found in localStorage.");
        // Optionally redirect to login or show an error message
        return;
      }

      let customerDetails;
      try {
        customerDetails = JSON.parse(storedData);
      } catch (error) {
        console.error(
          "Error parsing customer details from localStorage:",
          error
        );
        // Handle the error appropriately
        return;
      }

      const customerId = customerDetails ? customerDetails.customerId : null;
      if (customerId && bookingDetails) {
        try {
          const response = await axios.post(
            `http://localhost:8081/api/discounts/customer/${customerId}/discounts`,
            bookingDetails
          );
          setDiscounts(response.data);
        } catch (error) {
          console.error("Error fetching discounts:", error);
        }
      }
    };

    fetchDiscounts();
  }, [bookingDetails]); // Add bookingDetails as a dependency

  const createBookingRequestPayload = (customerId) => {
    const formattedCheckInDate = new Date(bookingDetails.checkInDate).toISOString();
    const formattedCheckOutDate = new Date(bookingDetails.checkOutDate).toISOString();

    console.log("Formatted Check-In Date:", formattedCheckInDate);
    console.log("Formatted Check-Out Date:", formattedCheckOutDate);

    const calculatedTotalDays = calculateTotalDays(formattedCheckInDate, formattedCheckOutDate);

    const payload = {
        customerId: customerId,
        roomId: roomid,
        checkInDate: formattedCheckInDate,
        checkOutDate: formattedCheckOutDate,
        totalDays: calculatedTotalDays,
        totalAmount: totalAmount,
        status: "booked",
        specialRequests: bookingDetails.specialRequests,
        // Add-Ons
        isBreakfastIncluded: bookingDetails.isBreakfastIncluded,
        isAirportPickupIncluded: bookingDetails.isAirportPickupIncluded,
        isRoomUpgrade: bookingDetails.isRoomUpgrade,
        isLateCheckout: bookingDetails.isLateCheckout,
        hasSpecialAmenities: bookingDetails.hasSpecialAmenities,
        hasReservedParking: bookingDetails.hasReservedParking,
        hasSpaAccess: bookingDetails.hasSpaAccess,
        hasDiningOptions: bookingDetails.hasDiningOptions,
    };

    console.log("Payload:", payload);
    return payload;
};


const handleAddToBooking = (selectedAddOns, specialRequests) => {
  const newTotalAmount = calculateTotalAmount(selectedAddOns, specialRequests);
  setTotalAmount(newTotalAmount); // Set the new total amount
  setBookingDetails(prevDetails => ({
    ...prevDetails,
    ...selectedAddOns,
    specialRequests: specialRequests,
    totalAmount: newTotalAmount // Update the total amount in booking details
  }));
};

const calculateTotalAmount = (selectedAddOns, specialRequests) => {
  if (!room) return 0;

  const addOnTotal = Object.entries(selectedAddOns).reduce((total, [key, isSelected]) => {
    const addOnKey = key.replace(/^(is|has)/, '').charAt(0).toLowerCase() + key.slice(3);
    return isSelected && addOnPrices[addOnKey] ? total + addOnPrices[addOnKey] : total;
  }, 0);

  return room.rentPerDay * totalDays + addOnTotal;
};




  const handleConfirmBooking = () => {
    setShowConfirmationModal(true);
  };

  const confirmBooking = async () => {
    const storedCustomerDetails = localStorage.getItem("customerDetails");
    if (!storedCustomerDetails) {
        console.error("No customer details found in local storage.");
        setError("Customer details are missing. Please log in again.");
        return;
    }

    const customerDetails = JSON.parse(storedCustomerDetails);
    const customerId = customerDetails ? customerDetails.id : null;

    if (!customerId || !roomid) {
        console.error("Missing customer ID or Room ID");
        setError("Booking cannot be processed without customer or room details.");
        return;
    }

    const bookingRequestPayload = createBookingRequestPayload(customerId);

    try {
      let response = await axios.post(
          "http://localhost:8081/api/bookings/calculate-price",
          bookingRequestPayload
      );
      if (response.data) {
          setTotalAmount(response.data.totalPrice); // Update the total amount state
          setAppliedDiscount(response.data.appliedDiscounts); // Assuming this is your state for discounts
          setBookingDetails(prevDetails => ({
              ...prevDetails,
              totalAmount: response.data.totalPrice, // Update the total amount in booking details
              appliedDiscounts: response.data.appliedDiscounts, // Update the applied discounts
          }));
  
          // Now confirm the booking
          response = await axios.post(
              "http://localhost:8081/api/bookings/confirm",
              {
                bookingRequestPayload,
                totalAmount: response.data.totalPrice // Use the new total price
              }

          );
          console.log("Booking confirmed:", response.data);
  
          // // Navigate to confirmation page with booking details
          // navigate("/booking-confirmation", {
          //     state: { bookingDetails: response.data },
          // });
      } else {
          throw new Error('Invalid response data');
      }
  } catch (error) {
      console.error("Error during booking process:", error);
      // Additional error handling as needed...
  }
};

  
  
  
  
  useEffect(() => {
    setBookingDetails(prevDetails => ({
        ...prevDetails,
        totalAmount: totalAmount,
        appliedDiscount: appliedDiscount,
    }));
  }, [totalAmount, appliedDiscount]);


    const validateDates = () => {
      const start = new Date(fromDate);
      const end = new Date(toDate);
    
      if (start instanceof Date && !isNaN(start) && end instanceof Date && !isNaN(end)) {
        if (end >= start) {
          const days = calculateTotalDays(start.toISOString(), end.toISOString());
          setTotalDays(days);
          // Update total amount when dates change
          calculateTotalAmount(bookingDetails, bookingDetails.specialRequests);
        } else {
          setError(true);
        }
      } else {
        setError(true);
      }
    };

  useEffect(() => {
    validateDates();
  }, [fromDate, toDate]);

  useEffect(() => {
    const fetchRoomById = async () => {
      try {
        setLoading(true);
        console.log("Room ID:", roomid);
        const response = await axios.get(
          `http://localhost:8081/api/rooms/search/${roomid}`
        );
        setRoom(response.data);
        console.log("Fetched Room Data:", response.data);
        setLoading(false);
      } catch (fetchError) {
        console.error("Error fetching room:", fetchError);
        setError(true);
      } finally {
        setLoading(false);
      }
    };

    if (roomid) {
      fetchRoomById();
    } else {
      setError(true);
      setLoading(false);
    }
  }, [roomid]);


  if (loading) {
    return <Loader />;
  }

  if (error) {
    return <Error />;
  }

  if (!room) {
    return <h1>Room not found.</h1>;
  }

  return (
    <div className="m-5">
      <div className="row justify-content-center mt-5 bs">
        <div className="col-md-5">
          <h1>{room.name}</h1>
          <img src={room.primaryImageUrl} className="bigimg" alt={room.name} />
        </div>

        <div className="col-md-6">
          <div style={{ textAlign: "right" }}>
            <h1>Booking Details</h1>
            <hr />
            <b>
              <p>Name: {room.name}</p>
              <p>From Date: {fromDate}</p>
              <p>To Date: {toDate}</p>
              <p>Max Count: {room.maxCount}</p>
            </b>
          </div>
          <BookingForm room={room} onSubmit={handleAddToBooking} />
          <BookingList
            bookingDetails={bookingDetails}
            totalAmount={totalAmount}
          />
        </div>

        <div className="col-md-6" style={{ textAlign: "right" }}>
          <h1>Amount</h1>
          <hr />
          <p>Total Days: {totalDays}</p>
          <p>Rent Per day: {room.rentPerDay}</p>
          <p>Total Amount: ${totalAmount}</p>
          <div style={{ float: "right" }}>
            <button className="btn btn-primary" onClick={handleConfirmBooking}>
              Confirm Booking
            </button>
            {showConfirmationModal && (
              <div className="confirmation-modal">
                <h2>Booking Summary</h2>
                <p>Final Price: ${totalAmount}</p>
                {appliedDiscount && (
                  <div>
                    <h3>Discounts Applied</h3>
                    {appliedDiscount.map((discount, index) => (
                      <p key={index}>
                        {discount.description} - Discounted Amount: $
                        {discount.amountApplied}
                      </p>
                    ))}
                  </div>
                )}
                <button onClick={confirmBooking}>Pay Now</button>
                <button onClick={() => setShowConfirmationModal(false)}>
                  Cancel
                </button>
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}


export default BookingPage;
