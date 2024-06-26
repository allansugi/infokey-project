class VaultService {

    authType = "Bearer ";

    async getAccountItems(token: string) {
        return await fetch("http://localhost:8080/api/v1/vaults", {
            method: 'GET',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
                "Authorization": this.authType + token
            },
        })
    }

    async createNewAccountItem(token: string, name: string, username: string, password: string) {
        return await fetch("http://localhost:8080/api/v1/vaults", {
            method: 'POST',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
                "Authorization": this.authType + token
            },
            body: JSON.stringify({
                name: name,
                username: username,
                password: password
            })
        })
    }

    async updateAccountItem(token: string, name: string, username: string, password: string) {
        return await fetch("http://localhost:8080/api/v1/vaults", {
            method: 'PUT',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
                "Authorization": this.authType + token
            },
            body: JSON.stringify({
                name: name,
                username: username,
                password: password
            })
        })
    }

    async readAccountItem(token: string, id: string) {
        return await fetch(`http://localhost:8080/api/v1/vaults/${id}`, {
            method: 'GET',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
                "Authorization": this.authType + token
            }
        })
    }

    async deleteAccountItem(token: string, id: string) {
        return await fetch(`http://localhost:8080/api/v1/vaults/${id}`, {
            method: 'DELETE',
            mode: 'cors',
            headers: {
                "Content-Type": "application/json",
                "Authorization": this.authType + token
            }
        })
    }
}