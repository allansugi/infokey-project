const accounts = [
    {
      "account_name": "Example Corp",
      "username_email": "example@example.com"
    },
    {
      "account_name": "Sample LLC",
      "username_email": "sample_user"
    },
    {
      "account_name": "Test Company",
      "username_email": "test@test.com"
    }
]

export interface AccountDetail {
    account_name: String;
    username_email: String;
}

export interface AccountDetails {
    accounts: AccountDetail[];
}

export function accountLoader() {
    return { accounts };
}