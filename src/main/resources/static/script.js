$(document).ready(function () {
    loadRecords();
});

function loadRecords() {
    $.ajax({
        url: "/get-absence-records",
        method: "GET",
        success: function (data) {
            $("#records").html(data);
        }
    });
}

function openForm(recordId) {
    $.ajax({
        url: "/get-absence-form",
        method: "GET",
        data: { id: recordId },
        success: function (data) {
            $("#form").html(data);
        }
    });
}

function saveRecord() {
    var formData = $("#absenceForm").serialize();
    $.ajax({
        url: "/save-absence-record",
        method: "POST",
        data: formData,
        success: function () {
            loadRecords();
            clearForm();
        }
    });
}

function updateRecord(id) {
    var formData = $("#absenceForm").serialize();
    $.ajax({
        url: "/update-absence-record/" + id,
        method: "POST",
        data: formData,
        success: function () {
            loadRecords();
            clearForm();
        }
    });
}

function deleteRecord(id) {
    $.ajax({
        url: "/delete-absence-record/" + id,
        method: "POST",
        success: function () {
            loadRecords();
        }
    });
}

function clearForm() {
    $("#form").empty();
}
