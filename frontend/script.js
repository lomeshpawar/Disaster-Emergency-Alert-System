// ================================
// DISASTER ALERT SYSTEM JAVASCRIPT
// ================================

function showPopup() {
    const popup = document.createElement("div");
    popup.classList.add("custom-popup");
    popup.innerHTML = `
        <div class="popup-content">
            <i class="fa-solid fa-triangle-exclamation"></i>
            <h2>Emergency Alerts Activated</h2>
            <p>You will now receive the latest disaster and emergency notifications.</p>
            <button onclick="closePopup()">OK</button>
        </div>`;
    document.body.appendChild(popup);
}

function closePopup() {
    const popup = document.querySelector(".custom-popup");
    if (popup) popup.remove();
}

const navLinks = document.querySelectorAll("nav a");
navLinks.forEach(link => {
    link.addEventListener("click", function () {
        navLinks.forEach(item => item.classList.remove("active"));
        this.classList.add("active");
    });
});

const buttons = document.querySelectorAll("button");
buttons.forEach(button => {
    button.addEventListener("click", function () {
        button.style.transform = "scale(0.95)";
        setTimeout(() => { button.style.transform = "scale(1)"; }, 150);
    });
});

const cards = document.querySelectorAll(".card");
cards.forEach(card => {
    card.addEventListener("mouseenter", () => {
        card.style.boxShadow = "0 15px 35px rgba(0,0,0,0.2)";
    });
    card.addEventListener("mouseleave", () => {
        card.style.boxShadow = "0 8px 25px rgba(0,0,0,0.08)";
    });
});

// ---------- API INTEGRATION ----------
// Railway production backend
const API_BASE_URL = 'https://disaster-backend-production-1250.up.railway.app';

function escapeHtml(text) {
    if (!text) return '';
    return String(text)
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/\"/g, "&quot;")
        .replace(/'/g, "&#039;");
}

function getRiskClass(category) {
    if (!category) return 'low';
    const cat = category.toLowerCase();
    if (cat.includes('fire') || cat.includes('earthquake')) return 'critical';
    if (cat.includes('flood') || cat.includes('medical')) return 'high';
    if (cat.includes('storm')) return 'medium';
    return 'low';
}

function getRiskLabel(category) {
    if (!category) return 'Low Risk';
    const cat = category.toLowerCase();
    if (cat.includes('fire') || cat.includes('earthquake')) return 'Critical';
    if (cat.includes('flood') || cat.includes('medical')) return 'High Risk';
    if (cat.includes('storm')) return 'Medium Risk';
    return 'Low Risk';
}

function getCategoryIcon(category) {
    if (!category) return 'fa-solid fa-triangle-exclamation';
    const cat = category.toLowerCase();
    if (cat.includes('flood')) return 'fa-solid fa-water';
    if (cat.includes('fire')) return 'fa-solid fa-fire';
    if (cat.includes('earthquake')) return 'fa-solid fa-house-crack';
    if (cat.includes('storm')) return 'fa-solid fa-wind';
    if (cat.includes('medical')) return 'fa-solid fa-truck-medical';
    return 'fa-solid fa-triangle-exclamation';
}

async function fetchAlerts() {
    try {
        const response = await fetch(`${API_BASE_URL}/alerts`);
        if (!response.ok) throw new Error('Server responded with an error');
        const alerts = await response.json();
        renderAlerts(alerts);
    } catch (error) {
        console.error('Error fetching alerts:', error);
        showError('Could not load active emergency alerts from server.');
    }
}

function showError(msg) {
    const list = document.getElementById('alertsList') || document.getElementById('homeAlertsList') || document.getElementById('adminAlertsList');
    if (list) list.innerHTML = `<p style="text-align: center; color: #ff3333;"><i class="fa-solid fa-circle-exclamation"></i> ${msg}</p>`;
}

function renderAlerts(alerts) {
    const alertsList = document.getElementById('alertsList');
    const homeAlertsList = document.getElementById('homeAlertsList');
    const adminAlertsList = document.getElementById('adminAlertsList');

    if (alertsList) {
        alertsList.innerHTML = alerts.length === 0
            ? '<p style="text-align: center; color: #666;">No active emergency alerts at this time.</p>'
            : alerts.map(alert => `
                <div class="alert-card">
                    <div class="alert-icon"><i class="${getCategoryIcon(alert.category)}"></i></div>
                    <div class="alert-content">
                        <div class="alert-top">
                            <h3>${escapeHtml(alert.title)}</h3>
                            <span class="alert-status ${getRiskClass(alert.category)}">${getRiskLabel(alert.category)}</span>
                        </div>
                        <p>${escapeHtml(alert.description)}</p>
                        <div class="alert-info"><span><i class="fa-solid fa-location-dot"></i> ${escapeHtml(alert.location)}</span></div>
                    </div>
                </div>`).join('');
    }

    if (homeAlertsList) {
        homeAlertsList.innerHTML = alerts.length === 0
            ? '<p style="text-align: center; color: #666;">No active emergency alerts.</p>'
            : alerts.slice(0, 3).map(alert => `
                <div class="recent-alert">
                    <i class="${getCategoryIcon(alert.category)}"></i>
                    <div><h3>${escapeHtml(alert.title)} - ${escapeHtml(alert.location)}</h3><p>${escapeHtml(alert.description)}</p></div>
                </div>`).join('');
    }

    if (adminAlertsList) {
        adminAlertsList.innerHTML = alerts.length === 0
            ? '<p style="text-align: center; color: #666;">No active emergency alerts.</p>'
            : alerts.map(alert => `
                <div class="recent-alert" style="display: flex; justify-content: space-between; align-items: center;">
                    <div style="display: flex; align-items: center; gap: 15px;">
                        <i class="${getCategoryIcon(alert.category)}"></i>
                        <div><h3>${escapeHtml(alert.title)} [${escapeHtml(alert.category)}]</h3><p>${escapeHtml(alert.description)} (Location: ${escapeHtml(alert.location)})</p></div>
                    </div>
                    <button onclick="deleteAlert(${alert.id})" style="background: var(--primary); padding: 8px 15px; font-size: 0.9rem; margin: 0; min-width: auto; height: auto;"><i class="fa-solid fa-trash"></i> Delete</button>
                </div>`).join('');
    }
}

async function addAlert(alertData) {
    try {
        const response = await fetch(`${API_BASE_URL}/alerts`, {
            method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(alertData)
        });
        if (!response.ok) throw new Error('Server error when publishing alert');
        showSuccessMessage('Emergency Alert Published Successfully');
        fetchAlerts();
        document.getElementById('alertForm').reset();
    } catch (error) {
        console.error('Error adding alert:', error);
        alert('Failed to publish emergency alert.');
    }
}

async function deleteAlert(id) {
    if (!confirm('Are you sure you want to delete this alert?')) return;
    try {
        const response = await fetch(`${API_BASE_URL}/alerts/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Server error when deleting alert');
        showSuccessMessage('Alert Deleted Successfully');
        fetchAlerts();
    } catch (error) {
        console.error('Error deleting alert:', error);
        alert('Failed to delete alert.');
    }
}

async function submitReport(reportData) {
    try {
        const response = await fetch(`${API_BASE_URL}/reports`, {
            method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(reportData)
        });
        if (!response.ok) throw new Error('Server error when submitting report');
        showSuccessMessage('Emergency Report Submitted Successfully');
        document.getElementById('reportForm').reset();
    } catch (error) {
        console.error('Error submitting report:', error);
        alert('Failed to submit emergency report.');
    }
}

function showSuccessMessage(message = "Report Submitted Successfully") {
    const success = document.createElement("div");
    success.classList.add("success-message");
    success.innerHTML = `<i class="fa-solid fa-circle-check"></i> ${escapeHtml(message)}`;
    document.body.appendChild(success);
    setTimeout(() => success.remove(), 3000);
}

document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementById('alertsList') || document.getElementById('homeAlertsList') || document.getElementById('adminAlertsList')) fetchAlerts();

    const alertForm = document.getElementById('alertForm');
    if (alertForm) {
        alertForm.addEventListener('submit', (e) => {
            e.preventDefault();
            addAlert({
                title: document.getElementById('alertTitle').value.trim(),
                location: document.getElementById('alertLocation').value.trim(),
                description: document.getElementById('alertDescription').value.trim(),
                category: document.getElementById('alertCategory').value
            });
        });
    }

    const reportForm = document.getElementById('reportForm');
    if (reportForm) {
        reportForm.addEventListener('submit', (e) => {
            e.preventDefault();
            submitReport({
                name: document.getElementById('reportName').value.trim(),
                message: document.getElementById('reportMessage').value.trim()
            });
        });
    }
});

window.addEventListener("scroll", () => {
    const cards = document.querySelectorAll(".card, .tip, .alert-card");
    cards.forEach(card => {
        if (card.getBoundingClientRect().top < window.innerHeight / 1.2) {
            card.style.opacity = "1";
            card.style.transform = "translateY(0)";
        }
    });
});

document.querySelectorAll(".card, .tip, .alert-card").forEach(item => {
    item.style.opacity = "0";
    item.style.transform = "translateY(40px)";
    item.style.transition = "all 0.6s ease";
});

function updateDateTime() {
    const dateContainer = document.getElementById("datetime");
    if (dateContainer) dateContainer.innerHTML = new Date().toLocaleString();
}
setInterval(updateDateTime, 1000);

function playSiren() {
    const audio = new Audio("https://www.soundjay.com/misc/sounds/bell-ringing-05.mp3");
    audio.play();
}
