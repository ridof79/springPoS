var status = document.getElementById('status').value;

if (status === 'insufficientCash') {
	alertify.error('Please ensure that the cash amount is sufficient for this transaction.');
}

function togglePaymentOptions() {
	var paymentForm = document.getElementById("paymentForm");
	var isFormVisible = window.getComputedStyle(paymentForm)
		.getPropertyValue('display');

	if (isFormVisible === "none") {
		paymentForm.style.display = "block";
	} else {
		paymentForm.style.display = "none";
	}
}

function togglePaymentOption() {
	var cashInput = document.getElementById("cashInput");
	var cashAmount = document.getElementById("cashAmount");
	var cashRadio = document.getElementById("cashRadio");

	if (cashRadio.checked) {
		cashInput.classList.remove("is-invalid");
		cashAmount.removeAttribute("disabled");
	} else {
		cashInput.classList.add("is-invalid");
		cashAmount.setAttribute("disabled", "disabled");
	}
}

function confirmPayment() {
	var paymentMethod = document
		.querySelector('input[name="paymentMethod"]:checked').value;

	if (paymentMethod === 'cash') {
		var cashAmount = document.getElementById("cashAmount").value;
	}

	var formAction = "sales/submit";
	if (paymentMethod === 'qris') {
		formAction += "/qris";
	} else if (paymentMethod === 'cash') {
		formAction += "/cash";
	}

	document.getElementById("paymentForm").action = formAction;
	document.getElementById("paymentForm").submit();
}



