class AuthService {
    async register(username: string, email: string, password: string) {
        return await fetch('http://localhost:8080/api/v1/auth/register', {
            method: 'POST',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: username,
                email: email,
                password: password
            })
        })
    }

    async login(username: string, password: string) {
        return await fetch('http://localhost:8080/api/v1/auth/login', {
            method: 'POST',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username: username,
                password: password
            })
        })
    }
}

export default new AuthService();