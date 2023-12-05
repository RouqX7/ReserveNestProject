import axios from 'axios';

const requestPresignedUrl = async (fileName) => {
    console.log("Requesting URL for file:", fileName); // Log the file name
    try {
        const response = await axios.post('http://localhost:8081/api/s3/generate-presigned-url', { fileName });
        console.log("Pre-signed URL:", response.data); // Log the response
        return response.data;
    } catch (error) {
        console.error('Error requesting pre-signed URL:', error);
        return null;
    }
};

const uploadFileToS3 = async (file) => {
    const preSignedUrl = await requestPresignedUrl(file.name);

    if (!preSignedUrl) {
        console.error('Unable to get pre-signed URL');
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

