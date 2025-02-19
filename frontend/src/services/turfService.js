export async function getTurfs() {
    const response = await fetch("http://localhost:8080/api/turfs");
    return response.json();
  }
  
  export async function getOwnerTurfs() {
    const response = await fetch("http://localhost:8080/api/turfs/owner");
    return response.json();
  }
  