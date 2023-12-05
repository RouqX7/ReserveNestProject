import React, { useEffect, useState } from 'react';
import axios from 'axios';
import DiscountForm from '../../components/rooms/admin/DiscountForm';
import DiscountList from '../../components/rooms/admin/DiscountList';

const DiscountManagement = () => {
    const [discounts, setDiscounts] = useState([]);
    const [editingDiscount, setEditingDiscount] = useState(null);

    useEffect(() => {
        loadDiscounts();
    }, []);
    
    const loadDiscounts = async () => {
        try {
            const response = await axios.get("http://localhost:8081/api/discounts/all");
            setDiscounts(response.data);
        } catch (error) {
            console.error("Failed to load discounts:", error);
        }
    };
    
    const createDiscount = async (discount) => {
        try {
            await axios.post("http://localhost:8081/api/discounts/create", discount);
            loadDiscounts(); // Reload discounts after creation
        } catch (error) {
            console.error('Error creating discount:', error);
        }
    };

    const updateDiscount = async (discount) => {
        try {
            await axios.put(`http://localhost:8081/api/discounts/update/${discount._id}`, discount);
            loadDiscounts(); // Reload discounts after update
        } catch (error) {
            console.error('Error updating discount:', error);
        }
    };

    const deleteDiscount = async (discountId) => {
        try {
            await axios.delete(`http://localhost:8081/api/discounts/delete/${discountId}`);
            loadDiscounts(); // Refresh the list after deletion
        } catch (error) {
            console.error('Error deleting discount:', error);
        }
    };

    const handleEditClick = (discount) => {
        setEditingDiscount(discount);
    };
    
    const handleDeleteClick = (discountId) => {
        deleteDiscount(discountId);
    };

    const handleCreateOrUpdateDiscount = async (discount) => {
        if (editingDiscount) {
          await updateDiscount(discount);
        } else {
          await createDiscount(discount);
        }
      };

    return (
        <div>
            <h1>Discount Management</h1>
            <DiscountForm onSave={handleCreateOrUpdateDiscount} discountData={editingDiscount} />
            <DiscountList discounts={discounts} onEdit={handleEditClick} onDelete={handleDeleteClick} />
        </div>
    );
};

export default DiscountManagement;
