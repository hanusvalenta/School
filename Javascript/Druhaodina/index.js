let studenti = [];

function zapsatSe(){
    let fname = document.getElementById('fname').value;
    let fLastName = document.getElementById('fLastName').value;
    let fVek = document.getElementById('fVek').value;
    let fKurz = document.getElementById('fKurz').value;

    let student = {
        jmeno: fname,
        prijmeni: fLastName,
        vek: fVek,
        kurz: fKurz
    };

    if(fname != '' & fLastName != '' & fKurz != '' & fVek != ''){
        studenti.push(student);

        document.getElementById('text').innerHTML = 'Student zapsán!';
        console.log("Student zapsán!")
    }
    else{
        document.getElementById('text').innerHTML = 'Neplatné údaje!';
        console.log("Neplatné údaje!")
    }
    
 
    document.getElementById('fname').value = '';
    document.getElementById('fLastName').value = '';
    document.getElementById('fVek').value = '';
    document.getElementById('fKurz').value = '';
}

function filterByAge(){

}

function ukazat(){
    let output = '';
    studenti.forEach((student, index) => {
        output += student.jmeno + ' ' + student.prijmeni + ' ' + student.fVek + ' - ' + student.kurz + '<br>';
    });

    document.getElementById('text').innerHTML = output;
}