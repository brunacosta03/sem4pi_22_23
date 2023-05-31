var validation = {
    isNumber:function(str) {
        var pattern = /^\d+\.?\d*$/;
        return pattern.test(str);
    }
};
function checkUserAuth() {
	const request = new XMLHttpRequest();

    request.onload = function() {
        if(request.status !== 401){
            window.location.href = window.location.origin + '/myboards';
        }
    };

  	request.open("GET", "/user", true);
	request.timeout = 5000;

    const token = getTokenCookie();

    if(token){
        request.setRequestHeader("Authorization", token);
    }

  	request.send();
}


function checkUser() {
    const request = new XMLHttpRequest();
    const mainContainer = document.getElementById("main-container");
    const loader = document.getElementById("loader");

    request.onload = function() {
        if(request.status === 200){
            setTimeout(() => {
                loader.style.display = "none";
                mainContainer.style.display = "block";
                console.log(request.responseText);
            }, 1);


            fileListener();
            getAllUserBoards();
        } else {
            window.location.href = window.location.origin;
        }
    };

    request.open("GET", "/user", true);
    request.timeout = 5000;

    const token = getTokenCookie();

    if(token){
        request.setRequestHeader("Authorization", token);
    }

    request.send();
}

function login(){
    event.preventDefault();

    const requestLogin = new XMLHttpRequest();

    requestLogin.onload = function() {
        if (requestLogin.status === 200) {
            document.cookie = "token=" + encodeURIComponent(requestLogin.responseText);

            window.location.href = window.location.origin + '/myboards';
        } else {
            notification(requestLogin.responseText, requestLogin.status);
        }
    };

    requestLogin.open("POST", "/login", true);
    requestLogin.setRequestHeader("Content-Type", "application/json");

    const data = {
        email: document.getElementById('email').value,
        password: document.getElementById('password').value
    };

    requestLogin.send(JSON.stringify(data));
}

function generateBoard(){
    event.preventDefault();

    const ulElement = document.querySelector('ul.columns');
    const boardTitle = document.getElementById('board-title').value;
    const numberOfColumns = document.getElementById('number-columns').value;
    const numberOfRows = document.getElementById('number-rows').value;

    while (ulElement.firstChild) {
        ulElement.removeChild(ulElement.firstChild);
    }

    if(boardTitle !== "" && validation.isNumber(numberOfColumns) && validation.isNumber(numberOfRows)){
        for(let i = 1; i <= numberOfColumns; i++){
            const liElement = document.createElement('li');
            liElement.classList.add('column');

            const divElement = document.createElement('div');
            divElement.classList.add('entry-header');
            liElement.appendChild(divElement);

            const h4Element = document.createElement('input');
            h4Element.setAttribute('id', 'Col' + i + 'Row' + 1);
            h4Element.setAttribute('placeholder', 'Entry Title...');
            divElement.appendChild(h4Element);

            const ulPostItList = document.createElement('ul');
            ulPostItList.classList.add('post-it-list');

            for(let j = 2; j <= numberOfRows; j++){
                if(i === 1){
                    const rowElement = document.createElement('div');
                    rowElement.classList.add('entry-header');
                    liElement.appendChild(rowElement);

                    const h4Element = document.createElement('input')
                    h4Element.setAttribute('id', 'Col' + i + 'Row' + j);
                    h4Element.setAttribute('placeholder', 'Entry Title...');
                    rowElement.appendChild(h4Element);
                } else {
                    const liPostItItem = document.createElement('li');
                    liPostItItem.classList.add('post-it');
                    ulPostItList.appendChild(liPostItItem);
                }
            }

            ulElement.appendChild(liElement);
        }
    } else {
        notification("Fill all fields correctly");
    }
}

function createBoard(){
    event.preventDefault();

    const form = document.getElementById('create-board-form');
    const boardTitle = document.getElementById('board-title').value;
    const numberOfColumns = document.getElementById('number-columns').value;
    const numberOfRows = document.getElementById('number-rows').value;
    const allEntrys = [];

    if(boardTitle !== "" && validation.isNumber(numberOfColumns) && validation.isNumber(numberOfRows)){

        for(let i = 1; i <= numberOfColumns; i++){
            allEntrys.push(document.getElementById('Col' + i + 'Row' + 1).value);
        }

        for(let i = 2; i <= numberOfRows; i++){
            allEntrys.push(document.getElementById('Col' + 1 + 'Row' + i).value);
        }

        const requestPost = new XMLHttpRequest();

        requestPost.onload = function() {
            if (requestPost.status === 200) {
                const ulElement = document.querySelector('ul.columns');

                while (ulElement.firstChild) {
                    ulElement.removeChild(ulElement.firstChild);
                }

                form.reset();

                notification(requestPost.responseText, requestPost.status);
            } else {
                notification(requestPost.responseText, requestPost.status);
            }
        };

        const token = getTokenCookie();

        requestPost.open("POST", "/create_board", true);
        requestPost.setRequestHeader("Content-Type", "application/json");

        if(token){
            requestPost.setRequestHeader("Authorization", token);
        }

        const data = {
            boardTitle: boardTitle,
            boardNRow: numberOfRows,
            boardNColumn: numberOfColumns,
            allBoardEntrys: allEntrys
        };

        requestPost.send(JSON.stringify(data));
    } else {
        notification("Fill all fields correctly");
    }
}

function createPostIt(){
    event.preventDefault();

    const form = document.getElementById('create-post-it-form');
    const boardId = document.getElementById('board-id').value;
    const rowPos = document.getElementById('post-it-row-position').value;
    const colPos = document.getElementById('post-it-column-position').value;
    const content = document.getElementById('post-it-content').value;

    const requestCreatePostIt = new XMLHttpRequest();

    requestCreatePostIt.onload = function() {
        if (requestCreatePostIt.status === 200) {
            form.reset();
            notification(requestCreatePostIt.responseText, requestCreatePostIt.status);
        } else {
            notification(requestCreatePostIt.responseText, requestCreatePostIt.status);
        }
    };

    const token = getTokenCookie();

    requestCreatePostIt.open("POST", "/create_post_it", true);
    requestCreatePostIt.setRequestHeader("Content-Type", "application/json");

    if(token){
        requestCreatePostIt.setRequestHeader("Authorization", token);
    }

    const data = {
        postItContent: content,
        postItRow: rowPos,
        postItColumn: colPos,
        boardId: boardId
    };

    requestCreatePostIt.send(JSON.stringify(data));
}

function getAllUserBoards(){
    const request = new XMLHttpRequest();

    request.onload = function() {
        if(request.status === 200){
            console.log(JSON.parse(request.responseText))
        }
    };

    request.open("GET", "/all_my_boards", true);
    request.timeout = 5000;

    const token = getTokenCookie();

    if(token){
        request.setRequestHeader("Authorization", token);
    }

    request.send();
}

function fileListener() {
    const file = document.getElementById("file");
    const content = document.getElementById('post-it-content');

    file.addEventListener("change", ev => {
        ev.preventDefault();

        const formdata = new FormData();
        formdata.append("image", ev.target.files[0]);

        const request = new XMLHttpRequest();
        request.open("POST", "https://api.imgur.com/3/image/");
        request.setRequestHeader("Authorization", "Client-ID eae95290510293a");

        request.onreadystatechange = function () {
            if (request.readyState === XMLHttpRequest.DONE) {
                if (request.status === 200) {
                    const response = JSON.parse(request.responseText);
                    content.textContent = response.data.link;

                    notification("Image upload with success!", request.status);
                } else {
                    notification("Problem uploading image!", request.status);
                }
            }
        };
        request.send(formdata);
    });
}


function notification(text, code){
    const notificationContainer = document.getElementById("container-notification");
    const reactangleNotification = document.getElementById("rectangle-notification");
    const notificationText = document.getElementById("notification-text");

    notificationText.textContent = text;
    notificationContainer.style.display = "flex";

    if(code === 200){
        reactangleNotification.style.backgroundColor = "#539165";
    } else {
        reactangleNotification.style.backgroundColor = "#ED2B2A";
    }

    setTimeout(() => notificationContainer.style.display = "none", 4000);
}

function getTokenCookie(){
    const cookies = document.cookie.split(";").map(cookie => cookie.trim());
    const tokenCookie = cookies.find(cookie => cookie.startsWith("token="));
    const token = decodeURIComponent(tokenCookie.split("=")[1]);

    return token;
}

function voteFor(option) {
	// var request = new XMLHttpRequest();
  	// request.open("PUT", "/votes/" + option , true);
  	// request.send();
    //     var vBoard=document.getElementById("votes");
    //     vBoard.innerHTML = vBoard.innerHTML + "<p>Casting your vote ... Please wait.";
}