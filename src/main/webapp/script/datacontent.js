function loadGenres() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            //create new unordered list for genres
            var ul = document.createElement('ul');
            ul.setAttribute('class', 'dataview');
            
            //remove innerHTML of the data container and add list
            var dataContainer = document.getElementById("dataContent");
            while(dataContainer.firstChild) {
                dataContainer.removeChild(dataContainer.firstChild);
            }
            dataContainer.appendChild(ul);
            
            //convert json to list elements
            var genreArray = JSON.parse(this.response).objs;
            genreArray.forEach(function (element, index, array) {
               var li = document.createElement('li');
               li.innerHTML = element.name;
               li.onclick = loadArtistByGenre;
               ul.appendChild(li);
            });
        }
    };
    xhttp.open("GET", "GenreServlet", true);
    xhttp.send();
}

function loadArtistByGenre(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200) {
            //create new unordered list for artist
            var ul = document.createElement('ul');
            ul.setAttribute('class', 'dataview');
            
            //remove innerHTML of the data container and add list
            var dataContainer = document.getElementById("dataContent");
            while(dataContainer.firstChild) {
                dataContainer.removeChild(dataContainer.firstChild);
            }
            dataContainer.appendChild(ul);
            
            //convert json to list elements
            var artistArray = JSON.parse(this.response).objs;
            artistArray.forEach(function (element, index, array) {
               var li = document.createElement('li');
               li.innerHTML = element.name;
               //onclick here
               ul.appendChild(li);
            });
        }
    };
    
    var reqURL = "ArtistServlet?genre=" + this.innerHTML;
    
    xhttp.open("GET", reqURL, true);
    xhttp.send();
}

document.body.onload = loadGenres();