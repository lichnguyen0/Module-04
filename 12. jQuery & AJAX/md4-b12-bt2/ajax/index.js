let currentEditId = null;

function addNewSmartPhone() {
    let producer = $('#producer').val();
    let model = $('#model').val();
    let price = $('#price').val();
    let smartphoneData = { producer, model, price };

    if (currentEditId) {
        // Update
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "PUT",
            url: `http://localhost:8080/api/smartphones/${currentEditId}`,
            data: JSON.stringify(smartphoneData),
            success: function () {
                currentEditId = null;
                $('input[type="submit"]').val('Add');
                $('#add-smartphone')[0].reset();
                successHandler();
                $('#div-btn').show();
            }
        });
    } else {
        // Create mới
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            data: JSON.stringify(smartphoneData),
            url: "http://localhost:8080/api/smartphones",
            success: function () {
                $('#add-smartphone')[0].reset();
                successHandler();
            }
        });
    }

    event.preventDefault();
}

function successHandler() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smartphones",
        success: function (data) {
            let content = '<table id="display-list" border="1"><tr>' +
                '<th>Producer</th><th>Model</th><th>Price</th><th>Actions</th></tr>';
            for (let i = 0; i < data.length; i++) {
                content += getSmartphone(data[i]);
            }
            content += "</table>";
            $('#smartphoneList').html(content).show();
            $('#add-smartphone').hide();
            $('#title').show();
            $('#display').hide();
            $('#display-create').show();
        }
    });
}

function getSmartphone(smartphone) {
    return `<tr>
        <td>${smartphone.producer}</td>
        <td>${smartphone.model}</td>
        <td>${smartphone.price}</td>
        <td class="btn">
            <button class="editSmartphone" onclick="editSmartphone(${smartphone.id})">Edit</button>
            <button class="deleteSmartphone" onclick="deleteSmartphone(${smartphone.id})">Delete</button>
        </td>
    </tr>`;
}

function editSmartphone(id) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/smartphones",
        success: function (data) {
            const smartphone = data.find(s => s.id === id);
            if (smartphone) {
                $('#producer').val(smartphone.producer);
                $('#model').val(smartphone.model);
                $('#price').val(smartphone.price);
                $('#add-smartphone').show();
                $('#div-btn').hide();
                currentEditId = id;
                $('input[type="submit"]').val('Update');
            }
        }
    });
}

function deleteSmartphone(id) {
    $.ajax({
        type: "DELETE",
        url: `http://localhost:8080/api/smartphones/${id}`,
        success: successHandler
    });
}

function displayFormCreate() {
    $('#smartphoneList').hide();
    $('#add-smartphone').show();
    $('#div-btn').hide();
    $('#title').hide();
    currentEditId = null;
    $('input[type="submit"]').val('Add');
    $('#add-smartphone')[0].reset();
}
