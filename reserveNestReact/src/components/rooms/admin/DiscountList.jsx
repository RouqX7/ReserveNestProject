import React from 'react';

const DiscountList = ({ discounts, onEdit, onDelete }) => {
    return (
        <table className="table">
            <thead>
                <tr>
                    <th>Type</th>
                    <th>Discount Percentage</th>
                    <th>Description</th>
                    <th>Validity Start</th>
                    <th>Validity End</th>
                    <th>Blackout Dates</th>
                    <th>Terms and Conditions</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {discounts.map((discount) => (
                    <tr key={discount.id}>
                        <td>{discount.type}</td>
                        <td>{discount.discountPercentage}%</td>
                        <td>{discount.description}</td>
                        <td>{discount.validityPeriodStart}</td>
                        <td>{discount.validityPeriodEnd}</td>
                        <td>{discount.blackoutDates}</td>
                        <td>{discount.termsAndConditions}</td>
                        <td>
                            <button onClick={() => onEdit(discount)}>Edit</button>
                            <button onClick={() => onDelete(discount.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
};

export default DiscountList;
