var status = document.getElementById('createStatus').value;

if (status === "failed") {
    alertify.error('Username taken');
}