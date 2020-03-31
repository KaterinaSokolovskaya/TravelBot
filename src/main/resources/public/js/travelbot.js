function getAllCities() {
    makeRequest('/travel', 'GET')
        .then(json => json.reverse().forEach(item => addItem(item)));
}

function saveCity() {
    let city = {
        name: document.getElementById('name').value,
        information: document.getElementById('information').value
    };

    makeRequest("/travel_save", "POST", city)
        .then(json => json.reverse().forEach(item => updateList(item)));
}

function updateCity() {
    let city = {
        id: document.getElementById('id_up').value,
        name: document.getElementById('name_up').value,
        information: document.getElementById('information_up').value
    };

    makeRequest("/travel_update", "POST", city)
        .then(json => json.reverse().forEach(item => updateList(item)));
}

function deleteCity() {
    let city = {
        id: document.getElementById('id_del').value,
    };

    makeRequest("/travel_delete", "POST", city)
        .then(json => json.reverse().forEach(item => updateList(item)));
}

function getInfo() {
    let city = {
        id: document.getElementById('id_up').value,
    };

    makeRequest("/travel_get", "POST", city)
        .then(json => addText(json));

}

function addText(json) {
    document.getElementById("name_up").value = json.name;
    document.getElementById("information_up").value = json.information;
}

function makeRequest(url, method, data) {
    let options = {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    return fetch(url, options)
        .then(response => response.json());
}

function updateList(item) {
    cleanInputs();
    cleanList();
    addItem(item);
}

function addItem(item) {
    let pIdElement = document.createElement('p');
    let pNameElement = document.createElement('p');
    let pInfoElement = document.createElement('p');
    let divElement = document.createElement('div');
    let element = document.getElementById('cities');
    divElement.setAttribute("class", "city");
    pIdElement.setAttribute("class", "p-id");
    pNameElement.setAttribute("class", "p-name");
    pInfoElement.setAttribute("class", "p-info");
    divElement.appendChild(pIdElement).innerText = item.id;
    divElement.appendChild(pNameElement).innerText = item.name;
    divElement.appendChild(pInfoElement).innerText = item.information;
    element.appendChild(divElement);
}

function cleanInputs() {
    document.getElementById('name').value = '';
    document.getElementById('information').value = '';
    document.getElementById('id_up').value = '';
    document.getElementById('name_up').value = '';
    document.getElementById('information_up').value = '';
    document.getElementById('id_del').value = '';
}

function cleanList() {
    let element = document.getElementById('cities');
    element.innerHTML = '';
}