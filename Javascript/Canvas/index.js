const canvas = document.getElementById('Canvas');
const ctx = canvas.getContext('2d');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

let maxIterations = 100;
let zoom = 200;
let offsetX = -0.75;
let offsetY = 0;
let targetZoom = zoom;
let zoomSpeed = 1.02;
let easingFactor = 0.5;
let shiftSpeed = 0.05;

let power = 2;

function Fractal(cRe, cIm, maxIter) {
    let zRe = 0, zIm = 0;
    let n = 0;

    while (n < maxIter) {
        const magnitude = Math.sqrt(zRe * zRe + zIm * zIm);
        const zReTemp = Math.pow(zRe * zRe + zIm * zIm, power / 2) * Math.cos(power * Math.atan2(zIm, zRe)) + cRe * Math.exp(-magnitude);
        const zImTemp = Math.pow(zRe * zRe + zIm * zIm, power / 2) * Math.sin(power * Math.atan2(zIm, zRe)) + cIm * Math.exp(-magnitude);

        zRe = zReTemp;
        zIm = zImTemp;

        if (zRe * zRe + zIm * zIm > 4) {
            break;
        }
        n++;
    }

    return n;
}

function drawFractal() {
    const width = canvas.width;
    const height = canvas.height;

    ctx.clearRect(0, 0, width, height);

    for (let x = 0; x < width; x++) {
        for (let y = 0; y < height; y++) {
            const cRe = (x - width / 2) / zoom + offsetX;
            const cIm = (y - height / 2) / zoom + offsetY;

            const n = Fractal(cRe, cIm, maxIterations);

            const color = n === maxIterations ? 0 : 255 - Math.floor((n / maxIterations) * 255);

            ctx.fillStyle = `rgb(${color}, ${color}, ${color})`;
            ctx.fillRect(x, y, 1, 1);
        }
    }
}

function animate() {
    targetZoom *= zoomSpeed;
    zoom += (targetZoom - zoom) * easingFactor;

    offsetX -= shiftSpeed / zoom;

    drawFractal();
    requestAnimationFrame(animate);
}

animate();

window.addEventListener('resize', () => {
    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    zoom = 200;
    offsetX = -0.75;
    offsetY = 0;
    targetZoom = zoom;
    drawFractal();
});