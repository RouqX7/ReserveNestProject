import React, { useState, useEffect } from "react";
import axios from "axios";
import '../../pages/Hotel/hotelDashboard.css'
import Room from "../../components/rooms/Room";
import Navbar from "../../components/navbar/Navbar";
import Loader from "../../components/alerts/Loader";
import Error from "../../components/alerts/Error";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import moment from "moment/moment";

function HotelDashboard() {
  const [rooms, setRooms] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(false);
  const [fromDate, setFromDate] = useState(null);
  const [toDate, setToDate] = useState(null);
  const parsedFromDate = moment(fromDate).format('DD-MM-YYYY');
  const parsedToDate = moment(toDate).format('DD-MM-YYYY');
  
console.log("Parsed Start Date:", parsedFromDate);
console.log("Parsed End Date:", parsedToDate);

const [customerDetails, setCustomerDetails] = useState(null);

useEffect(() => {
  try {
    const storedDetails = localStorage.getItem("customerDetails");
    if (storedDetails) {
      const parsedDetails = JSON.parse(storedDetails);
      setCustomerDetails(parsedDetails);
    } else {
      console.log("No customer details found in local storage");
      // Handle the case where no customer details are available
    }
  } catch (error) {
    console.error("Error parsing customer details from localStorage:", error);
    // Handle the error case
  }
}, []);


  useEffect(() => {
    const fetchRooms = async () => {
      try {
        setLoading(true);
        const response = await axios.get("http://localhost:8081/api/rooms/all");
        setRooms(response.data);
        setLoading(false);
      } catch (error) {
        setError('Failed to load rooms. Please try again.'); // More descriptive error message
      } finally {
        setLoading(false);
      }
    };

    fetchRooms();
  }, [customerDetails]);


  

  // Check if date range is valid
  const isDateRangeValid = () => {
    if (!fromDate || !toDate) {
      return true; // Assume valid if either date is not set
    }
    return moment(toDate).isSameOrAfter(fromDate);
  };

  return (
    <div className="container">
      <Navbar />
      <div className="row mt-5">
        <div className="col-md-12">
          <div className="date-picker-container">
            <label>From Date:</label>
            <DatePicker selected={fromDate} onChange={(date) => setFromDate(date)} />

            <label>To Date:</label>
            <DatePicker selected={toDate} onChange={(date) => setToDate(date)} />
          </div>

          {!isDateRangeValid() && (
            <div className="alert alert-warning mt-3">
              Please select a valid date range.
            </div>
          )}
        </div>

        <div className="row justify-content-center mt-5">
          {loading ? (
            <Loader />
          ) : error ? (
            <Error />
          ) : (
            rooms.map((room) => (
              <div className="col-md-4 mt-3" key={room.id}>
                <Room room={room} fromDate={fromDate} toDate={toDate} />
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default HotelDashboard;
