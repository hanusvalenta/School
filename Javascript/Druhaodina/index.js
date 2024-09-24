let studenti = [];

function zapsatSe() {
    let fname = document.getElementById('fname').value;
    let fLastName = document.getElementById('fLastName').value;
    let fKurz = document.getElementById('fKurz').value;

    let student = {
        jmeno: fname,
        prijmeni: fLastName,
        kurz: fKurz
    };

    if(fname != '' & fLastName != '' & fKurz != ''){
        studenti.push(student);

        document.getElementById('zapsano').innerHTML = 'Student zapsán!';
        console.log("Student zapsán!")
    }
    else{
        document.getElementById('zapsano').innerHTML = 'Neplatné údaje!';
        console.log("Neplatné údaje!")
    }
    
 
    document.getElementById('fname').value = '';
    document.getElementById('fLastName').value = '';
    document.getElementById('fKurz').value = '';
}

function ukazat() {
    let output = '';
    studenti.forEach((student, index) => {
        output += (index + 1) + '. ' + student.jmeno + ' ' + student.prijmeni + ' - ' + student.kurz + '<br>';
    });

    document.getElementById('ukazka').innerHTML = output;
}