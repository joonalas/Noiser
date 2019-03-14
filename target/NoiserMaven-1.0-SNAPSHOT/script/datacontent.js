//browse stack includes viewed data items by name
//this helps going back when browsing music
class Stack {
    constructor() {
        this.items = [];
    }
    push(item) {
        this.items.push(item);
    }
    pop() {
        if(this.items.length > 0) {
           this.items.pop();
        }
    }
    peek() {
        if(this.items.length > 0) {
            return this.items[this.items.length - 1];
        }
    }
    getStackSize() {
        return this.items.length;
    }
}
var browseStack = new Stack();

//run callback after processing response, mainly good for creating back buttons
function requestAndView(reqURL, onclickFunc, callback) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            //create new unordered list for genres
            var ul = document.createElement('ul');
            ul.setAttribute('class', 'dataview');
            
            //remove innerHTML of the data container and add list
            var dataContainer = document.getElementById("dataContainer");
            while(dataContainer.firstChild) {
                dataContainer.removeChild(dataContainer.firstChild);
            }
            dataContainer.appendChild(ul);
            
            //convert json to list elements
            console.log(this.response);
            var objsArray = JSON.parse(this.response);
            objsArray.forEach(function (element, index, array) {
                var li = document.createElement('li');
                li.innerHTML = element.name;

                //if onclick function was given, set it
                if (onclickFunc !== null) {
                    li.onclick = onclickFunc;
                }

                ul.appendChild(li);
            });
            callback();
        }
    };
    xhttp.open("GET", reqURL, true);
    xhttp.send();
}

//-------------------LOAD FUNCTIONS----------------------------------

function loadGenres() {
    var reqURL = "GenreServlet";
    requestAndView(reqURL, function() {
        browseStack.push(this.innerHTML);
        loadArtistByGenre();
    }, function() {});
}

function loadArtistByGenre(){
    var reqURL = "ArtistServlet?genre=" + browseStack.peek();
    requestAndView(reqURL, 
    //onclick function for list elements
    function() {
        browseStack.push(this.innerHTML);
        loadAlbumByArtist();
    }, 
    //callback for creating back button
    function() {
        createBackButton(loadGenres);
    });
}

function loadAlbumByArtist() {
    var reqURL = "AlbumServlet?artist=" + browseStack.peek();
    requestAndView(reqURL, function() {
        browseStack.push(this.innerHTML);
        loadSongByAlbum();
    }, function() {
        //stack size of 1 means that the next item is the root of the browse
        if(browseStack.getStackSize() > 1) {
            createBackButton(loadArtistByGenre);
        } else { createBackButton(loadArtists); }
    });
}

function loadSongByAlbum() {
    var reqURL = "SongServlet?album=" + browseStack.peek();
    requestAndView(reqURL, null, 
    //callback for creating back button
    function() {
        if(browseStack.getStackSize() > 1) {
            createBackButton(loadAlbumByArtist);
        } else { createBackButton(loadAlbums); }
    });
}

function loadArtists() {
    var reqURL = "ArtistServlet";
    requestAndView(reqURL, function() {
        browseStack.push(this.innerHTML);
        loadAlbumByArtist();
    }, function() {});
}

function loadAlbums() {
    var reqURL = "AlbumServlet";
    requestAndView(reqURL, function() {
        browseStack.push(this.innerHTML);
        loadSongByAlbum();
    }, function() {});
}

function loadSongs() {
    var reqURL = "SongServlet";
    requestAndView(reqURL, null, function() {});
}

//----------------------------------------------------------------

function createBackButton(loadFunc) {
    var btn = document.createElement("BUTTON");
    btn.onclick = function() {
        browseStack.pop();
        loadFunc();
    };
    btn.appendChild(document.createTextNode("<"));
    document.getElementById("dataContainer").appendChild(btn);
}


document.body.onload = loadGenres();