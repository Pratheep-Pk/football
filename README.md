# Football Standings Application

## Overview

The **Football Standings Application** is a full-stack solution built with **Angular** for the front-end and **Spring Boot** (Java 17) for the back-end. This application allows users to fetch football standings based on league IDs and manage the API's offline mode. The back-end integrates with an external football API to fetch real-time standings data but also supports offline functionality for when the API is unavailable. The application is containerized using **Docker** and can be deployed with a **Jenkins** pipeline.

---

## Front-End (Angular)

### Description

The front-end is built using **Angular** and communicates with the back-end through **HTTP** requests.

### Key Components

1. **FootballService**:
   - Manages all HTTP requests to the back-end.
   - Methods:
     - `getStandings(leagueId: string)`: Fetches football standings for a given league ID.
     - `updateOfflineMode(status: boolean)`: Updates the offline mode status.

2. **FootballStandingsComponent**:
   - Displays the standings and provides functionality to fetch standings for a given league.
   - Handles user inputs and displays any error messages.

### How to Run

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project folder:
    ```bash
    cd <project-folder>
    ```

3. Install the dependencies:
    ```bash
    npm install
    ```

4. Serve the application:
    ```bash
    ng serve
    ```

5. Open the application in a browser:
    ```bash
    http://localhost:4200
    ```

---

## Back-End (Spring Boot)

### Description

The back-end is built using **Spring Boot (Java 17)** and provides RESTful APIs for fetching football standings and updating the offline mode. It integrates with a third-party football API to fetch the standings. In addition, it has an offline mode feature that can be toggled.

### Key Components

1. **FootballStandingsController**:
   - Provides two main endpoints:
     - `/standings/{leagueId}`: Fetches the standings for a given league.
     - `/offline-mode/{status}`: Updates the offline mode.

2. **FootballService**:
   - Fetches data from an external football API or returns offline data if the API is down.

3. **ApiConfig**:
   - Stores the configuration properties for the external API, including the base URL and API key.

4. **RestTemplateConfig**:
   - Configures a `RestTemplate` bean for making HTTP requests to the external API.

5. **StandingsMapper**:
   - Maps the API response to internal model objects (`StandingsResponse`).

6. **GlobalExceptionHandler**:
   - Handles exceptions thrown in the application and returns appropriate responses.

### How to Run Locally

1. Clone the repository:
    ```bash
    git clone <repository-url>
    ```

2. Navigate to the project folder:
    ```bash
    cd <project-folder>
    ```

3. Build the application using Maven:
    ```bash
    mvn clean install
    ```

4. Run the application:
    ```bash
    mvn spring-boot:run
    ```

5. The application will be available on:
    ```bash
    http://localhost:8080
    ```

### API Endpoints

- **GET `/api/standings/{leagueId}`**: Fetch football standings for a specific league.
  
  **Example:**
  - URL: `/api/standings/12345`
  - Response: A list of football standings for the league.

- **PUT `/api/offline-mode/{status}`**: Update the API’s offline mode (enable/disable).
  
  **Example:**
  - URL: `/api/offline-mode/true`
  - Response: Confirms that the offline mode has been updated.

---

## Docker Setup

The application is containerized using **Docker**. The following `Dockerfile` is provided for building the Docker image and running the container.

### How to Build and Run with Docker

1. Build the Docker image:
    ```bash
    docker build -t football-standings:1.0 .
    ```

2. Run the Docker container:
    ```bash
    docker run -d -p 8080:8080 --name football-standings-container football-standings:1.0
    ```

3. The application will be available at:
    ```bash
    http://localhost:8080
    ```

---

## Jenkins Pipeline

The application also includes a **Jenkins pipeline** for continuous integration and deployment (CI/CD).

### Jenkins Pipeline Stages

1. **Checkout Code**: Checks out the code from the repository.
2. **Build**: Builds the application using Maven.
3. **Docker Build & Push**: Builds the Docker image and pushes it to a Docker registry.
4. **Deploy**: Deploys the Docker container to a local server.

---

## Configuration

Configuration for the external football API and offline mode is handled through the `application.properties` file. The following properties are used:

- `api.football.base-url`: The base URL for the external football API.
- `api.football.apiKey`: The API key for authenticating with the external API.
- `api.football.offline`: A boolean flag to toggle offline mode.

# Football Standings Service

## Design Overview

This section provides an overview of the Football Standings service, including the sequence diagram that shows the interactions between the front-end and back-end components.

## Flow Explanation
1. **User Input**: The user provides a `leagueId` on the UI.
2. **API Request**: The Angular frontend sends a request to the backend to fetch the football standings.
3. **Backend Logic**: The Spring Boot backend either fetches data from the API or provides offline data depending on the offline mode status.
4. **Response**: The backend responds with the standings data, which is displayed on the UI.
5. **Offline Mode**: If offline mode is enabled, the backend returns a predefined set of standings data.
