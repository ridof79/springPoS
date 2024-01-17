var status = document.getElementById('status').value;

if (status === 'duplicate') {
    alertify.error('Item with this Code and/or Description already exists.');
}