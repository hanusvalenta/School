function myFunction() {
    var x = document.getElementById("myNumber").value;
    let y = Math.floor(Math.random() * 10);
    if(x == y){
        document.getElementById("demo").innerHTML = "Uhodl jsi :)";
    }
    else{
        document.getElementById("demo").innerHTML = "Hádal jsi špatně číslo na uhodnutí bylo " + y;
    }
  }