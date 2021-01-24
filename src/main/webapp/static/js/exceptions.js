let currentPage = 1;
let limit = 2;

document.onload(getPage);

function getPage() {
    fetch(`/api/expositions?limit=${limit}&page=${currentPage}`)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            updatePage(data);
        });
}

function updatePage(data) {
    
}