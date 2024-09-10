function myFunction() {
    var x = document.getElementById("myNumber").value;
    let y = Math.floor(Math.random() * 10);
    if(x == y){
        document.getElementById("demo").innerHTML = "hadal jsi spravne";
    }
    else{
        document.getElementById("demo").innerHTML = "hadal jsi spatne";
    }
  }