window.onload = function(){

    renderItemChart(itemData);
    renderCashierChart(cashierData);
    renderPaymentChart(paymentData);

}

function renderItemChart(data){
    var chart = new CanvasJS.Chart("itemChartContainer", {
        theme: "light2",
        backgroundColor: "rgba(0,0,0,0)",
        animationEnabled: true,
        title: {
            text: "Item Sold Percentage"
        },
        data: [{
            type: "pie",
            startAngle: 240,
            yValueFormatString: "##0.00\"%\"",
            indexLabel: "{label} {y}",
            dataPoints: data
        }]
    });
    chart.render();
}

function renderCashierChart(data){
    var chart = new CanvasJS.Chart("cashierChartContainer", {
        theme: "light2",
        backgroundColor: "rgba(0,0,0,0)",
        animationEnabled: true,
        title: {
            text: "Cashier Sold Percentage"
        },
        data: [{
            type: "pie",
            startAngle: 240,
            yValueFormatString: "##0.00\"%\"",
            indexLabel: "{label} {y}",
            dataPoints: data
        }]
    });
    chart.render();
}

function renderPaymentChart(data){
    var chart = new CanvasJS.Chart("paymentChartContainer", {
        theme: "light2",
        backgroundColor: "rgba(0,0,0,0)",
        animationEnabled: true,
        title: {
            text: "Transaction amount by type"
        },
        data: [{
            type: "pie",
            startAngle: 240,
            indexLabel: "{label} {y}",
            dataPoints: data
        }]
    });
    chart.render();
}