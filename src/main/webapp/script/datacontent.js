//browse stack includes viewed data items by database id
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
    popAll() {
        while(this.items.length > 0) {
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

//----------------------XML HTTP Stuff----------------------------------------

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
            var objsArray = JSON.parse(this.response);
            objsArray.forEach(function (element, index, array) {
                var li = document.createElement('li');
                li.innerHTML = element.name;
                li.setAttribute("data-dbId", element.id);

                //if onclick function was given, set it
                if (onclickFunc !== null) {
                    li.onclick = onclickFunc;
                }

                ul.appendChild(li);
            });
            //invoke callback if there's any
            if(callback !== null) {
                callback();
            }
        }
    };
    xhttp.open("GET", reqURL, true);
    xhttp.send();
}

function requestAndPlay(reqURL, callback) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            var audioplayer = document.getElementById("audioplayer");
            audioplayer.pause();
            audioplayer.currentTime = 0;
            while(audioplayer.firstChild) {
                audioplayer.removeChild(audioplayer.firstChild);
            }
            
            var fileArray = JSON.parse(this.response);
            fileArray.forEach(function(file) {
                var source = document.createElement("SOURCE");
                source.src = file.path;
                console.log(source.src);
                source.type = "audio/" + file.format;
                console.log(source.type);
                audioplayer.appendChild(source);
            });
            
            audioplayer.load();
            audioplayer.play();
            
            //invoke callback if there's any
            if(callback !== null) {
                callback();
            }
        }
    };
    xhttp.open("GET", reqURL, true);
    xhttp.send();
}

//-------------------------------------------------------------------


//-------------------LOAD FUNCTIONS----------------------------------

function loadGenres() {
    var reqURL = "GenreServlet";
    requestAndView(reqURL, function() {
        browseStack.push(this.getAttribute("data-dbId"));
        loadArtistByGenre();
    }, null);
}

function loadArtistByGenre(){
    var reqURL = "ArtistServlet?genre=" + browseStack.peek();
    requestAndView(reqURL, 
    //onclick function for list elements
    function() {
        browseStack.push(this.getAttribute("data-dbId"));
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
        browseStack.push(this.getAttribute("data-dbId"));
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
    requestAndView(reqURL,
    //when clicked, play the song
    function() {
        var reqURL = "SoundFileServlet?song=" + this.getAttribute("data-dbId");
        requestAndPlay(reqURL, null);
    }, 
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
        browseStack.push(this.getAttribute("data-dbId"));
        loadAlbumByArtist();
    }, null);
}

function loadAlbums() {
    var reqURL = "AlbumServlet";
    requestAndView(reqURL, function() {
        browseStack.push(this.getAttribute("data-dbId"));
        loadSongByAlbum();
    }, null);
}

function loadSongs() {
    var reqURL = "SongServlet";
    requestAndView(reqURL,
    //when clicked, play the song
    function() {
        var reqURL = "SoundFileServlet?song=" + this.getAttribute("data-dbId");
        requestAndPlay(reqURL, null);
    }, null);
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


document.body.onload = function() {
    document.getElementById("genreNav").onclick = function() {
        browseStack.popAll();
        loadGenres();
    };
    document.getElementById("artistNav").onclick = function() {
        browseStack.popAll();
        loadArtists();
    };
    document.getElementById("albumNav").onclick = function() {
        browseStack.popAll();
        loadAlbums();
    };
    document.getElementById("songNav").onclick = function() {
        browseStack.popAll();
        loadSongs();
    };
    loadGenres();
};
