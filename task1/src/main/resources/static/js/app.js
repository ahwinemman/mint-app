function getCardDetails() {
    var card_number = document.getElementById('card-number').value;

    var div = document.getElementById('info-div');

    if (card_number.length == 0) {
        div.innerHTML = 'Please input the card number';
    }

    fetch("/card-scheme/verify/" + card_number)
        .then(response => {
        return response.text();
})
.then(data => {
        div.innerHTML = data;
})
.catch(err => {
        console.log("Something went wrong!", err);
});
}


function getNumberOfHits() {
    var start = document.getElementById('start').value;
    var limit = document.getElementById('limit').value;

    var div = document.getElementById('hit-info-div');

    if (start.length == 0 || limit.length ==  0) {

        div.innerHTML = 'Please fill in values for start and limit';
    }


    fetch("/card-scheme/stats?start=" + start + "&" + "limit=" + limit)
        .then(response => {
        return response.text();
})
.then(data => {
        div.innerHTML = data;
})
.catch(err => {
        console.log("Something went wrong!", err);
});
}