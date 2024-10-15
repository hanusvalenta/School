function one(){
    let slova = document.getElementById('fslovo').value;
    slovaArray = slova.split(' ');

    // vrátit podle abecedy
    document.getElementById('text').innerHTML = slovaArray.sort();
}

function two(){
    let slova = document.getElementById('fslovo').value;
    slovaArray = slova.split(' ');

    // vrátit podle délky slova
    document.getElementById('text').innerHTML = slovaArray.sort(function(a, b){return a.length - b.length})
}

function shuffle(array) {
    var copy = [], n = array.length, i;
  
    while (n) {
  
      i = Math.floor(Math.random() * n--);
  
      copy.push(array.splice(i, 1)[0]);
    }
  
    return copy;
}

function three(){
    let slova = document.getElementById('fslovo').value;
    slovaArray = slova.split(' ');

    // random seřadit pomocí Fisher–Yates algortmu
    document.getElementById('text').innerHTML = shuffle(slovaArray);
}

function four(){
    let slova = document.getElementById('fslovo').value;
    slovaArray = slova.split(' ');

    function isSorted(array) {
        for (let i = 1; i < array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }
    
    function shuffle(array) {
        for (let i = array.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [array[i], array[j]] = [array[j], array[i]];
        }
    }
    
    function bogosort(array) {
        while (!isSorted(array)) {
            shuffle(array);
        }
        return array;
    }

    // seřadit pomocí bogosortu
    bogosort(slovaArray);
    document.getElementById('text').innerHTML = slovaArray;
}