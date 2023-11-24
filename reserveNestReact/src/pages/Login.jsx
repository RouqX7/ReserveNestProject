const Login = () => {
    const [credentials, setCredentials] = useState({ username: '', password: '', email: '' });

    const handleChange = (e) => {
        setCredentials({ ...credentials, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/api/auth/login', credentials);
            console.log(response.data); // Handle successful login
        } catch (error) {
            console.error("Login failed:", error); // Handle login failure
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <input type="text" name="username" value={credentials.username} onChange={handleChange} />
            <input type="email" name="email" value={credentials.email} onChange={handleChange} />
            <input type="password" name="password" value={credentials.password} onChange={handleChange} />
            <button type="submit">Login</button>
        </form>
    );
};

export default Login;