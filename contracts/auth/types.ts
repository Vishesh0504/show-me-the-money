export interface LoginCredentials {
  email: string;
  password: string;
}

export interface AuthTokenResponse {
  token: string;
}

export interface ErrorResponse {
  message?: string;
  [key: string]: unknown;
}

