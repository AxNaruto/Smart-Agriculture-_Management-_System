/**
 * Smart Agriculture Management System - Frontend Controller
 * Pure Vanilla JavaScript communicating with Spring Boot REST Endpoints
 */

const API_BASE = '/api';

document.addEventListener('DOMContentLoaded', () => {
    initTabs();
    initFertilizerModule();
    initWaterModule();
    initMandiModule();
    initPestModule();
});

/* ===================================================================
   1. TAB SWITCHING LOGIC
   =================================================================== */
function initTabs() {
    const tabButtons = document.querySelectorAll('.tab-btn');
    const tabContents = document.querySelectorAll('.tab-content');

    tabButtons.forEach(btn => {
        btn.addEventListener('click', () => {
            const targetTabId = btn.getAttribute('data-tab');

            tabButtons.forEach(b => b.classList.remove('active'));
            tabContents.forEach(c => c.classList.remove('active'));

            btn.classList.add('active');
            const activeContent = document.getElementById(targetTabId);
            if (activeContent) {
                activeContent.classList.add('active');
            }
        });
    });
}

/* ===================================================================
   2. FERTILIZER & N-P-K ADVISOR MODULE
   =================================================================== */
function initFertilizerModule() {
    const form = document.getElementById('fertilizerForm');
    const emptyState = document.getElementById('fertEmptyState');
    const resultContent = document.getElementById('fertResultContent');
    const cropBadge = document.getElementById('fertCropBadge');

    const resN = document.getElementById('resN');
    const resP = document.getElementById('resP');
    const resK = document.getElementById('resK');
    const scheduleText = document.getElementById('fertScheduleText');
    const remarksText = document.getElementById('fertRemarksText');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const cropName = document.getElementById('fertCrop').value;
        const soilType = document.getElementById('fertSoil').value;
        const acreage = parseFloat(document.getElementById('fertAcreage').value);

        if (!cropName || !soilType || isNaN(acreage) || acreage <= 0) {
            showToast('Please provide valid crop, soil type, and positive acreage.', 'error');
            return;
        }

        const payload = { cropName, soilType, acreage };

        try {
            const response = await fetch(`${API_BASE}/calculate/fertilizer`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error(`Server returned HTTP ${response.status}`);
            }

            const data = await response.json();

            // Populate Results
            cropBadge.textContent = `${data.cropName} (${data.soilType} Soil)`;
            resN.textContent = data.totalNitrogenKg;
            resP.textContent = data.totalPhosphorusKg;
            resK.textContent = data.totalPotassiumKg;
            scheduleText.textContent = data.recommendedSchedule;
            remarksText.textContent = data.remarks;

            emptyState.classList.add('hidden');
            resultContent.classList.remove('hidden');

            showToast(`Calculated dosage for ${data.acreage} acre(s) of ${data.cropName}!`, 'success');
        } catch (err) {
            console.error('Fertilizer calculation error:', err);
            showToast('Failed to calculate fertilizer: ' + err.message, 'error');
        }
    });
}

/* ===================================================================
   3. IRRIGATION WATER CALCULATOR MODULE
   =================================================================== */
function initWaterModule() {
    const form = document.getElementById('waterForm');
    const emptyState = document.getElementById('waterEmptyState');
    const resultContent = document.getElementById('waterResultContent');
    const stageBadge = document.getElementById('waterStageBadge');

    const litersVal = document.getElementById('waterLitersVal');
    const tankersVal = document.getElementById('waterTankersVal');
    const resAcreage = document.getElementById('resWaterAcreage');
    const resFactor = document.getElementById('resWaterFactor');
    const methodText = document.getElementById('waterMethodText');
    const notesText = document.getElementById('waterNotesText');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const cropName = document.getElementById('waterCrop').value;
        const acreage = parseFloat(document.getElementById('waterAcreage').value);
        const growthStage = document.getElementById('waterStage').value;

        if (!cropName || isNaN(acreage) || acreage <= 0 || !growthStage) {
            showToast('Please provide valid crop, acreage, and growth stage.', 'error');
            return;
        }

        const payload = { cropName, acreage, growthStage };

        try {
            const response = await fetch(`${API_BASE}/calculate/water`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error(`Server returned HTTP ${response.status}`);
            }

            const data = await response.json();

            stageBadge.textContent = `${data.cropName} • ${data.growthStage}`;
            litersVal.textContent = Number(data.dailyWaterLiters).toLocaleString();

            const tankers = (data.dailyWaterLiters / 5000).toFixed(1);
            tankersVal.textContent = `≈ ${tankers} Standard Water Tankers (5,000L each)`;

            resAcreage.textContent = `${data.acreage} Acre(s)`;
            resFactor.textContent = data.growthStageFactor;
            methodText.textContent = data.irrigationMethodSuggestion;
            notesText.textContent = data.notes;

            emptyState.classList.add('hidden');
            resultContent.classList.remove('hidden');

            showToast(`Computed daily water requirement: ${Number(data.dailyWaterLiters).toLocaleString()} Liters`, 'success');
        } catch (err) {
            console.error('Water calculation error:', err);
            showToast('Failed to calculate water requirement: ' + err.message, 'error');
        }
    });
}

/* ===================================================================
   4. MANDI MARKETPLACE MODULE
   =================================================================== */
let allMandiListings = [];

function initMandiModule() {
    const form = document.getElementById('mandiForm');
    const refreshBtn = document.getElementById('refreshMandiBtn');
    const searchInput = document.getElementById('mandiSearchInput');

    // Fetch listings initially
    fetchMandiListings();

    refreshBtn.addEventListener('click', () => {
        fetchMandiListings();
        showToast('Refreshed marketplace listings', 'success');
    });

    // Client-side search filter
    searchInput.addEventListener('input', (e) => {
        const query = e.target.value.toLowerCase().trim();
        if (!query) {
            renderMandiTable(allMandiListings);
            return;
        }
        const filtered = allMandiListings.filter(item => 
            (item.cropName && item.cropName.toLowerCase().includes(query)) ||
            (item.location && item.location.toLowerCase().includes(query)) ||
            (item.farmerName && item.farmerName.toLowerCase().includes(query))
        );
        renderMandiTable(filtered);
    });

    // Form submit: Post new listing
    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const farmerName = document.getElementById('mandiFarmerName').value.trim();
        const cropName = document.getElementById('mandiCropName').value.trim();
        const quantityQuintals = parseFloat(document.getElementById('mandiQuantity').value);
        const price = parseFloat(document.getElementById('mandiPrice').value);
        const location = document.getElementById('mandiLocation').value.trim();
        const phone = document.getElementById('mandiPhone').value.trim();

        if (!farmerName || !cropName || isNaN(quantityQuintals) || isNaN(price) || !location || !phone) {
            showToast('Please fill out all mandi listing fields correctly.', 'error');
            return;
        }

        const payload = { farmerName, cropName, quantityQuintals, price, location, phone };

        try {
            const response = await fetch(`${API_BASE}/mandi`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!response.ok) {
                throw new Error(`Server returned HTTP ${response.status}`);
            }

            showToast('Crop listing posted successfully to Mandi!', 'success');
            form.reset();
            fetchMandiListings();
        } catch (err) {
            console.error('Failed to post mandi listing:', err);
            showToast('Error creating listing: ' + err.message, 'error');
        }
    });
}

async function fetchMandiListings() {
    const tbody = document.getElementById('mandiTableBody');
    const statMandiCount = document.getElementById('statMandiCount');

    try {
        const response = await fetch(`${API_BASE}/mandi`);
        if (!response.ok) throw new Error('Failed to fetch listings');

        allMandiListings = await response.json();
        renderMandiTable(allMandiListings);
        statMandiCount.textContent = `${allMandiListings.length} Active Listings`;
    } catch (err) {
        console.error('Error fetching mandi listings:', err);
        tbody.innerHTML = `<tr><td colspan="8" class="text-center" style="color: var(--danger); padding: 1.5rem;">
            Unable to connect to MySQL Mandi directory. Make sure Spring Boot backend is active.
        </td></tr>`;
        statMandiCount.textContent = 'Offline';
    }
}

function renderMandiTable(listings) {
    const tbody = document.getElementById('mandiTableBody');

    if (!listings || listings.length === 0) {
        tbody.innerHTML = `<tr><td colspan="8" class="text-center" style="padding: 2rem; color: var(--text-muted);">
            No crop listings found matching your search.
        </td></tr>`;
        return;
    }

    tbody.innerHTML = listings.map(item => `
        <tr>
            <td><strong>#${item.id}</strong></td>
            <td>${escapeHtml(item.farmerName)}</td>
            <td><strong>${escapeHtml(item.cropName)}</strong></td>
            <td>${item.quantityQuintals} Qtl</td>
            <td><span class="price-tag">₹ ${Number(item.price).toLocaleString()} / Qtl</span></td>
            <td>${escapeHtml(item.location)}</td>
            <td><span class="phone-tag">📞 ${escapeHtml(item.phone)}</span></td>
            <td>
                <button class="btn-danger-sm" onclick="deleteMandiListing(${item.id})">
                    🗑️ Delete
                </button>
            </td>
        </tr>
    `).join('');
}

window.deleteMandiListing = async function(id) {
    if (!confirm(`Are you sure you want to remove listing #${id}?`)) {
        return;
    }

    try {
        const response = await fetch(`${API_BASE}/mandi/${id}`, {
            method: 'DELETE'
        });

        if (!response.ok) {
            throw new Error(`Delete failed with HTTP ${response.status}`);
        }

        showToast(`Listing #${id} deleted successfully.`, 'success');
        fetchMandiListings();
    } catch (err) {
        console.error('Delete listing error:', err);
        showToast('Error deleting listing: ' + err.message, 'error');
    }
};

/* ===================================================================
   5. PEST ADVISORY MODULE
   =================================================================== */
function initPestModule() {
    const filterSelect = document.getElementById('pestCropFilter');

    fetchPestAdvice('All');

    filterSelect.addEventListener('change', (e) => {
        fetchPestAdvice(e.target.value);
    });
}

async function fetchPestAdvice(crop) {
    const container = document.getElementById('pestCardsContainer');
    const statPestCount = document.getElementById('statPestCount');

    try {
        let url = `${API_BASE}/pest`;
        if (crop && crop !== 'All') {
            url += `?crop=${encodeURIComponent(crop)}`;
        }

        const response = await fetch(url);
        if (!response.ok) throw new Error('Failed to load pest remedies');

        const advices = await response.json();

        if (crop === 'All') {
            statPestCount.textContent = `${advices.length} Remedies`;
        }

        renderPestCards(advices);
    } catch (err) {
        console.error('Pest advice fetch error:', err);
        container.innerHTML = `<div class="empty-state" style="color: var(--danger);">
            Unable to load pest remedies from database.
        </div>`;
    }
}

function renderPestCards(advices) {
    const container = document.getElementById('pestCardsContainer');

    if (!advices || advices.length === 0) {
        container.innerHTML = `
            <div class="empty-state" style="grid-column: 1 / -1;">
                <div class="empty-icon">🛡️</div>
                <p>No pest symptoms recorded for this selection.</p>
            </div>
        `;
        return;
    }

    container.innerHTML = advices.map(item => `
        <div class="pest-card">
            <div class="pest-header">
                <span class="pest-crop-badge">🌾 ${escapeHtml(item.cropName)}</span>
                <span style="font-size: 0.75rem; color: var(--text-muted); font-weight: 600;">ID #${item.id}</span>
            </div>
            <div class="pest-symptom">
                ⚠️ ${escapeHtml(item.symptom)}
            </div>
            <div class="remedy-box remedy-bio">
                <h4>🌿 Biological / Organic Control:</h4>
                <p>${escapeHtml(item.bioRemedy)}</p>
            </div>
            <div class="remedy-box remedy-chemical">
                <h4>🧪 Chemical Spray Dosage:</h4>
                <p>${escapeHtml(item.chemicalDosage)}</p>
            </div>
        </div>
    `).join('');
}

/* ===================================================================
   6. TOAST NOTIFICATIONS & UTILS
   =================================================================== */
function showToast(message, type = 'success') {
    const container = document.getElementById('toastContainer');
    const toast = document.createElement('div');
    toast.className = `toast toast-${type}`;
    toast.innerHTML = `
        <span>${type === 'success' ? '✅' : '⚠️'}</span>
        <span>${escapeHtml(message)}</span>
    `;

    container.appendChild(toast);

    setTimeout(() => {
        toast.style.opacity = '0';
        toast.style.transform = 'translateY(10px)';
        toast.style.transition = 'all 0.3s ease-out';
        setTimeout(() => toast.remove(), 300);
    }, 4000);
}

function escapeHtml(text) {
    if (!text) return '';
    return String(text)
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#039;');
}
