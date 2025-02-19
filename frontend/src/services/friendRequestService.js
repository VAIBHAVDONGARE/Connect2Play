export async function getFriendRequests() {
    const response = await fetch("http://localhost:8080/api/friend-requests");
    return response.json();
  }
  