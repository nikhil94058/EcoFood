const baseUrl = "http://localhost:8080/api/users";

// ---------------- SIGNUP ----------------
async function signup() {
    const user = {
        name: document.getElementById("signupName").value,
        email: document.getElementById("signupEmail").value,
        password: document.getElementById("signupPassword").value,
        address: document.getElementById("signupAddress").value,
        phoneNumber: document.getElementById("signupPhone").value,
        role: "donor"
    };

    const res = await fetch(`${baseUrl}/signup`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    });

    const data = await res.json();
    document.getElementById("signupMsg").innerText = "Signup successful! Your ID: " + data.id;
}

// ---------------- LOGIN ----------------
async function login() {
    const user = {
        email: document.getElementById("loginEmail").value,
        password: document.getElementById("loginPassword").value
    };

    const res = await fetch(`${baseUrl}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user)
    });

    const msg = await res.text();
    document.getElementById("loginMsg").innerText = msg;
}

// ---------------- VERIFY ----------------
async function verifyUser() {
    const userId = document.getElementById("verifyId").value;
    const res = await fetch(`${baseUrl}/verify/${userId}`, { method: "POST" });
    const msg = await res.text();
    document.getElementById("verifyMsg").innerText = msg;
}

// ---------------- NOTIFICATIONS ----------------
async function addNotification() {
    const userId = document.getElementById("notifUserId").value;
    const notif = document.getElementById("notifText").value;

    const res = await fetch(`${baseUrl}/${userId}/notifications`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(notif)
    });

    const data = await res.json();
    document.getElementById("notifMsg").innerText = "Notification added. Total: " + data.notifications.length;
}

async function clearNotifications() {
    const userId = document.getElementById("notifUserId").value;

    const res = await fetch(`${baseUrl}/${userId}/notifications`, { method: "DELETE" });
    const data = await res.json();
    document.getElementById("notifMsg").innerText = "Notifications cleared.";
}

// ---------------- VIEW ALL USERS ----------------
async function loadUsers() {
    const res = await fetch(baseUrl);
    const users = await res.json();
    const ul = document.getElementById("userList");
    ul.innerHTML = "";
    users.forEach(u => {
        const li = document.createElement("li");
        li.innerText = `${u.name} (${u.email}) - Verified: ${u.verified} - Donations: ${u.donationCount}`;
        ul.appendChild(li);
    });
}
