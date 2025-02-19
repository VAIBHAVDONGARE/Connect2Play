export async function getTeamRequests() {
    const response = await fetch("http://localhost:8080/api/team-requests");
    return response.json();
  }
  