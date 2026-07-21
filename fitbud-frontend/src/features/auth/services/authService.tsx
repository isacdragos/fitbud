import api from "../../../shared/services/api";
import type { LoginRequest } from "../types/LoginRequest";
import type { LoginResponse } from "../types/LoginResponse";
import type { RegisterRequest } from "../types/RegisterRequest";

export async function login(request: LoginRequest): Promise<LoginResponse> {
    const response = await api.post<LoginResponse>("/auth/login", request);
    return response.data;
}

export async function register(request: RegisterRequest): Promise<LoginResponse> {
    await api.post("/auth/register", request);
    const response = await login({ email: request.email, password: request.password });
    return response;
}