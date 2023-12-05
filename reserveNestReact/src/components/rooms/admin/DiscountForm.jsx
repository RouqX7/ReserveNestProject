import React, { useState } from 'react';
import axios from 'axios';

const DiscountForm = ({ discountData }) => {
    const [discount, setDiscount] = useState(discountData || {
        type: '',
        discountPercentage: 0,
        description: '',
        validityPeriodStart: '',
        validityPeriodEnd: '',
        blackoutDates: '',
        termsAndConditions: ''
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setDiscount({ ...discount, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/api/discounts/create', discount);
            console.log('Discount saved:', response.data);
            // Additional actions after successful save...
        } catch (error) {
            console.error('Error saving discount:', error);
            // Handle error...
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div className="form-group">
                <label>Type</label>
                <select 
                    className="form-control" 
                    name="type" 
                    value={discount.type} 
                    onChange={handleChange}
                >
                    <option value="">Select Type</option>
                    <option value="BULK">Bulk</option>
                    <option value="EARLY_BIRD">Early Bird</option>
                    <option value="LAST_MINUTE">Last Minute</option>
                    <option value="LOYALTY">Loyalty</option>
                    <option value="FIRST_TIME_USER">First Time User</option>
                    </select>

            </div>

            <div className="form-group">
                <label>Discount Percentage</label>
                <input 
                    type="number" 
                    className="form-control" 
                    name="discountPercentage" 
                    value={discount.discountPercentage} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Description</label>
                <textarea 
                    className="form-control" 
                    name="description" 
                    value={discount.description} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Validty Period Start</label>
                <textarea 
                    className="form-control" 
                    name="validityPeriodStart" 
                    value={discount.validityPeriodStart} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Validity Period End</label>
                <textarea 
                    className="form-control" 
                    name="validityPeriodEnd" 
                    value={discount.validityPeriodEnd} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Blacked Out Dates</label>
                <textarea 
                    className="form-control" 
                    name="blackoutDates" 
                    value={discount.blackoutDates} 
                    onChange={handleChange} 
                />
            </div>

            <div className="form-group">
                <label>Terms And Condiitions</label>
                <textarea 
                    className="form-control" 
                    name="termsAndConditions" 
                    value={discount.termsAndConditions} 
                    onChange={handleChange} 
                />
            </div>

            <button type="submit" className="btn btn-primary mt-4">Save Discount</button>
        </form>
    );
};

export default DiscountForm;
