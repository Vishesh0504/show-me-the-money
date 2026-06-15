# Auth API Contract

Shared contract artifacts for backend and frontend.

## Files

- `openapi.yaml`: OpenAPI 3.0 contract for `/auth/signup` and `/auth/login`.
- `types.ts`: Frontend-friendly TypeScript interfaces matching the same contract.

## Endpoints

- `POST /auth/signup`
  - Request: `LoginCredentials`
  - Success: `201` with `AuthTokenResponse`
  - Errors: `400`, `409`

- `POST /auth/login`
  - Request: `LoginCredentials`
  - Success: `200` with `AuthTokenResponse`
  - Errors: `400`, `401`

## Notes

- Both endpoints return a JWT token in `{ "token": "..." }`.
- Current backend error bodies may vary depending on exception mapper; use `ErrorResponse` as a safe shape.

