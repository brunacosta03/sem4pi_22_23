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
    const user = document.getElementById("user");

    request.onload = function() {
        if(request.status === 200){
            setTimeout(() => {
                loader.style.display = "none";
                mainContainer.style.display = "block";

                if(user !== null){
                    user.innerText = user.innerText + " " + request.responseText;
                }
            }, 1);
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

function boardViewLoad(){
    checkUser();
    showBoard();
    fileListener();
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

                addBoardCreatedToList(JSON.parse(requestPost.responseText));

                notification("Board created successfully!", requestPost.status);
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

    const boardId = getBoardUserIsIn();
    const content = document.getElementById('post-it-content');

    const requestCreatePostIt = new XMLHttpRequest();

    requestCreatePostIt.onload = function() {
        if (requestCreatePostIt.status === 200) {
            console.log(JSON.parse(requestCreatePostIt.responseText));

            notification("Post-It created successfully!", requestCreatePostIt.status);
        } else {
            notification(requestCreatePostIt.responseText, requestCreatePostIt.status);
        }

        content.value = '';
        disableOverlay();
    };

    const token = getTokenCookie();

    requestCreatePostIt.open("POST", "/create_post_it", true);
    requestCreatePostIt.setRequestHeader("Content-Type", "application/json");

    if(token){
        requestCreatePostIt.setRequestHeader("Authorization", token);
    }

    const data = {
        postItContent: content.value,
        postItRow: rowPos,
        postItColumn: colPos,
        boardId: boardId
    };

    requestCreatePostIt.send(JSON.stringify(data));
}

let numberOfBoards = 0;

function getAllUserBoards(){
    const container = document.getElementById('all-boards-container');
    const request = new XMLHttpRequest();

    request.onload = function() {
        if(request.status === 200){
            const boards = JSON.parse(request.responseText);

            if(boards.length !== numberOfBoards){
                numberOfBoards = boards.length;

                boards.forEach((board) => {
                    const existingBoard = container.querySelector(`[data-board-id="${board.boardId}"]`);

                    if (!existingBoard) {
                        addBoardCreatedToList(board);
                    }
                });
            }

            setTimeout(() => getAllUserBoards(), 3000);
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

function addBoardCreatedToList(board){
    const container = document.getElementById('all-boards-container');

    const newBoardDiv = document.createElement('div');
    newBoardDiv.className = 'my-boards';
    newBoardDiv.setAttribute('data-board-id', board.boardId);

    newBoardDiv.innerHTML = '<a href="board/' + board.boardId + '">' +
        '<h3 class="title">' + board.boardTitle.value + '</h3>' +
        '<h3 class="title">Rows Number - ' + board.boardNRow.value + '</h3>' +
        '<h3 class="title">Columns Number - ' + board.boardNCol.value + '</h3>' +
        '</a>';

    container.appendChild(newBoardDiv);
}

let rowPos = 0;
let colPos = 0;

function showBoard() {
    const ulElement = document.querySelector('ul.columns');

    for(let i = 1; i <= 3; i++){
        const liElement = document.createElement('li');
        liElement.classList.add('column');

        const divElement = document.createElement('div');
        divElement.classList.add('entry-header');
        liElement.appendChild(divElement);

        const h4Element = document.createElement('h4');
        h4Element.textContent = 'To Do (' + i + ')';

        divElement.appendChild(h4Element);

        const ulPostItList = document.createElement('ul');
        ulPostItList.classList.add('post-it-list');

        for(let j = 2; j <= 3; j++){
            if(i === 1){
                const rowElement = document.createElement('div');
                rowElement.classList.add('entry-header');
                liElement.appendChild(rowElement);

                const h4Element = document.createElement('h4');
                h4Element.textContent = 'To Do (' + j + ')';
                rowElement.appendChild(h4Element);
            } else {
                const liPostItItem = document.createElement('li');
                liPostItItem.classList.add('post-it');
                liPostItItem.id = `post-it-${i}-${j}`;
                ulPostItList.appendChild(liPostItItem);

                const imgElement = document.createElement('img');
                imgElement.src = '../change-cell.png';
                imgElement.classList.add('add-image');
                liPostItItem.appendChild(imgElement);

                liPostItItem.onclick = () =>{
                    colPos = i;
                    rowPos = j;
                    showCreatePostIt();
                };
            }
        }

        if(i !== 1){
            liElement.appendChild(divElement);
            liElement.appendChild(ulPostItList);
        }

        ulElement.appendChild(liElement);
    }
}

function showCreatePostIt(){
    const createPostIt = document.getElementById("create-post-it-section");
    const overlay = document.getElementById("overlay");

    createPostIt.style.display = "block";
    overlay.style.display = "block";
}

function disableOverlay(){
    const overlay = document.getElementById("overlay");
    const createPostIt = document.getElementById("create-post-it-section");

    overlay.style.display = "none";
    createPostIt.style.display = "none";
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

function getBoardUserIsIn(){
    const link = window.location.href;
    const parts = link.split('/');
    const boardId = parts[parts.length - 1];

    return boardId;
}

function voteFor(option) {
	// var request = new XMLHttpRequest();
  	// request.open("PUT", "/votes/" + option , true);
  	// request.send();
    //     var vBoard=document.getElementById("votes");
    //     vBoard.innerHTML = vBoard.innerHTML + "<p>Casting your vote ... Please wait.";
}