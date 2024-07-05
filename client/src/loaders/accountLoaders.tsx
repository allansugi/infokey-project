import { HTTPStatus } from "../helpers/httpstatus";
import VaultService from "../service/VaultService";

export interface AccountDetail {
    id: string
    name: string;
    username: string;
}

export interface AccountItemDetail {
    id: string
    name: string;
    username: string;
    password: string;
}

export interface AccountDetails {
    items: AccountDetail[];
}

export interface editAccountParams {
    accountId: string;
}

export async function accountLoader(): Promise<AccountDetails> {
    const token = sessionStorage.getItem("token");
    
    if (token !== null) {
        const response = await VaultService.getAccountItems(token);
        if (response.status === HTTPStatus.OK) {
            return await response.json();
        }
    }

    return { items: [] };
}

export async function accountItemLoader({ params }: { params: any }): Promise<AccountItemDetail | null> {
    const token = sessionStorage.getItem("token");
    if (token !== null) {
        const response = await VaultService.readAccountItem(token, params.accountId);
        if (response.status === HTTPStatus.OK) {
            return await response.json();
        }
    }

    return null;
}