const validation = {
    isNumber:function(str) {
        var pattern = /^\d+\.?\d*$/;
        return pattern.test(str);
    },
    isLink: function(str) {
        var pattern = /^(https?|ftp):\/\/[^\s/$.?#].[^\s]*$/i;
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
    fileListener("file", "post-it-content");
    fileListener("update-file", "update-post-it-content");
    setHistoryRef();
}

function historyLoad(){
    checkUser();
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
            const data = JSON.parse(requestCreatePostIt.responseText);

            writeContentInCell(data.postItColumn.value, data.postItRow.value, data.postItContent.value);

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

function fileListener(fileElementId, contentId) {
    const file = document.getElementById(fileElementId);
    const content = document.getElementById(contentId);

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

                    content.value = response.data.link;

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
                liPostItItem.id = `cell-${i}-${j}`;
                ulPostItList.appendChild(liPostItItem);

                const postItText = document.createElement('p');
                liPostItItem.classList.add('post-it-text');
                postItText.id = `post-it-${i}-${j}`;
                liPostItItem.appendChild(postItText);

                const imgElement = document.createElement('img');
                imgElement.src = '../change-cell.png';
                imgElement.classList.add('add-image');
                liPostItItem.appendChild(imgElement);

                const postItImage = document.createElement('img');
                postItImage.classList.add('post-it-img');
                postItImage.id = `img-${i}-${j}`;
                liPostItItem.appendChild(postItImage);

                liPostItItem.onclick = () =>{
                    colPos = i;
                    rowPos = j;
                    showCreateUpdatePostIt(i, j);
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

function updatePostItContent(){
    event.preventDefault();

    const boardId = getBoardUserIsIn();
    const content = document.getElementById('update-post-it-content');

    const requestCreatePostIt = new XMLHttpRequest();

    requestCreatePostIt.onload = function() {
        if (requestCreatePostIt.status === 200) {
            const data = JSON.parse(requestCreatePostIt.responseText);

            writeContentInCell(data.postItColumn.value, data.postItRow.value, data.postItContent.value);

            notification("Post-It updated successfully!", requestCreatePostIt.status);
        } else {
            notification(requestCreatePostIt.responseText, requestCreatePostIt.status);
        }

        content.value = '';
        disableOverlay();
    };

    const token = getTokenCookie();

    requestCreatePostIt.open("PUT", "/update_post_it", true);
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

function deletePostIt(){
    event.preventDefault();

    const boardId = getBoardUserIsIn();

    const requestCreatePostIt = new XMLHttpRequest();

    requestCreatePostIt.onload = function() {
        if (requestCreatePostIt.status === 200) {
            const data = JSON.parse(requestCreatePostIt.responseText);

            writeContentInCell(data.postItColumn.value, data.postItRow.value, "");

            notification("Post-It deleted successfully!", requestCreatePostIt.status);
        } else {
            notification(requestCreatePostIt.responseText, requestCreatePostIt.status);
        }

        disableOverlay();
    };

    const token = getTokenCookie();

    requestCreatePostIt.open("DELETE", "/delete_post_it", true);
    requestCreatePostIt.setRequestHeader("Content-Type", "application/json");

    if(token){
        requestCreatePostIt.setRequestHeader("Authorization", token);
    }

    const data = {
        postItRow: rowPos,
        postItColumn: colPos,
        boardId: boardId
    };

    requestCreatePostIt.send(JSON.stringify(data));
}

function undoPostIt(){
    event.preventDefault();

    console.log("UNDOOO");
}

function undoDeletedPostIt(){
    event.preventDefault();

    console.log("UNDOOO DELETED POST IT");
}

function setHistoryRef() {
    const history = document.getElementById("history-button");

    history.href = "/history/" + getBoardUserIsIn();
}

function showCreateUpdatePostIt(colPos, rowPos){
    const postIt = document.getElementById('post-it-' + colPos + '-' + rowPos);
    const postItImg = document.getElementById('img-' + colPos + '-' + rowPos);
    const overlay = document.getElementById("overlay");

    if(postIt.textContent === '' && postItImg.src === ''){
        const createPostIt = document.getElementById("create-post-it-section");
        createPostIt.style.display = "block";
    } else {
        const changePostIt = document.getElementById("change-post-it-section");
        changePostIt.style.display = "block";
    }

    overlay.style.display = "block";
}

function disableOverlay(){
    const overlay = document.getElementById("overlay");
    const createPostIt = document.getElementById("create-post-it-section");
    const changePostIt = document.getElementById("change-post-it-section");

    overlay.style.display = "none";
    createPostIt.style.display = "none";
    changePostIt.style.display = "none";
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

    if(tokenCookie !== undefined){
        return decodeURIComponent(tokenCookie.split("=")[1]);
    }

    return null;
}

function getBoardUserIsIn(){
    const link = window.location.href;
    const parts = link.split('/');
    const boardId = parts[parts.length - 1];

    return boardId;
}

function writeContentInCell(column, row, content){
    const cell = document.getElementById('cell-' + column + '-' + row);
    const postItText = document.getElementById('post-it-' + column + '-' + row);
    const postItImg = document.getElementById('img-' + column + '-' + row);

    if(validation.isLink(content)){
        postItText.style.display = "none";
        postItImg.style.display = "block";
        postItText.textContent = "";
        postItImg.src = content;
    } else {
        postItImg.style.display = "none";
        postItText.style.display = "block";
        postItImg.removeAttribute('src');
        postItText.textContent = content;
    }

    if(content === '') {
        cell.style.backgroundColor = "#FFFFFF";
    } else {
        cell.style.backgroundColor = "#F9EA83";
    }
}