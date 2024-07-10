class PasswordService {
    async generatePassword(length: Number, lower: Boolean, upper: Boolean, number: Boolean, special: Boolean) {

        const url = new URL("http://localhost:8080/api/v1/password/generate");
        const params = new URLSearchParams(url.search);

        params.append("length", length.toString());
        params.append("lower", lower.toString());
        params.append("upper", upper.toString());
        params.append("number", number.toString());
        params.append("special", special.toString());

        url.search = params.toString();

        return await fetch(url.toString(), {
            method: 'GET',
            mode: 'cors',
        })
    }
}

export default new PasswordService();